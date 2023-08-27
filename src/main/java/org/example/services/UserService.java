package org.example.services;

import org.example.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserService {
     private final List<User> users= new ArrayList<>();

    public List<User> getAll(){
        return users;
    }

    public void add(User... users) {
        this.users.addAll(Arrays.asList(users));
    }

    public Optional<User> login(String userName, String password) {
        if (userName==null || password==null){
            throw new IllegalArgumentException("login or password is null");
        }
        return users.stream().filter(user -> user.getUserName().equals(userName))
                .filter(user -> user.getPassword().equals(password)).findAny();
    }

    public Map<Integer, User> getAllConvertedMap() {

        return users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
