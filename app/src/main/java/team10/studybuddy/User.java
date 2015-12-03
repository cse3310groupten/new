package team10.studybuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Brian on 12/1/2015.
 */
public class User{

    String name, username, password;


    public User(String name,String username,String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }

    //if user does not enter a name and only has username and password
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.name = "";

    }
}
