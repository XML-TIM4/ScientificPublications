<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xpath-default-namespace="https://github.com/XML-TIM4/ScientificPublications"
                xmlns="https://github.com/XML-TIM4/ScientificPublications">

    <xsl:output method="xml" indent="yes"/>
    <xsl:param name="reviewToMerge" select="document(.)"/>

    <xsl:variable name="reviewToMergeReviewers">
        <xsl:for-each select="$reviewToMerge//reviewer">
            <xsl:value-of select="."/>
        </xsl:for-each>
    </xsl:variable>

    <xsl:variable name="reviewToMergeRecommendation">
        <xsl:for-each select="$reviewToMerge//recommendation">
            <xsl:value-of select="."/>
        </xsl:for-each>
    </xsl:variable>


    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="review-metadata">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()[not(self::entry)] |
                                   entry[not(id = $updateItems/id)]" />
            <xsl:apply-templates select="$updateItems" />
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
