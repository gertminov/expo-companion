package util

import (
	"github.com/johnfercher/maroto/pkg/pdf"
)

type VerticalSpace struct {
	maxSpace  float64
	restSpace float64
}

func NewVerticalSpace(m pdf.Maroto) VerticalSpace {
	height := calcAvailHeight(m)
	space := VerticalSpace{
		maxSpace:  height,
		restSpace: height,
	}

	return space

}

func (s VerticalSpace) Reset() {
	s.restSpace = s.maxSpace
}

func (s VerticalSpace) Shorten(amt float64) {
	s.maxSpace -= amt
}

func (s VerticalSpace) Points(amt float64) float64 {
	s.restSpace -= amt
	return amt
}

func (s VerticalSpace) Percent(amt float64) float64 {
	percentInPoints := s.maxSpace * amt
	s.restSpace -= percentInPoints
	if s.restSpace < 0 {
		return 0
	}
	return percentInPoints
}

func (s VerticalSpace) Fill() float64 {
	rest := s.restSpace
	s.restSpace = 0
	return rest
}

func calcAvailHeight(m pdf.Maroto) float64 {
	_, height := m.GetPageSize()
	_, top, _, bottom := m.GetPageMargins()
	availHeight := height - (top + bottom) - 3 // -3 for safety
	return availHeight
}
