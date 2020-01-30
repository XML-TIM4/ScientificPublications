<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="scientificPaperPage">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="scientificPaperPage">
                <fo:flow flow-name="xsl-region-body">

                    <fo:block text-align="center" font-size="24px" space-after="10px">
                        <xsl:value-of select="sc:scientific-paper/sc:title"/>
                    </fo:block>

                    <fo:block space-after="12px">
                        <fo:table>
                            <fo:table-body>
                                <fo:table-row>
                                    <xsl:for-each select="sc:scientific-paper/sc:authors/sc:author">
                                        <fo:table-cell text-align="center">
                                            <xsl:call-template name="TAuthor">
                                                <xsl:with-param name="author" select = "." />
                                            </xsl:call-template>
                                        </fo:table-cell>
                                    </xsl:for-each>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block space-after="10px">
                        <fo:block margin-left="5px" font-size="18px" space-after="5px">
                            Abstract
                        </fo:block>
                        <xsl:for-each select="sc:scientific-paper/sc:abstract/sc:abstract-item">
                            <fo:block>
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="@title"/>:
                                </fo:inline>
                                <xsl:value-of select="."/>
                            </fo:block>
                        </xsl:for-each>
                        <fo:block>
                            <fo:inline font-weight="bold">
                                Keywords:
                            </fo:inline>
                            <xsl:for-each select="sc:scientific-paper/sc:metadata/sc:keywords/sc:keyword">
                                <xsl:value-of select="."/><xsl:if test="not(position()=last())">,&#160;</xsl:if>
                            </xsl:for-each>
                        </fo:block>
                    </fo:block>

                    <xsl:for-each select="sc:scientific-paper/sc:content/sc:section">
                        <fo:block space-after="10px">
                            <fo:block margin-left="5px" font-size="18px" space-after="5px">
                                <xsl:value-of select="sc:title"/>
                            </fo:block>
                            <xsl:for-each select="./sc:paragraph">
                                <fo:block>
                                    <xsl:call-template name="TParagraph"/>
                                </fo:block>
                            </xsl:for-each>
                        </fo:block>
                    </xsl:for-each>

                    <fo:block margin-left="5px" font-size="18px" space-after="5px">
                        References
                    </fo:block>
                    <xsl:for-each select="sc:scientific-paper/sc:references/sc:reference">
                        <fo:block>
                            <fo:inline>
                                <xsl:variable name="link" select="sc:publication/sc:url"/>
                                <xsl:for-each select="sc:authors/sc:name">
                                    <xsl:if test="position()=last()">and&#160;</xsl:if>
                                    <xsl:value-of select="sc:first-name"/>&#160;<xsl:for-each select="sc:middle-name"><xsl:value-of select="."/>&#160;</xsl:for-each>
                                    <xsl:value-of select="sc:last-name"/><xsl:if test="not(position()=last())">,&#160;</xsl:if>
                                </xsl:for-each>
                                &#160;(<xsl:value-of select="sc:publication/sc:year"/>).&#160;<xsl:value-of select="sc:publication/sc:title"/>,&#160;<xsl:value-of
                                    select="sc:publication/sc:publisher"/>,&#160;<xsl:value-of select="sc:publication/sc:place"/><fo:basic-link color="blue" external-destination="{$link}">[^]</fo:basic-link>
                            </fo:inline>
                        </fo:block>
                    </xsl:for-each>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>