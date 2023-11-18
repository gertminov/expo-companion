package mail

import (
	"bytes"
	"github.com/pocketbase/pocketbase"
	"github.com/pocketbase/pocketbase/tools/mailer"
	"io"
	"net/mail"
)

func SendMailToTeacher(mailAddress string, pdf bytes.Buffer, app *pocketbase.PocketBase) error {

	template := app.Settings().Meta.VerificationTemplate
	appName := app.Settings().Meta.AppName
	appUrl := app.Settings().Meta.AppUrl
	subject, body, _ := template.Resolve(appName, appUrl, "")

	var bytesReadr = bytes.NewReader(pdf.Bytes())
	mp := make(map[string]io.Reader)
	mp["notizen.pdf"] = bytesReadr

	email := mailer.Message{
		From: mail.Address{
			Name:    app.Settings().Meta.SenderName,
			Address: app.Settings().Meta.SenderAddress,
		},
		To: mail.Address{
			Name:    "",
			Address: mailAddress,
		},
		Bcc:         nil,
		Cc:          nil,
		Subject:     subject,
		HTML:        body,
		Headers:     nil,
		Attachments: mp,
	}
	err := app.NewMailClient().Send(&email)
	return err
}
