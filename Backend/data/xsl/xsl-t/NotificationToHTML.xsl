<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:template match="/">
        <xsl:variable name="documentLink"
                      select="document(concat('http://localhost:8080/api/', //sc:document-collection, '/', //sc:document-id))"/>
        <html>
            <body>
                <h3 style="margin:20px">Dear <xsl:value-of select="//sc:recipient-name"/>,</h3>
                <br/>
                <p><xsl:value-of select="//sc:content"/></p>
                <br/>
                <span><h4>Document: </h4><a href="{$documentLink}"><xsl:value-of select="$documentLink"/></a></span>
                <br/>
                <span><h4>On date: </h4><xsl:value-of select="//date"/></span>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
