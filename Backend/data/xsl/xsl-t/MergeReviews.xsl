<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xpath-default-namespace="https://github.com/XML-TIM4/ScientificPublications"
                xmlns="https://github.com/XML-TIM4/ScientificPublications">

    <xsl:output method="xml" indent="yes"/>
    <xsl:param name="reviewToMerge" select="document(.)"/>

    <xsl:variable name="newReviewer" select="$reviewToMerge//reviewer"/>

    <xsl:variable name="newRecommendation" select="$reviewToMerge//recommendation"/>

    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="review-metadata">
        <xsl:apply-templates select="@* | node()[not(self::reviewer)] |
                                   reviewer[not(email = $newReviewer/email)]"/>
        <xsl:apply-templates select="$newReviewer"/>
    </xsl:template>

    <xsl:template match="//recommendation">
        <xsl:copy-of select="."/>
        <xsl:apply-templates select="$newRecommendation"/>
    </xsl:template>

    <xsl:variable name="newReviewComments" select="$reviewToMerge//reviewer-comments"/>

    <xsl:template match="//reviewer-commments[last()]">
        <xsl:copy-of select="."/>
        <xsl:apply-templates select="$newReviewComments"/>
    </xsl:template>

    <xsl:variable name="newQuestions" select="$reviewToMerge//question"/>

    <xsl:template match="//questionnaire/question[last()]">
        <xsl:copy-of select="."/>
        <xsl:apply-templates select="$newQuestions"/>
    </xsl:template>
</xsl:stylesheet>
