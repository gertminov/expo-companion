package images

import "embed"

//This file is used to embend the images in the Icon directory into the executable for use in the pdf

var (
	//go:embed Icon
	Fs embed.FS
)
