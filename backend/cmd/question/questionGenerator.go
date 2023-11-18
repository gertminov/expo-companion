package question

import (
	"backend/cmd/data"
	"fmt"
	"github.com/labstack/echo/v5"
	"github.com/pocketbase/pocketbase"
	"net/http"
)

func MapQuestionToLeaf(c echo.Context, app *pocketbase.PocketBase) error {
	leafs, err := data.GetLeafsByCategory(app.Dao())
	if err != nil {
		fmt.Println("error getting leafs")
		return c.String(http.StatusNotFound, err.Error())
	}

	questions, apiError := data.GetQuestionsForLeafs(app.Dao(), leafs)
	if apiError != nil {
		fmt.Println("error getting quesitons")
		fmt.Println(apiError.Error())
		return apiError
	}

	return c.JSON(http.StatusOK, questions)
}
