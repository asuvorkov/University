<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="text" indent="yes"/>
	<xsl:param name="lectureId"/>
	<xsl:param name="lectureTitle"/>	
	<xsl:param name="lectureImagePath"/>
	
	<xsl:template match="/">
\documentclass[12pt,a4paper,headsepline,chapterprefix,pointlessnumbers,noonelinecaption,BCOR20mm,DIV14]{scrartcl}
\usepackage[ngerman]{babel}
\usepackage{amsmath}
\usepackage{url}
\usepackage{graphicx}
\usepackage{amssymb}
\usepackage{amsmath}
%\usepackage{qtree}
\usepackage{enumerate}
\usepackage{listings}

\newcommand{\JavaDoc}{\textsc{JavaDoc}}
\newcommand{\html}{\textsc{html}}

\newcommand{\impl}{\Rightarrow}
\newcommand{\lequiv}{\Leftrightarrow}

\newcommand{\simp}{\rightarrow}
\newcommand{\sequiv}{\leftrightarrow}

\newtheorem{theorem}{Satz}[section]
\newtheorem{lemma}[theorem]{Lemma} 
\newtheorem{satz}[theorem]{Satz} 
\newtheorem{example}[theorem]{Beispiel} 
\newtheorem{proof}[theorem]{Beweis} 
\newtheorem{definition}[theorem]{Definition \rm} 
\newtheorem{remark}[theorem]{Bemerkung} 
\newtheorem{proposition}[theorem]{Aussage}
\newtheorem{exercise}[theorem]{\"Ubungsaufgabe \rm}
\newtheorem{corollary}[theorem]{Korollar}

\usepackage{fancyvrb}
\usepackage{xcolor}

\usepackage{longtable}

%\usepackage{lmodern}
%\usepackage{hyperref}
\usepackage{latexsym}

%\usepackage[encapsulated]{CJK}	% Chinese/Japanese/Korean
%\newcommand{\cntext}[1]{\begin{CJK*}{UTF8}{gbsn}\mbox{#1}\end{CJK*}} 

\setlength\parindent{0pt}
\setlength{\parskip}{5pt plus 2pt minus 1pt}

\sloppy

\title{<xsl:apply-templates select="lecture/title"></xsl:apply-templates>}
\author{<xsl:apply-templates select="lecture/docent"></xsl:apply-templates>}
\date{\today}

%\pdfinfo{%
%  /Title    (<xsl:apply-templates select="lecture/title"></xsl:apply-templates>)
%  /Author   (<xsl:apply-templates select="lecture/docent"></xsl:apply-templates>)
%  /Creator  (LectureNotes Teaching System)
%  /Producer (LectureNotes Teaching System)
%  /Subject  ()
%  /Keywords ()
%}

\usepackage{xltxtra}

\setmainfont{FlamaBook} 

\usepackage{multind}
\makeindex{Klassen}

\usepackage{fancyhdr}
\pagestyle{fancy}

\usepackage{fancyvrb }





\usepackage{color}

\definecolor{mygreen}{rgb}{0,0.6,0}
\definecolor{mygray}{rgb}{0.5,0.5,0.5}
\definecolor{mymauve}{rgb}{0.58,0,0.82}

\newcounter{unteraufgabe}
\setcounter{unteraufgabe}{1}

\newcounter{aufgabe}
\setcounter{aufgabe}{0}


\lstset{ %
  backgroundcolor=\color{white},   % choose the background color; you must add \usepackage{color} or \usepackage{xcolor}
  basicstyle=\footnotesize,        % the size of the fonts that are used for the code
  breakatwhitespace=false,         % sets if automatic breaks should only happen at whitespace
  breaklines=true,                 % sets automatic line breaking
  captionpos=b,                    % sets the caption-position to bottom
  commentstyle=\color{mygreen},    % comment style
  deletekeywords={...},            % if you want to delete keywords from the given language
  escapeinside={\%*}{*)},          % if you want to add LaTeX within your code
  extendedchars=true,              % lets you use non-ASCII characters; for 8-bits encodings only, does not work with UTF-8
  frame=single,                    % adds a frame around the code
  keepspaces=true,                 % keeps spaces in text, useful for keeping indentation of code (possibly needs columns=flexible)
  keywordstyle=\color{blue},       % keyword style
  language=Java,                   % the language of the code
  morekeywords={*,...},            % if you want to add more keywords to the set
  numbers=left,                    % where to put the line-numbers; possible values are (none, left, right)
  numbersep=5pt,                   % how far the line-numbers are from the code
  numberstyle=\tiny\color{mygray}, % the style that is used for the line-numbers
  rulecolor=\color{black},         % if not set, the frame-color may be changed on line-breaks within not-black text (e.g. comments (green here))
  showspaces=false,                % show spaces everywhere adding particular underscores; it overrides 'showstringspaces'
  showstringspaces=false,          % underline spaces within strings only
  showtabs=false,                  % show tabs within strings adding particular underscores
  stepnumber=1,                    % the step between two line-numbers. If it's 1, each line will be numbered
  stringstyle=\color{mymauve},     % string literal style
  tabsize=2,                       % sets default tabsize to 2 spaces
  title=\lstname                   % show the filename of files included with \lstinputlisting; also try caption instead of title
}


\newcommand{\includecode}[2][c]{\lstinputlisting[caption=#2, escapechar=]{#2}<!---->}
\begin{document}


<xsl:apply-templates select="blatt|lecture" />

\end{document}

	</xsl:template>

	<xsl:template match="lecture">
\begin{titlepage}
\begin{center}
{\Huge 
<xsl:apply-templates select="title"></xsl:apply-templates>

}

\vfill

{\Large 
<xsl:apply-templates select="subtitle"></xsl:apply-templates>

}

\vfill
{\Large <xsl:apply-templates select="docent"></xsl:apply-templates>
}

{\large \bf Hochschule Rhein-Main}

%{\bf Version <xsl:apply-templates select="lecture/version"></xsl:apply-templates>
%}

%{\bf Generiert von LectureNotes Teaching System \\ \today}

\vfill
\begin{quote} 
<xsl:apply-templates select="preface"></xsl:apply-templates>

\end{quote}


\end{center}
\end{titlepage}

<xsl:apply-templates select="content"></xsl:apply-templates>
</xsl:template>
	

	<xsl:template match="blatt">
\renewcommand{\footrulewidth}{0.4pt}

\cfoot{(Seite \thepage)}
\begin{center}{\Huge \bf Übungsblatt <xsl:value-of select="@nr" />}\\\end{center}
\begin{center}{\large \bf (<xsl:value-of select="@ausgabe" />)}\\\end{center}
\lfoot{<xsl:value-of select="@ausgabe" />}\rfoot{\"Ubungsblatt  <xsl:value-of select="@nr"/>}
      \lhead{<xsl:value-of select="@vorlesung" />}\rhead{Sven Eric Panitz}



	  <xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="*/paragraph/title">
\section{<xsl:apply-templates></xsl:apply-templates>}
	</xsl:template>

	<xsl:template match="*/paragraph/paragraph/title">
\subsection{<xsl:apply-templates></xsl:apply-templates>}
	</xsl:template>

	<xsl:template match="*/paragraph/paragraph/paragraph/title">
\subsubsection{<xsl:apply-templates></xsl:apply-templates>}
	</xsl:template>
	<xsl:template match="*/paragraph/paragraph/paragraph/paragraph/title">
\paragraph{<xsl:apply-templates></xsl:apply-templates>}
	</xsl:template>
	<xsl:template match="*/paragraph/paragraph/paragraph/paragraph/paragraph/title">
{\bf <xsl:apply-templates></xsl:apply-templates>}\\
	</xsl:template>
	
	<xsl:template match="*/exercises">
\begin{description}
<xsl:apply-templates></xsl:apply-templates>
\end{description}
	</xsl:template>	
	
<!--	<xsl:template match="*/exercise">
\item[Aufgabe <xsl:apply-templates select="position"></xsl:apply-templates>]
	 <xsl:apply-templates select="content"></xsl:apply-templates>
	</xsl:template>				
-->	
	<xsl:template match="bild">
		<xsl:choose><xsl:when test="./@float">\begin{figure}[!htb]</xsl:when></xsl:choose>
		\centerline{\includegraphics[scale=<xsl:value-of select="./@pdfscale"/>]{images/<xsl:value-of select="./@name"/>}}
		<xsl:choose><xsl:when test="./@caption">\caption{<xsl:value-of select="./@caption"/>}</xsl:when></xsl:choose>
		\label{<xsl:value-of select="./@label"/>}
		<xsl:choose><xsl:when test="./@float">\end{figure}</xsl:when></xsl:choose>
	</xsl:template>
	
	<xsl:template match="img">
		<xsl:choose><xsl:when test="./@float">\begin{figure}[!htb]</xsl:when></xsl:choose>
		\centerline{\includegraphics[<xsl:value-of select="./@latexParam"/>]{<xsl:value-of select="$lectureImagePath"/><xsl:value-of select="./@src"/>}}
		<xsl:choose><xsl:when test="./@caption">\caption{<xsl:value-of select="./@caption"/>}</xsl:when></xsl:choose>
		\label{<xsl:value-of select="./@label"/>}
		<xsl:choose><xsl:when test="./@float">\end{figure}</xsl:when></xsl:choose>
	</xsl:template>	

	<!-- Include common tags for latex output -->
    <xsl:include href="commonPrintTagsForLatex.xsl" />	

</xsl:stylesheet>
