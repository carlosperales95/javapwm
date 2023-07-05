package com.sapokodes.javapwm.dataObjects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UsersRepo {
        
    //private User user = new User();

    public List<User> getUsers() {
        List<User> usersList =  new ArrayList<>();

        usersList.add(new User(1, "manololillo", "123pass", "manolo.manolillo@gmail.com" ));
        usersList.add(new User(2, "uriente3", "mypass", "ursula.clienta@gmail.com" ));
        usersList.add(new User(3, "paqui04", "1234", "paquillo2004@gmail.com" ));

        return usersList;
    }
}
