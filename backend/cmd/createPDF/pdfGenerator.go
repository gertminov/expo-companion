package createPDF

import (
	"backend/cmd/createPDF/images"
	"backend/cmd/createPDF/util"
	"backend/cmd/data"
	"bytes"
	"encoding/base64"
	"errors"
	"fmt"
	"github.com/johnfercher/maroto/pkg/color"
	"github.com/johnfercher/maroto/pkg/consts"
	"github.com/johnfercher/maroto/pkg/pdf"
	"github.com/johnfercher/maroto/pkg/props"
	"strconv"
)

var space util.VerticalSpace
var footerHeight float64 = 2

func generatePDF(questions []*data.SessionQuestion) (bytes.Buffer, error) {
	m := pdf.NewMaroto(consts.Portrait, consts.A4)
	//m.SetFirstPageNb(1)
	space = util.NewVerticalSpace(m)
	space.Shorten(footerHeight)

	buildIntroPage(m)
	m.SetFirstPageNb(1) //make first page start at 1
	buildFooter(m)

	var err error = nil
	for _, question := range questions {
		space.Reset()
		buildQuestionSection(m, question)
		//Throw error if no answer file in sessionQuestion
		if question.Answer == "[]" {
			if err == nil {
				err = errors.New("")
			}
			err = errors.New(err.Error() + ", \n" + "no ansfer file defined for question: " + question.QuestionID + " for teacher: " + question.TeacherId)
			m.AddPage()
		} else {
			err = buildAnswerSection(m, question.Answer)
			if err != nil {
				fmt.Println(err)
			}

		}
	}

	// send remaining output and accumulated errors
	if err != nil {
		output, _ := m.Output()
		return output, err
	}

	return m.Output()
}

// buildFooter adds page number to the bottom of the page
func buildFooter(m pdf.Maroto) {
	m.RegisterFooter(func() {
		m.Row(footerHeight, func() {
			m.Text(strconv.Itoa(m.GetCurrentPage()))
		})
	})
}

// buildQuestionSection puts the category icons, the question text and a dividing line on the page
func buildQuestionSection(m pdf.Maroto, question *data.SessionQuestion) {
	base64Image, err := getImageAsBase64("Icon/" + question.Category + ".png")

	m.Row(space.Points(15), func() {
		if err == nil { // Only render icon if there is one for the category
			m.Col(12, func() {
				m.Base64Image(base64Image, consts.Png, props.Rect{
					Center:  true,
					Percent: 80,
				})
			})
		}
	})
	m.Row(space.Percent(0.2), func() {
		m.Col(12, func() {
			m.Text(question.QuestionText, props.Text{
				Top:             2,
				Size:            13,
				Color:           color.NewBlack(),
				Family:          consts.Helvetica,
				Style:           consts.Normal,
				Align:           consts.Left,
				VerticalPadding: 4,
			})
		})

	})
	m.Line(1.0)
}

func buildAnswerSection(m pdf.Maroto, answerFilePath string) error {
	var err1 error = nil
	m.Row(space.Percent(0.7), func() {
		m.Col(12, func() {
			err := m.FileImage(answerFilePath, props.Rect{
				Center: true,
			})
			if err != nil {
				err1 = err1
			}

		})
	})
	return err1
}

func buildIntroPage(m pdf.Maroto) {
	m.Row(space.Percent(0.2), func() {
		m.Col(12, func() {
			m.Text("Expo-Companion", props.Text{
				Style: consts.Bold,
				Align: consts.Center,
				Size:  16,
			})
		})
	})
	m.Row(space.Percent(0.7), func() {
		m.Col(12, func() {
			m.Text(IntroText, props.Text{
				Left:            30,
				Right:           30,
				Size:            12,
				Align:           consts.Center,
				VerticalPadding: 4,
			})
		})
	})
	space.Reset()
}

func getImageAsBase64(filename string) (string, error) {
	file, err := images.Fs.ReadFile(filename)
	if err != nil {
		return "", err
	}
	return byteToString(file), nil
}

func byteToString(b []byte) string {
	return base64.StdEncoding.EncodeToString(b)
}
