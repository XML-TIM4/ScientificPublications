package xmlteam4.Project.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDTO {
    @NotBlank
    @Pattern(regexp = "^([a-zA-Z0-9@*#]{8,15})$")
    private String password;

    @NotBlank
    @Pattern(regexp = "[^@]+@[^\\.]+\\..+")
    private String email;

    private String id;

    @NotNull
    private Boolean editor;

    @NotBlank
    private String expertise;

    public UserDTO() {
    }

    public UserDTO(@NotBlank @Pattern(regexp = "^([a-zA-Z0-9@*#]{8,15})$") String password,
                   @NotBlank @Pattern(regexp = "[^@]+@[^\\.]+\\..+") String email, String id, @NotNull Boolean editor, @NotBlank String expertise) {
        this.password = password;
        this.email = email;
        this.id = id;
        this.editor = editor;
        this.expertise = expertise;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getEditor() {
        return editor;
    }

    public void setEditor(Boolean editor) {
        this.editor = editor;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
}
