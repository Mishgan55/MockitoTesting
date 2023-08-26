package org.example.services;

import org.example.User;
import org.example.services.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {
    UserService userService;

    @BeforeAll
    void beforeClass(){
        System.out.println("before class: " +  this.toString());
    }

    @BeforeEach
    void beforeMethods(){
        System.out.println("Before each: "+this.toString());
        userService = new UserService();
    }

    @Test
    void ifUserIsEmpty(){
        var all = userService.getAll();
        Assertions.assertTrue(all.isEmpty());
    }

    @Test
    void userAddingTest(){
        userService.add(new User(1,"Misha"));
        userService.add(new User(2,"Hanna"));
        var all = userService.getAll();
        Assertions.assertEquals(2,all.size());
    }

    @AfterEach
    void afterMethods(){
        System.out.println("After each: "+this.toString());

    }

    @AfterAll
    void afterAll(){
        System.out.println("After all:" + this.toString());
    }
}