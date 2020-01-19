<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">

    <xsl:template name="TAuthor">
        <xsl:param name="author"/>
        <fo:block>
            <fo:inline><xsl:value-of select="$author/sc:name/sc:first-name"/> <xsl:for-each select="$author/sc:name/sc:middle-name"><xsl:value-of select="."/> </xsl:for-each></fo:inline>
            <fo:inline><xsl:value-of select="$author/sc:name/sc:last-name"/></fo:inline>
            <fo:inline><xsl:value-of select="$author/sc:affiliation/sc:university"/></fo:inline>
            <fo:inline><xsl:value-of select="$author/sc:affiliation/sc:city"/>, <xsl:value-of select="$author/sc:affiliation/sc:state"/>, <xsl:value-of select="$author/sc:affiliation/sc:country"/></fo:inline>
            <fo:inline><xsl:value-of select="$author/sc:email"/></fo:inline>
        </fo:block>
    </xsl:template>

    <xsl:template name="TComment">
        <xsl:param name="comment"/>
        <fo:block>
            <fo:inline><xsl:value-of select="$comment/sc:review-reference"/></fo:inline>
            <fo:inline><xsl:value-of select="$comment/sc:comment-text"/></fo:inline>
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:question" name="TQuestion">
        <xsl:if test="@type = 'text'">
            <xsl:for-each select="./*">
                <fo:block>
                    <xsl:if test="name(.) = 'question-text'">
                        <fo:inline><xsl:value-of select="."/></fo:inline>
                    </xsl:if>
                    <xsl:if test="name(.) = 'answer'">
                        <fo:inline><xsl:value-of select="."/></fo:inline>
                    </xsl:if>
                </fo:block>
            </xsl:for-each>
        </xsl:if>

        <xsl:if test="@type = 'multiple-choice'">
            <xsl:for-each select="./*">
                <fo:block>
                    <xsl:if test="name(.) = 'question-text'">
                        <fo:inline><xsl:value-of select="."/></fo:inline>
                    </xsl:if>
                    <xsl:if test="name(.) = 'answer'">
                        <xsl:choose>
                            <xsl:when test="@selected = 'true'">
                                <fo:inline><xsl:value-of select="."/>: True</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline><xsl:value-of select="."/>: False</fo:inline>
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
                <fo:block>
                    <xsl:apply-templates/>
                </fo:block>
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
                <fo:block>
                    <xsl:for-each select="./sc:image">
                        <fo:block>
                            <fo:external-graphic>
                                <xsl:attribute name="src">
                                    data:image/*;base64,<xsl:value-of select="."/>
                                </xsl:attribute>
                            </fo:external-graphic>
                        </fo:block>
                    </xsl:for-each>
                </fo:block>
            </xsl:if>
            <xsl:if test="name(.) ='table'">
                <fo:table-and-caption>
                    <fo:table-caption>
                        <fo:block>
                            <xsl:value-of select="@title"/>
                        </fo:block>
                    </fo:table-caption>
                    <fo:table>
                        <fo:table-header>
                            <xsl:for-each select="sc:header/sc:row">
                                <fo:table-row>
                                    <xsl:for-each select="./sc:column">
                                        <fo:table-cell border="1px solid black">
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
                                        <fo:table-cell border="1px solid black">
                                            <fo:block>
                                                <xsl:value-of select="."/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </xsl:for-each>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:table-and-caption>
            </xsl:if>
            <xsl:if test="name(.) = 'decorator'">
                <xsl:apply-templates select="."/>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="sc:quote/sc:quote-text">
        <fo:block>
            "<xsl:value-of select="."/>"
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:publication">
        <fo:block>
            <xsl:value-of select="sc:title" />, <xsl:value-of select="sc:publisher" />, <xsl:value-of select="sc:place" />
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:bold">
        <fo:block font-weight="bold">
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."></xsl:apply-templates>
            </xsl:for-each>
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:italic">
        <fo:block font-style="italic">
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."></xsl:apply-templates>
            </xsl:for-each>
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:underline">
        <fo:block font-decoration="underline">
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."></xsl:apply-templates>
            </xsl:for-each>
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:bold/text()">
        <fo:block>
            <xsl:copy-of select="." />
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:italic/text()">
        <fo:block>
            <xsl:copy-of select="." />
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:underline/text()">
        <fo:block>
            <xsl:copy-of select="." />
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:paragraph/text()">
        <fo:block>
            <xsl:copy-of select="." />
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:figure/text()">
        <fo:block>
            <xsl:copy-of select="." />
        </fo:block>
    </xsl:template>

    <xsl:template match="sc:quote/text()">
        <fo:block>
            <xsl:copy-of select="." />
        </fo:block>
    </xsl:template>


    <xsl:template match="sc:decorator/text()">
        <fo:block>
            <xsl:copy-of select="." />
        </fo:block>
    </xsl:template>





</xsl:stylesheet>