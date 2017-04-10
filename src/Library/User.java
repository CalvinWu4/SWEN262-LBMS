package Library;

import Library.Database.Users;

import java.io.Serializable;

/**
 * Created by Jp on 4/5/17.
 */
public class User implements Serializable{

    private Integer uID;
    private String username;
    private String password;
    private String role;
    private Integer visitorID;

    public User(Integer _uid, String _username, String _password, String role, Integer visitorID){
        this.username = _username;
        this.password = _password;
        this.role = role;
        this.visitorID = visitorID;
        this.uID = _uid;
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
