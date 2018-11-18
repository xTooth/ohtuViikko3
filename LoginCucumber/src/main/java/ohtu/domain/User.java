package ohtu.domain;


import org.springframework.stereotype.Component;

@Component
public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(){
        this.password ="";
        this.username = "";
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
