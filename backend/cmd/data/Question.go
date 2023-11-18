package data

import (
	"backend/cmd/data/tableNames"
	"fmt"
	"github.com/pocketbase/dbx"
	"github.com/pocketbase/pocketbase/apis"
	"github.com/pocketbase/pocketbase/daos"
	"github.com/pocketbase/pocketbase/models"
	"strconv"
)

var _ models.Model = (*Question)(nil)

type Question struct {
	models.BaseModel
	Text       string
	CategoryID string `db:"category" json:"category"`
	Active     bool
}

func (m *Question) TableName() string {
	return tableNames.Question
}

func QuestionQuery(dao *daos.Dao) *dbx.SelectQuery {
	return dao.ModelQuery(&Question{})
}

func GetQuestionsByCategory(dao *daos.Dao, categoryID string, amt int) (questions []*Question, err error) {
	sql := "SELECT * FROM 'question' WHERE category = " + "'" + categoryID + "'" + " ORDER BY RANDOM() LIMIT " + strconv.Itoa(amt)
	err = dao.DB().NewQuery(sql).All(&questions)
	if err != nil {
		return nil, err
	}
	return questions, nil
}

// GetQuestionsForLeafs Returns a map with the NFC IDs as key and the Questions as value
func GetQuestionsForLeafs(dao *daos.Dao, leafs map[string][]*Leaf) (map[string]*Question, *apis.ApiError) {

	questionsMap := make(map[string]*Question)

	//go through all categories
	for category, leafsInCategory := range leafs {
		questions, err := GetQuestionsByCategory(dao, category, len(leafsInCategory))
		if err != nil {
			return nil, apis.NewApiError(500, "could not get questions for category: "+category+"reason: "+err.Error(), nil)
		}
		// check if enough questions for leafs
		if len(leafsInCategory) > len(questions) {
			errorMessage := fmt.Sprintf("Not enough questions for amount of leafs in category: %s.\n"+
				"amt Leafs: %d, amt Questions: %d", category, len(leafsInCategory), len(questions))
			return nil, apis.NewApiError(500, errorMessage, nil)
		}
		//map each questions to a LeafID
		for i, leaf := range leafsInCategory {
			questionsMap[leaf.NfcId] = questions[i]
		}
	}
	categories, err := GetCategories(dao)
	if err != nil {
		return nil, apis.NewApiError(500, "Problems fetching Categories", nil)
	}
	MapCatogories(questionsMap, categories)

	return questionsMap, nil
}
