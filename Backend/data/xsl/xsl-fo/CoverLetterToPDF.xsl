<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
        <fo:root>
        <fo:layout-master-set>
            <fo:simple-page-master master-name="reviewPage">
                <fo:region-body margin="1in"/>
            </fo:simple-page-master>
        </fo:layout-master-set>

        <fo:page-sequence master-reference="reviewPage">
            <fo:flow flow-name="xsl-region-body">
                <fo:block font-size="10px">
                    <xsl:value-of select="sc:CoverLetter/sc:cover-letter-metadata/sc:date"></xsl:value-of>
                </fo:block>
                <fo:block font-size="10px" text-align="left" space-after="30px">
                    <xsl:call-template name="TAuthor">
                        <xsl:with-param name="author" select="sc:CoverLetter/sc:cover-letter-metadata/sc:author"/>
                    </xsl:call-template>
                </fo:block>

                <fo:block text-align="center" space-before="10px" space-after="10px" font-size="24px">
                    Cover Letter
                </fo:block>

                <fo:block text-align="left" margin-top="10px" margin-bottom="10px">
                    <xsl:for-each select="sc:CoverLetter/sc:content/sc:paragraph">
                        <fo:block space-after="20px" space-before="10px"><xsl:call-template name="TParagraph"/></fo:block>
                    </xsl:for-each>

                    <fo:block space-before="10px" space-after="20px">
                        <fo:external-graphic>
                            <xsl:attribute name="src">
                                data:image/*;base64,<xsl:value-of select="sc:CoverLetter/sc:content/sc:signature"/>
                            </xsl:attribute>
                        </fo:external-graphic>
                    </fo:block>
                </fo:block>

                <fo:block text-align="left" margin-top="10px" margin-bottom="10px">
                    <xsl:call-template name="TAuthor">
                        <xsl:with-param name="author" select="sc:CoverLetter/sc:cover-letter-metadata/sc:editor"/>
                    </xsl:call-template>
                </fo:block>

            </fo:flow>
        </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>