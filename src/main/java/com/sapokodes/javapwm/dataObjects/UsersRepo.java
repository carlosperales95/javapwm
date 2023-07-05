package com.sapokodes.javapwm.dataObjects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepo {
        
    //private User user = new User();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users", BeanPropertyRowMapper.newInstance(User.class));
    }
}
