package xmlteam4.Project.businessprocess;

public enum ReviewCycleStatus {
    PENDING("pending"),
    REJECTED("rejected"),
    ACCEPTED("accepted"),
    REVISE("revise"),
    WITHDRAWN("withdrawn"),
    REVIEWED("reviewed");

    private String name;

    ReviewCycleStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
