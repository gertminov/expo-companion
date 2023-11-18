package data

import (
	"backend/cmd/data/tableNames"
	"encoding/json"
	"errors"
	"fmt"
	"github.com/pocketbase/dbx"
	"github.com/pocketbase/pocketbase/daos"
	"github.com/pocketbase/pocketbase/models"
	"path/filepath"
)

var _ models.Model = (*SessionQuestion)(nil)

type SessionQuestion struct {
	models.BaseModel
	QuestionID   string `db:"questionId" json:"question_id"`
	QuestionText string `db:"questionText" json:"question_text"`
	Answer       string `db:"answer" json:"answer"`
	TeacherId    string `db:"teacherId" json:"teacher_id"`
	LeafId       string `db:"leafId" json:"leaf_id"`
	Category     string
}

func (s SessionQuestion) TableName() string {
	return tableNames.SessionQuestion
}

func SessionQuestionQuery(dao *daos.Dao) *dbx.SelectQuery {
	return dao.ModelQuery(&SessionQuestion{})
}

func GetAllSessionQuestions(dao *daos.Dao) (sessionQuestions []*SessionQuestion, err error) {
	sessionQuestions = []*SessionQuestion{}
	err = SessionQuestionQuery(dao).All(&sessionQuestions)
	if err != nil {
		return nil, errors.New("there was an error while getting sessionQuestions from DB")
	}
	return sessionQuestions, nil
}

func GetSessionsQuestionsForTeacher(dao *daos.Dao, teacherID string, dataDir string) (sessionQuestions []*SessionQuestion, err error) {
	sessionQuestions = []*SessionQuestion{}
	err = SessionQuestionQuery(dao).Where(dbx.HashExp{"teacherId": teacherID}).All(&sessionQuestions)
	if err != nil {
		return nil, errors.New("could not fetch sessionquestions for teacher: " + teacherID)
	}

	collection, err := dao.FindCollectionByNameOrId(tableNames.SessionQuestion)
	if err != nil {
		return nil, errors.New(`could not fetch collection: ` + tableNames.SessionQuestion)
	}

	questionCategories := getCategories(dao)
	mapCategories(sessionQuestions, questionCategories)

	unmarshalFirstFileName(sessionQuestions, dataDir, collection.Id)

	return sessionQuestions, nil
}

func unmarshalFirstFileName(questions []*SessionQuestion, dataDir string, collectionId string) {
	for _, question := range questions {
		filenames := cleanJsonArray(question.Answer)
		if len(filenames) < 1 {
			continue
		}
		path := getFilePath(question, dataDir, collectionId, filenames[0])
		question.Answer = path
	}
}

func getCategories(dao *daos.Dao) map[string]string {

	query := dao.DB().NewQuery("SELECT DISTINCT question.id, category.name FROM category JOIN question ON question.category=category.id JOIN sessionQuestion ON sessionQuestion.questionId = question.id")
	rows, err := query.Rows()
	if err != nil {
		fmt.Println(err)
	}

	categories := make(map[string]string)
	var id, name string
	for rows.Next() {
		rows.Scan(&id, &name)
		categories[id] = name
	}
	return categories
}

func mapCategories(questions []*SessionQuestion, categories map[string]string) {
	for _, question := range questions {
		cat := categories[question.QuestionID]
		question.Category = cat
	}
}

func getFilePath(question *SessionQuestion, dataDir string, collectionID string, filename string) string {
	filePath := filepath.Join(dataDir, "storage", collectionID, question.Id, filename)
	return filePath
}

func cleanJsonArray(jsonString string) []string {
	var arr []string
	err := json.Unmarshal([]byte(jsonString), &arr)
	if err != nil {
		fmt.Println(err)
	}
	return arr
}
