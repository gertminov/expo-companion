package routes

import (
	"backend/cmd/question"
	"github.com/labstack/echo/v5"
	"github.com/pocketbase/pocketbase"
	"github.com/pocketbase/pocketbase/apis"
	"github.com/pocketbase/pocketbase/core"
	"net/http"
)

func RegisterGenerateQuestionsRoute(e *core.ServeEvent, app *pocketbase.PocketBase) error {
	// add questions route to pocketbase
	_, err := e.Router.AddRoute(echo.Route{
		Method: http.MethodGet,
		Path:   "/api/questions",
		Handler: func(c echo.Context) error {
			return question.MapQuestionToLeaf(c, app)
		},
		Middlewares: []echo.MiddlewareFunc{
			//request must have Authorization header. Can be optained by calling /api/collections/users/auth-with-password
			//for more information read https://pocketbase.io/docs/authentication/#authenticate-as-app-user or the build in docs ob pocketbase
			apis.RequireRecordAuth(),
			apis.ActivityLogger(app),
		},
	})
	return err
}
