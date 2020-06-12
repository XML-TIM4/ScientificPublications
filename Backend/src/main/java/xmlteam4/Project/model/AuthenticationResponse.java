package xmlteam4.Project.model;


public class AuthenticationResponse {

    private static String message = "Authentication successful.";
    private String token;

    private AuthenticationResponse(){

    }

    public AuthenticationResponse(String token){
        this.token = token;
    }

    public void setToken(String token){ this.token = token; }

    public String getToken(){ return this.token; }
}
