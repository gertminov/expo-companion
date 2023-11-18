package data

import "github.com/labstack/echo/v5"

type Teacher struct {
	Id       string `json:"id"`
	EMail    string `json:"email"`
	FromJson func(c echo.Context) Teacher
}

func NewTeacher() Teacher {
	return Teacher{
		FromJson: fromJson,
	}
}

// FromJson creates a new Teacher struct from a json body like: {id: "string", email: "emailadress"}
func fromJson(c echo.Context) Teacher {
	var teacher Teacher
	c.Bind(&teacher)
	return teacher
}
