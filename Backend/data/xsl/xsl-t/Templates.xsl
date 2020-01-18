<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">

    <xsl:template name="TAuthor">
        <xsl:param name="author"/>
        <div>
            <xsl:value-of select="$author/sc:name/sc:first-name"/>&#160;<xsl:value-of select="$author/sc:name/sc:last-name"/><br/>
            <xsl:value-of select="$author/sc:affiliation/sc:university"/><br/>
            <xsl:value-of select="$author/sc:affiliation/sc:city"/>, <xsl:value-of select="$author/sc:affiliation/sc:state"/>,<xsl:value-of select="$author/sc:affiliation/sc:country"/><br/>
            <xsl:value-of select="$author/sc:email"/>
        </div>
    </xsl:template>

    <xsl:template name="TComment">
        <xsl:param name="comment"/>
        <div>
            <xsl:value-of select="$comment/sc:review-reference"/>
            <xsl:value-of select="$comment/sc:comment-text"/>
        </div>
    </xsl:template>

    <xsl:template match="sc:question" name="TQuestion">
        <xsl:for-each select="./*">
            <xsl:if test="name(.) = 'question-text'">
                <p><xsl:value-of select="."/></p>
            </xsl:if>
            <xsl:if test="name(.) = 'answer'">
                proso
                <xsl:value-of select="@type"/>
                <xsl:if test="@type = 'text'">
                        <p><xsl:value-of select="."/></p>
                </xsl:if>
                <xsl:if test="@type = 'multiple-choice'">
                    <xsl:choose>
                        <xsl:when test="@selected = 'true'">
                            <input type="checkbox" name="" value="" checked="true"><xsl:value-of select="."/></input>
                        </xsl:when>
                        <xsl:otherwise>
                            <input type="checkbox" name="" value=""><xsl:value-of select="."/></input>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:if>
                </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="sc:paragraph" name="TParagraph">
        <xsl:for-each select="./*">
            <xsl:if test="name(.) = 'quote'">
                <xsl:apply-templates/>
            </xsl:if>

            <xsl:if test="name(.) = 'list'">
                <xsl:choose>
                    <xsl:when test="@ordered='true'">
                        <ol>
                            <xsl:for-each select="./sc:list-item">
                                <li>
                                    <xsl:value-of select="."/>
                                </li>
                            </xsl:for-each>
                        </ol>
                    </xsl:when>
                    <xsl:otherwise>
                        <ul>
                            <xsl:for-each select="./sc:list-item">
                                <li>
                                    <xsl:value-of select="."/>
                                </li>
                            </xsl:for-each>
                        </ul>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:if>
            <xsl:if test="name(.) ='figure'">
                <div>
                    <xsl:for-each select="./sc:image">
                    <img>
                        <xsl:attribute name="src">
                            data:image/*;base64,<xsl:value-of select="."/>
                        </xsl:attribute>
                    </img>
                    </xsl:for-each>
                </div>
            </xsl:if>
            <xsl:if test="name(.) ='table'">
                <table border="1">
                    <caption>
                        <xsl:value-of select="@title"/>
                    </caption>
                    <xsl:for-each select="./*">
                        <xsl:choose>
                            <xsl:when test="name(.) = 'header'">
                                <xsl:for-each select="./sc:row">
                                    <th>
                                        <xsl:for-each select="./sc:column">
                                            <td>
                                                <xsl:value-of select="."/>
                                            </td>
                                        </xsl:for-each>
                                    </th>
                                </xsl:for-each>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:for-each select="./sc:row">
                                    <tr>
                                        <xsl:for-each select="./sc:column">
                                            <td>
                                                <xsl:value-of select="."/>
                                            </td>
                                        </xsl:for-each>
                                    </tr>
                                </xsl:for-each>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                </table>
            </xsl:if>
            <xsl:if test="name(.) = 'decorator'">
                    <br/><xsl:apply-templates select="."/>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="sc:list-item">
        <xsl:for-each select="./*">
            <li>
                <xsl:apply-templates select="."></xsl:apply-templates>
            </li>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="sc:quote/sc:quote-text">
        <q>
            <xsl:value-of select="."/>
        </q>
    </xsl:template>

    <xsl:template match="sc:publication">
        <xsl:value-of select="sc:title" />, <xsl:value-of select="sc:publisher" />, <xsl:value-of select="sc:place" />
    </xsl:template>


    <xsl:template match="sc:bold">
        <b>
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."></xsl:apply-templates>
            </xsl:for-each>
        </b>
    </xsl:template>

    <xsl:template match="sc:italic">
        <i>
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."></xsl:apply-templates>
            </xsl:for-each>
        </i>
    </xsl:template>

    <xsl:template match="sc:underline">
        <u>
            <xsl:for-each select="./* | text()">
                <xsl:apply-templates select="."></xsl:apply-templates>
            </xsl:for-each>
        </u>
    </xsl:template>

    <xsl:template match="sc:bold/text()">
        <xsl:copy-of select="." />
    </xsl:template>

    <xsl:template match="sc:italic/text()">
        <xsl:copy-of select="." />
    </xsl:template>

    <xsl:template match="sc:underline/text()">
        <xsl:copy-of select="." />
    </xsl:template>

    <xsl:template match="sc:paragraph/text()">
        <xsl:copy-of select="." />
    </xsl:template>

    <xsl:template match="sc:figure/text()">
        <xsl:copy-of select="." />
    </xsl:template>

    <xsl:template match="sc:quote/text()">
        <xsl:copy-of select="." />
    </xsl:template>


    <xsl:template match="sc:decorator/text()">
        <xsl:copy-of select="." />
    </xsl:template>





</xsl:stylesheet>