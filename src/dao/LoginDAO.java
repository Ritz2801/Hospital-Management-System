package dao;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    private List<User> users =
            new ArrayList<>();


    public LoginDAO(){

        users.add(
                new User(
                        "admin",
                        "admin123",
                        "Admin"
                )
        );

        users.add(
                new User(
                        "doctor",
                        "doctor123",
                        "Doctor"
                )
        );

        users.add(
                new User(
                        "reception",
                        "reception123",
                        "Reception"
                )
        );

    }


    public User login(

            String username,
            String password

    ){

        for(User user:users){

            if(

                    user.getUsername()
                            .equals(username)

                    &&

                    user.getPassword()
                            .equals(password)

            ){

                return user;

            }

        }

        return null;

    }

}