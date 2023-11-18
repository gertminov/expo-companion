package main

import (
	"backend/cmd/routes"
	"github.com/pocketbase/pocketbase"
	"github.com/pocketbase/pocketbase/core"
	"log"
)

// Tutorial vom https://github.com/pocketbase/pocketbase
func main() {
	// get an instance of pocketbase
	app := pocketbase.New()

	//add a custom route
	app.OnBeforeServe().Add(func(e *core.ServeEvent) error {
		return routes.RegisterGenerateQuestionsRoute(e, app)
	})

	app.OnBeforeServe().Add(func(e *core.ServeEvent) error {
		return routes.RegisterSendEmailRoute(e, app)
	})

	//Start the App
	if err := app.Start(); err != nil {
		log.Fatal(err)
	}
}
