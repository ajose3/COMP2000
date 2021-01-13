package Model;

public class Admin {
    private String username;
    private String password;

    //Constructor
    public Admin(String name, String pass) {
        setUsername(name);
        setPassword(pass);
    }

    //Setters
    public void setUsername(String uName){
        username = uName;
    }

    public void setPassword(String pWord){
        password = pWord;
    }


    //Getters
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
