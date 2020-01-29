package xmlteam4.Project.businessprocess;

public enum UserType {
    AUTHOR("author"),
    EDITOR("editor"),
    REVIEWER("reviewer");

    private String name;

    UserType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
