package xmlteam4.Project.businessprocess;

public enum DocumentType {
    SCIENTIFIC_PAPER("scientific-paper"),
    REVIEW("review"),
    COVER_LETTER("cover-letter"),
    NOTIFICATION("notification");

    private String name;

    DocumentType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
