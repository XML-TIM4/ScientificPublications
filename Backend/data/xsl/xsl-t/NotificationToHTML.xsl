<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Notification</title>
            </head>

            <body>
                <xsl:variable name="paper" select="document(concat('http://localhost:8080/api/scientific-papers/', sc:notification/sc:notification-metadata/sc:scientific-paper-id))"/>
                <h3 style="margin:20px">
                    Dear <xsl:value-of select="sc:notification/sc:notification-metadata/sc:recipient/sc:name/sc:first-name"/>
                    <xsl:text> </xsl:text>
                    <xsl:for-each select="sc:notification/sc:notification-metadata/sc:recipient/sc:name/sc:middle-name">
                        <xsl:value-of select="."/>
                        <xsl:text> </xsl:text>
                    </xsl:for-each>
                    <xsl:value-of select="sc:notification/sc:notification-metadata/sc:recipient/sc:name/sc:last-name"/>
                    <xsl:text>,</xsl:text><br/><br/>You have received a new notification regarding the publication<br/>
                    <xsl:text>"</xsl:text>
                    <xsl:value-of select="$paper/sc:scientific-paper/sc:title"/>
                    <xsl:text>"</xsl:text>
                    <br/>on <xsl:value-of select="sc:notification/sc:notification-metadata/sc:date"/>
                    :<br/><br/>
                </h3>
                <xsl:for-each select="sc:notification/sc:content">
                    <xsl:call-template name="TParagraph"/>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>