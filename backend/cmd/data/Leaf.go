package data

import (
	"backend/cmd/data/tableNames"
	"errors"
	"github.com/pocketbase/dbx"
	"github.com/pocketbase/pocketbase/daos"
	"github.com/pocketbase/pocketbase/models"
)

var _ models.Model = (*Leaf)(nil)

type Leaf struct {
	models.BaseModel
	ID         string `db:"id" json:"id"`
	CategoryID string `db:"category" json:"category"`
	NfcId      string `db:"nfcId" json:"nfc_id"`
}

func (m *Leaf) TableName() string {
	return tableNames.Leaf
}

func LeafQuery(dao *daos.Dao) *dbx.SelectQuery {
	return dao.ModelQuery(&Leaf{})
}

func GetLeafs(dao *daos.Dao) (leafs []*Leaf, err error) {
	leafs = []*Leaf{}
	err = LeafQuery(dao).All(&leafs)
	if err != nil {
		return nil, errors.New("there was an error while getting the Leafs")
	}

	//categories, err := GetCategories(dao)
	//if err != nil {
	//	return nil, errors.New("there was an error while getting the Categories")
	//}
	//
	//leafs = MapCatogories(leafs, categories)

	return leafs, nil
}

// GetLeafsByCategory returns a map with the category as key and the leaf as value from the database
func GetLeafsByCategory(dao *daos.Dao) (leafs map[string][]*Leaf, err error) {
	leafs = map[string][]*Leaf{}
	leafList, err := GetLeafs(dao)
	if err != nil {
		return nil, err
	}
	for _, leaf := range leafList {
		leafList := leafs[leaf.CategoryID]
		leafs[leaf.CategoryID] = append(leafList, leaf)
	}

	return leafs, nil
}
