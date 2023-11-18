# Backend
This is the backend for the Expo-Companion.  
It is based on [pocketbase](https://pocketbase.io/).

## Prerequisites
- [GoLang](https://go.dev/) > 1.18

## Run
If there is an executable in [/bin](./bin) you can just type `./pocketbase serve` to start the server.  
If not, you have to ...

## Compile

````shell
go build -o ./bin/pocketbase.exe backend/cmd/main
````
to compile and run in one command
````shell
go build -o ./bin/pocketbase.exe backend/cmd/main | ./bin/pocketbase.exe serve
````

 ### compile for Linux
````pwsh
$Env:GOOS="linux"; go build -o ./bin/pocketbase backend/cmd/main
````

# Modifications

## Added Routes 
(click on link for swagger docs)

[/api/questions](./docs/questions.yaml)  
    - [example](./docs/questions.http)


# Mail
You can customize the mail that is sent to the teachers, by modifying the 
`Default "Verification" email template` in pocketbase (found under Settings/Mail settings).
The PDF with the quesitons and answers will be attached automatically.
The Framework forces you to set the {ACTION_URL} token, don't worry, 
it won't show up in the mail as long as there is just {TOKEN} in the action url field

