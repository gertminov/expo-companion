package routes

import (
	"backend/cmd/createPDF"
	"backend/cmd/data"
	"backend/cmd/mail"
	"github.com/labstack/echo/v5"
	"github.com/pocketbase/pocketbase"
	"github.com/pocketbase/pocketbase/core"
	"net/http"
)

func RegisterSendEmailRoute(e *core.ServeEvent, app *pocketbase.PocketBase) error {
	e.Router.AddRoute(echo.Route{
		Method: http.MethodPost,
		Path:   "/api/sendmail",
		Handler: func(c echo.Context) error {
			teacher := data.NewTeacher().FromJson(c)
			pdfBuffer, err := createPDF.Generate(app, teacher.Id)
			if err != nil {
				c.String(http.StatusInternalServerError, "Could not generate pdf for teacher: "+teacher.Id)
				return err
			}
			err = mail.SendMailToTeacher(teacher.EMail, pdfBuffer, app)
			if err != nil {
				c.String(http.StatusInternalServerError, "Could not send email to teacher:"+teacher.Id)
				return err
			}
			c.String(http.StatusOK, "send email to teacher: "+teacher.Id)
			return err
		},
		Middlewares: []echo.MiddlewareFunc{
			//apis.RequireAdminAuth(),
		},
	})

	return nil
}
