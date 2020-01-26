package xmlteam4.Project.model;

public enum ScientificPaperAbstractTitles {
    PURPOSE("Purpose", true),
    METHODOLOGY("Methodology", true),
    FINDINGS("Findings", true),
    IMPLICATIONS("Implications", false),
    PRACTICAL_IMPLICATIONS("Practical Implications", false),
    SOCIAL_IMPLICATIONS("Social Implications", false),
    ORIGINALITY("Originality", false);

    private String name;
    private boolean mandatory;

    ScientificPaperAbstractTitles(String name, boolean mandatory) {
        this.name = name;
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isMandatory() {
        return mandatory;
    }
}
