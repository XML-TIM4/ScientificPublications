package xmlteam4.Project.businessprocess;

public enum PhaseTitle {
    SUBMITTED("submitted"),
    REVIEW("review"),
    REVIEWED("reviewed"),
    REJECTED("rejected"),
    ACCEPTED("accepted"),
    REVISE("revise");

    private String name;

    PhaseTitle(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
