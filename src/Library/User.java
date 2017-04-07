package Library;

/**
 * Created by Jp on 4/5/17.
 */
public class User {

    private Integer uID;
    private String username;
    private String password;
    private String role;
    private Integer visitorID;





    User(String _username, String _password, String role, Integer visitorID){
        this.username = _username;
        this.password = _password;
        this.role = role;
        this.visitorID = visitorID;
        this.uID = Users.getMap().lastKey() + 1;
        Users.getMap().put(this.uID, this);
    }

    public Integer getuID(){
        return this.uID;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){
        return this.role;
    }
    public Integer getVisitorID(){
        return this.visitorID;
    }


}
