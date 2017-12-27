<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="b">{\normalfont \bfseries  <xsl:apply-templates></xsl:apply-templates>}</xsl:template>
	<xsl:template match="em">{\em <xsl:apply-templates></xsl:apply-templates>}</xsl:template>
	<xsl:template match="footnote/tt">{\normalfont \ttfamily <xsl:apply-templates></xsl:apply-templates>}</xsl:template>
	<xsl:template match="title/tt">{\normalfont \ttfamily <xsl:apply-templates></xsl:apply-templates>}</xsl:template>
	<xsl:template match="tt">\verb+<xsl:apply-templates></xsl:apply-templates>+</xsl:template>

	<xsl:template match="itemize">\begin{itemize}
	 <xsl:apply-templates></xsl:apply-templates>
	 \end{itemize}
	 </xsl:template>

	<xsl:template match="item">\item <xsl:apply-templates></xsl:apply-templates>
    </xsl:template>	

	<xsl:template match="code"> 
	\bigskip
	
%\begin{small}
<xsl:if test="@class">\index{Klassen}{<xsl:value-of select="@class"/>}</xsl:if>
\fvset{xleftmargin=0.4cm}
\begin{lstlisting}[frame=single,language=Java<xsl:if test="@class">,caption=<xsl:value-of select="@class"/><xsl:if test="@lang"
>.<xsl:value-of select="@lang"/></xsl:if></xsl:if><xsl:if test="@class">,name=<xsl:value-of select="@class"/><xsl:if test="@lang"
>.<xsl:value-of select="@lang"/></xsl:if></xsl:if>]
<xsl:apply-templates></xsl:apply-templates>\end{lstlisting}

<!-- \begin{Verbatim}[fontfamily=courier,frame=single,numbersep=3pt,numbers=left
  <xsl:if test="@sequel">,firstnumber=last</xsl:if>
<xsl:if test="@class">,label=<xsl:value-of select="@class"/><xsl:if test="@lang"
>.<xsl:value-of select="@lang"/></xsl:if></xsl:if>]-->
%\end{Verbatim}
%\end{small}
	</xsl:template>



	<xsl:template match="scode">
\begin{scriptsize}\begin{verbatim}
<xsl:apply-templates></xsl:apply-templates>
\end{verbatim}
\end{scriptsize}
	</xsl:template>
	

	<xsl:template match="dontshow"></xsl:template>

	<xsl:template match="ref">\ref{<xsl:value-of select="./@name"/>}</xsl:template>
	<xsl:template match="label">\label{<xsl:value-of select="./@name"/>}</xsl:template>

	<xsl:template match="footnote">\footnote{<xsl:apply-templates></xsl:apply-templates>}</xsl:template>

	<xsl:template match="wichtig">\begin{center}\colorbox{lightgray}{\begin{minipage}{0.9\textwidth}%
 <xsl:apply-templates></xsl:apply-templates>\end{minipage}}\end{center}</xsl:template>

<xsl:template match="definition">\begin{definition}
 <xsl:apply-templates></xsl:apply-templates>
 \end{definition}</xsl:template>


<xsl:template match="beispiel">\begin{example}
 <xsl:apply-templates></xsl:apply-templates>
 \end{example}</xsl:template>



  <xsl:template match="eqbr">\nonumber\\&amp;&amp;</xsl:template>
  <xsl:template match="dots">\dots</xsl:template>
  <xsl:template match="white">~</xsl:template>
  <xsl:template match="w">~</xsl:template>
  <xsl:template match="rpar">\}</xsl:template>
  <xsl:template match="pi">\pi</xsl:template>
  <xsl:template match="alpha">\alpha</xsl:template>
  <xsl:template match="beta">\beta</xsl:template>
  <xsl:template match="lpar">\{</xsl:template>
  <xsl:template match="otimes">\otimes</xsl:template>
  <xsl:template match="oplus">\oplus</xsl:template>
  <xsl:template match="quot">"$~$</xsl:template>
  <xsl:template match="rightarrow">$\rightarrow$</xsl:template>
  <xsl:template match="setN">$\mathds{N}$</xsl:template>
  <xsl:template match="setZ">$\mathds{Z}$</xsl:template> 
  <xsl:template match="setQ">$\mathds{Q}$</xsl:template> 
  <xsl:template match="setR">$\mathds{R}$</xsl:template> 
  <xsl:template match="setC">$\mathds{C}$</xsl:template>
  <xsl:template match="subset">$\subset$</xsl:template> 

<xsl:template match="grammar">\begin{eqnarray*}<xsl:apply-templates/>\end{eqnarray*}
</xsl:template>

<xsl:template match="rule">
<xsl:apply-templates select="lhs"/> &amp; \rightarrow  &amp;<xsl:apply-templates select="rhs"/>\\</xsl:template>

<xsl:template match="lhs">  \mbox{\normalfont \slshape   <xsl:apply-templates/>}</xsl:template>

<xsl:template match="nt">  \mbox{\normalfont \slshape   <xsl:apply-templates/>}</xsl:template>

<xsl:template match="rhs">\begin{minipage}{0.7\textwidth}<xsl:apply-templates/>\end{minipage}</xsl:template>
  
<xsl:template match="term"> \mbox{\normalfont \ttfamily <xsl:apply-templates/>}</xsl:template>

<xsl:template match="alts"><xsl:for-each select="*"><xsl:choose><xsl:when test="position() != 1">|</xsl:when></xsl:choose><xsl:apply-templates select="."/></xsl:for-each></xsl:template>

<xsl:template match="par">(<xsl:apply-templates/>)</xsl:template>

<xsl:template match="rep0">  (<xsl:apply-templates/>)*</xsl:template>

<!-- Pure HTML Templates -->
	<xsl:template match="a">
		<xsl:value-of select="."/>\footnote{\url{<xsl:value-of select="./@href"/>}}
	</xsl:template>  	

	<xsl:template match="br">\\
	
	</xsl:template>
	
	<xsl:template match="strong">
	  \textbf{<xsl:apply-templates></xsl:apply-templates>}
	</xsl:template>	
	
	<xsl:template match="p"><xsl:apply-templates></xsl:apply-templates>
	
	</xsl:template>	
	<xsl:template match="m">$<xsl:apply-templates></xsl:apply-templates>$
	</xsl:template>	
	
	<xsl:template match="center">
	  \begin{center}<xsl:apply-templates></xsl:apply-templates>\end{center}
	</xsl:template>
		
	<xsl:template match="ul">\begin{itemize}
	 <xsl:apply-templates></xsl:apply-templates>
	 \end{itemize}
	 </xsl:template>

	<xsl:template match="ol">\begin{enumeration}
	 <xsl:apply-templates></xsl:apply-templates>
	 \end{enumeration}
	 </xsl:template>

	<xsl:template match="li">\item <xsl:apply-templates></xsl:apply-templates>
    </xsl:template>	
    
<xsl:template match="dl">
	<xsl:text>\begin{description}</xsl:text>
	<xsl:for-each select="*">
		<xsl:if test="local-name()='dt'">
			<xsl:text>\item[</xsl:text>
		</xsl:if>
		<xsl:apply-templates />
		<xsl:if test="local-name()='dt'">
			<xsl:text>] </xsl:text>
			<xsl:choose><xsl:when test="dd"><xsl:value-of select="dd"/></xsl:when></xsl:choose>
		</xsl:if>
	</xsl:for-each>
	<xsl:text>\end{description}</xsl:text>
</xsl:template>    
	
<xsl:template match="table">
	<xsl:text>\begin{center}</xsl:text>
	<xsl:text>\begin{tabular}{|</xsl:text>
	<xsl:for-each select="tr[1]/*">
		<xsl:text>c|</xsl:text>
	</xsl:for-each>
	<xsl:text>}
</xsl:text>
	<xsl:for-each select="tr">
		<xsl:text>\hline
</xsl:text>
		<xsl:for-each select="*">
			<xsl:if test="name()='th'">
				{\normalfont \bfseries
			</xsl:if>
			<xsl:apply-templates />
			<xsl:if test="name()='th'">
				}
			</xsl:if>
			<xsl:if test="position() != last()">
				<xsl:text><![CDATA[ & ]]></xsl:text>
			</xsl:if>
		</xsl:for-each>
		<xsl:text> \\
</xsl:text>
	</xsl:for-each>
	<xsl:text>\hline
</xsl:text>
	
	<xsl:text>\end{tabular}
</xsl:text>
<xsl:choose><xsl:when test="./@caption">\\ \textbf{<xsl:value-of select="./@caption"/>}</xsl:when></xsl:choose>
	<xsl:text>\end{center}
</xsl:text>
</xsl:template>

<xsl:template match="solution[@private]"></xsl:template>

<xsl:template match="solution[@public]">
\paragraph*{Lösung}
<xsl:apply-templates></xsl:apply-templates>
</xsl:template>

	
<xsl:template match="link">
  <xsl:apply-templates select="description"/> ({\normalfont \ttfamily <xsl:apply-templates select="address"/>})
</xsl:template>

<xsl:template match="zitat">
\hspace*{0.45\textwidth}\begin{minipage}{0.55\textwidth}<xsl:apply-templates></xsl:apply-templates>\end{minipage}

</xsl:template>

<xsl:template match="zitat/text"><xsl:apply-templates></xsl:apply-templates></xsl:template>

<xsl:template match="quelle">
\\
\hspace*{\fill}{\em <xsl:apply-templates></xsl:apply-templates>}
</xsl:template>


<xsl:template match="stable">
\begin{tabular}{<xsl:value-of select="@layout"/>}
<xsl:apply-templates/>
\end{tabular}

</xsl:template>


<xsl:template match="zeile">
<xsl:apply-templates/>\\
</xsl:template>

<xsl:template match="zelle[position()=last()]">
<xsl:apply-templates/>
</xsl:template>

<xsl:template match="zelle">
<xsl:apply-templates/>&amp;
</xsl:template>

<xsl:template match="hline">\hline
</xsl:template>

<xsl:template match="importSource">
  \includecode{<xsl:value-of select="@file"/>}
</xsl:template>

<xsl:template match="parts|unteraufgaben">
\begin{description}
<xsl:apply-templates/>
\end{description}
</xsl:template> 

<xsl:template match="pparts">
\begin{tabular}{p{0.5\textwidth}p{0.5\textwidth}}
<xsl:apply-templates/>
\end{tabular}
</xsl:template>

<xsl:template match="part|teil">
\item[{\normalfont \bfseries \alph{unteraufgabe})}] \addtocounter{unteraufgabe}{1}%
<xsl:apply-templates/>
</xsl:template>

<xsl:template match="partline">
\begin{description}
<xsl:apply-templates/>
\end{description}
</xsl:template>

<xsl:template match="ppart">
\item[{\normalfont \bfseries \alph{unteraufgabe})}] \addtocounter{unteraufgabe}{1}%
<xsl:apply-templates/>
</xsl:template>

<xsl:template match="exercise|aufgabe">
\paragraph{\mbox{Aufgabe~\arabic{aufgabe}}} 
\addtocounter{aufgabe}{1}\setcounter{unteraufgabe}{1}
<xsl:apply-templates/>
</xsl:template>


</xsl:stylesheet>
