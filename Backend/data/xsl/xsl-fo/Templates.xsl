<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">

    <xsl:template name="TAuthor">
        <xsl:param name="author"/>
        <fo:block>
            <fo:block><xsl:value-of select="$author/sc:name/sc:first-name"/><xsl:text> </xsl:text><xsl:for-each select="$author/sc:name/sc:middle-name"><xsl:value-of select="."/><xsl:text> </xsl:text></xsl:for-each><xsl:value-of select="$author/sc:name/sc:last-name"/></fo:block>
            <fo:block><xsl:value-of select="$author/sc:affiliation/sc:university"/></fo:block>
            <fo:block><xsl:value-of select="$author/sc:affiliation/sc:city"/>, <xsl:value-of select="$author/sc:affiliation/sc:state"/>, <xsl:value-of select="$author/sc:affiliation/sc:country"/></fo:block>
            <fo:block><xsl:value-of select="$author/sc:email"/></fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template name="TComment">
        <xsl:param name="comment"/>
        <fo:block space-before="10px">
            <fo:block><xsl:value-of select="$comment/sc:review-reference"/></fo:block>
            <fo:block><xsl:value-of select="$comment/sc:comment-text"/></fo:block>
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:question" name="TQuestion">
        <xsl:if test="@type = 'text'">
            <xsl:for-each select="./*">
                <fo:block space-after="10px">
                    <xsl:if test="name(.) = 'question-text'">
                        <fo:block><fo:inline font-weight="bold"><xsl:value-of select="."/></fo:inline></fo:block>
                    </xsl:if>
                    <xsl:if test="name(.) = 'answer'">
                        <fo:block space-before="5px"><xsl:value-of select="."/></fo:block>
                    </xsl:if>
                </fo:block>
            </xsl:for-each>
        </xsl:if>

        <xsl:if test="@type = 'multiple-choice'">
            <xsl:for-each select="./*">
                <fo:block space-after="10px">
                    <xsl:if test="name(.) = 'question-text'">
                        <fo:block><fo:inline font-weight="bold"><xsl:value-of select="."/></fo:inline></fo:block>
                    </xsl:if>
                    <xsl:if test="name(.) = 'answer'">
                        <xsl:choose>
                            <xsl:when test="@selected = 'true'">
                                <fo:block space-before="5px"><xsl:value-of select="."/>: True</fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block space-before="5px"><xsl:value-of select="."/>: False</fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:if>
                </fo:block>
            </xsl:for-each>
        </xsl:if>
    </xsl:template>

    <xsl:template match="sc:paragraph" name="TParagraph">
        <xsl:for-each select="./*">
            <xsl:if test="name(.) = 'quote'">
                <fo:inline>
                    <xsl:apply-templates/>
                </fo:inline>
            </xsl:if>

            <xsl:if test="name(.) = 'list'">
                <xsl:choose>
                    <xsl:when test="@ordered='true'">
                        <fo:list-block>
                            <xsl:for-each select="./sc:list-item">
                                <fo:list-item>
                                    <fo:list-item-label>
                                        <fo:block>
                                            <xsl:number value="position()" format="1"/>
                                        </fo:block>
                                    </fo:list-item-label>
                                    <fo:list-item-body>
                                        <fo:block>
                                            <xsl:value-of select="."/>
                                        </fo:block>
                                    </fo:list-item-body>
                                </fo:list-item>
                            </xsl:for-each>
                        </fo:list-block>
                    </xsl:when>
                    <xsl:otherwise>
                        <fo:list-block>
                            <xsl:for-each select="./sc:list-item">
                                <fo:list-item>
                                    <fo:list-item-label>
                                        <fo:block>
                                            (*-*')
                                        </fo:block>
                                    </fo:list-item-label>
                                    <fo:list-item-body>
                                        <fo:block>
                                            <xsl:value-of select="."/>
                                        </fo:block>
                                    </fo:list-item-body>
                                </fo:list-item>
                            </xsl:for-each>
                        </fo:list-block>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:if>
            <xsl:if test="name(.) ='figure'">
                <fo:block margin="5px" space-after="10px">
                    <xsl:for-each select="./sc:image">
                        <fo:external-graphic>
                            <xsl:attribute name="src">
                                data:image/*;base64,<xsl:value-of select="."/>
                            </xsl:attribute>
                        </fo:external-graphic>
                    </xsl:for-each>
                </fo:block>
            </xsl:if>
            <xsl:if test="name(.) ='table'">
                <fo:block margin="5px">
                    <fo:block text-align="center" font-weight="bold">
                        <xsl:value-of select="@title"/>
                    </fo:block>
                    <fo:table>
                        <fo:table-header>
                            <xsl:for-each select="./sc:header/sc:row">
                                <fo:table-row>
                                    <xsl:for-each select="./sc:column">
                                        <fo:table-cell border="1px solid black" padding="2px 2px 2px 5px">
                                            <fo:block font-weight="bold">
                                                <xsl:value-of select="."/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </xsl:for-each>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-header>
                        <fo:table-body>
                            <xsl:for-each select="sc:body/sc:row">
                                <fo:table-row>
                                    <xsl:for-each select="./sc:column">
                                        <fo:table-cell border="1px solid black" padding="2px">
                                            <fo:block>
                                                <xsl:value-of select="."/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </xsl:for-each>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
            </xsl:if>
            <xsl:if test="name(.) = 'decorator'">
                <xsl:apply-templates select="."/>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="sc:quote/sc:quote-text">"<xsl:value-of select="."/>"</xsl:template>

    <xsl:template match="sc:publication">
        <xsl:text> </xsl:text><xsl:value-of select="sc:title" />, <xsl:value-of select="sc:publisher" />, <xsl:value-of select="sc:place" />
    </xsl:template>

    <xsl:template match="sc:bold">
        <fo:inline font-weight="bold">
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."/>
            </xsl:for-each>
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:italic">
        <fo:inline font-style="italic">
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."/>
            </xsl:for-each>
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:underline">
        <fo:inline text-decoration="underline">
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."/>
            </xsl:for-each>
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:bold/text()">
        <fo:inline>
            <xsl:copy-of select="." />
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:italic/text()">
        <fo:inline>
            <xsl:copy-of select="." />
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:underline/text()">
        <fo:inline>
            <xsl:copy-of select="." />
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:paragraph/text()">
        <fo:inline>
            <xsl:copy-of select="." />
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:figure/text()">
        <fo:inline>
            <xsl:copy-of select="." />
        </fo:inline>
    </xsl:template>

    <xsl:template match="sc:quote/text()">
        <fo:inline>
            <xsl:copy-of select="." />
        </fo:inline>
    </xsl:template>


    <xsl:template match="sc:decorator/text()">
        <fo:inline>
            <xsl:copy-of select="." />
        </fo:inline>
    </xsl:template>





</xsl:stylesheet>