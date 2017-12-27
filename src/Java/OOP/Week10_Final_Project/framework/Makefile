SKRIPTE=../stylesheets/
filename=$(shell (basename $(CURDIR)))

all: ${filename}.pdf


${filename}.pdf: skript.pdf
	cp skript.pdf ${filename}.pdf

skript.pdf: skript.tex
	xelatex skript.tex
	-bibtex skript
	makeindex Klassen 
	xelatex skript
	xelatex skript

skript.tex: skript.xml ${SKRIPTE}commonPrintTagsForLatex.xsl  ${SKRIPTE}lectureToLatex.xsl
	xsltproc -o skript.tex ${SKRIPTE}lectureToLatex.xsl skript.xml


clean:
	-rm skript.aux
	-rm skript.blg
	-rm skript.pdf
	-rm skript.bbl
	-rm skript.log
	-rm skript.tex
	-rm Klassen.*
	-rm *~
