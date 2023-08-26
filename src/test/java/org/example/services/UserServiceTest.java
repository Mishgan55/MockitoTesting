package org.example.services;

import org.example.models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Optional;


class UserServiceTest {
    private static final User MISHA = User.of(1, "Misha", "111");
    private static final User HANNA = User.of(2, "Hanna", "222");
    UserService userService;

    @BeforeAll
    static void beforeClass(){
        System.out.println("before class: ");
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
        userService.add(MISHA);
        userService.add(HANNA);
        var all = userService.getAll();
        Assertions.assertEquals(2,all.size());
    }
    @Test
    void loginSuccessIfUserExist(){
        userService.add(MISHA);

        Optional<User> maybeUser=userService.login(MISHA.getUserName(),MISHA.getPassword());
        maybeUser.ifPresent(user->Assertions.assertEquals(MISHA,user));
    }
    @Test
    void loginFailedIfPasswordIncorrect(){
        userService.add(MISHA);

        Optional<User> maybeUser=userService.login(MISHA.getUserName(),"dummy");
        Assertions.assertTrue(maybeUser.isEmpty());
    }
    @Test
    void loginFailedIfUserNameIncorrect(){
        userService.add(MISHA);

        Optional<User> maybeUser=userService.login("dummy",MISHA.getPassword());
        Assertions.assertTrue(maybeUser.isEmpty());
    }

    @AfterEach
    void afterMethods(){
        System.out.println("After each: "+this.toString());

    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all:" );
    }
}