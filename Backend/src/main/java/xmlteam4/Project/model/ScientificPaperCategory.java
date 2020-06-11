package xmlteam4.Project.model;

public enum ScientificPaperCategory {
    RESEARCH_PAPER("research-paper"),
    VIEWPOINT("viewpoint"),
    TECHNICAL_PAPER("technical-paper"),
    CONCEPTUAL_PAPER("conceptual-paper"),
    CASE_STUDY("case-study"),
    LITERATURE_REVIEW("literature-review"),
    GENERAL_REVIEW("general-review");

    private String name;

    ScientificPaperCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
