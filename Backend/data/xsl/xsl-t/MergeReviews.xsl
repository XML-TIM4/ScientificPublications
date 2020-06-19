<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xpath-default-namespace="https://github.com/XML-TIM4/ScientificPublications"
                xmlns="https://github.com/XML-TIM4/ScientificPublications">

    <xsl:output method="xml" indent="yes"/>
    <xsl:param name="fileName" select="."/>
    <xsl:param name="reviewToMerge" select="document($fileName)"/>

    <xsl:variable name="newReviewer" select="$reviewToMerge//reviewer"/>

    <xsl:variable name="newRecommendation" select="$reviewToMerge//recommendation"/>

    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="//reviewer[last()]">
        <xsl:copy-of select="."/>
        <xsl:copy-of select="$newReviewer"/>
    </xsl:template>

    <xsl:template match="//recommendation">
        <xsl:copy-of select="."/>
        <xsl:copy-of select="$newRecommendation"/>
    </xsl:template>

    <xsl:variable name="newReviewComments" select="$reviewToMerge//reviewer-comment"/>

    <xsl:template match="//reviewer-commment[last()]">
        <xsl:copy-of select="."/>
        <xsl:copy-of select="$newReviewComments"/>
    </xsl:template>

    <xsl:variable name="newQuestions" select="$reviewToMerge//question"/>

    <xsl:template match="//questionnaire/question[last()]">
        <xsl:copy-of select="."/>
        <xsl:copy-of select="$newQuestions"/>
    </xsl:template>
</xsl:stylesheet>
