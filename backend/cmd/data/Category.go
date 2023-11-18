package data

import (
	"backend/cmd/data/tableNames"
	"github.com/pocketbase/dbx"
	"github.com/pocketbase/pocketbase/daos"
	"github.com/pocketbase/pocketbase/models"
)

var _ models.Model = (*Category)(nil)

type Category struct {
	models.BaseModel
	Name string `db:"name" json:"category"`
}

func (m *Category) TableName() string {
	return tableNames.Category
}

func CategoryQuery(dao *daos.Dao) *dbx.SelectQuery {
	return dao.ModelQuery(&Category{})
}

func GetCategories(dao *daos.Dao) ([]*Category, error) {
	categories := []*Category{}
	err := CategoryQuery(dao).All(&categories)
	if err != nil {
		return nil, err
	}
	return categories, nil
}

func MapCatogories(leafs map[string]*Question, categories []*Category) map[string]*Question {
	categoryMap := make(map[string]*Category)
	for _, category := range categories {
		categoryMap[category.Id] = category
	}
	for _, leaf := range leafs {
		leaf.CategoryID = categoryMap[leaf.CategoryID].Name
	}
	return leafs
}
