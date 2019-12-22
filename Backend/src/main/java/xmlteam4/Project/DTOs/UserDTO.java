package xmlteam4.Project.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserDTO {
    @NotBlank
    private String username;

    @NotBlank
    @Pattern(regexp = "^([a-zA-Z0-9@*#]{8,15})$")
    private String password;

    @NotBlank
    @Pattern(regexp = "[^@]+@[^\\.]+\\..+")
    private String email;

    private Boolean isEditor;

    private String authorId;

    public UserDTO() {

    }

    public UserDTO(@NotBlank String username, @NotBlank @Pattern(regexp = "^([a-zA-Z0-9@*#]{8,15})$") String password, @NotBlank @Pattern(regexp = "[^@]+@[^\\.]+\\..+") String email, Boolean isEditor, String authorId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEditor = isEditor;
        this.authorId = authorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEditor() {
        return isEditor;
    }

    public void setEditor(Boolean editor) {
        isEditor = editor;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
