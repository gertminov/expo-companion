package createPDF

import (
	"backend/cmd/data"
	"bytes"
	"github.com/pocketbase/pocketbase"
)

func Generate(app *pocketbase.PocketBase, teacherID string) (bytes.Buffer, error) {
	questions, err := data.GetSessionsQuestionsForTeacher(app.Dao(), teacherID, app.DataDir())
	if err != nil {
		return bytes.Buffer{}, err
	}
	pdf, err := generatePDF(questions)

	return pdf, err

}
