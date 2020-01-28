package xmlteam4.Project.model;

public enum ScientificPaperStatus {
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    REVISION("revision"),
    WITHDRAWN("withdrawn"),
    UPLOADED("uploaded");

    private String name;

    ScientificPaperStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
