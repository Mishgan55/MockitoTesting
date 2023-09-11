package org.example.services;

import org.example.models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


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
        assertTrue(all.isEmpty());
    }

    @Test
    void userAddingTest(){
        userService.add(MISHA);
        userService.add(HANNA);
        var all = userService.getAll();
        assertThat(all).hasSize(2);
//        assertEquals(2,all.size());
    }
    @Test
    @Tag("login")
    void loginSuccessIfUserExist(){
        userService.add(MISHA);

        Optional<User> maybeUser=userService.login(MISHA.getUserName(),MISHA.getPassword());

        assertThat(maybeUser).isPresent();
        maybeUser.ifPresent(user->assertThat(user).isEqualTo(MISHA));
//        maybeUser.ifPresent(user-> assertEquals(MISHA,user));
    }
    @Test
    @Tag("login")
    void exceptionIfUserNameOrPasswordIsNull(){
        assertAll(
                ()->{var e=assertThrows(IllegalArgumentException.class,()->userService.login("dummy",null));
                    assertThat(e).hasMessage("login or password is null");
                },
                ()->assertThrows(IllegalArgumentException.class,()->userService.login(null,"dummy"))

        );
    }
    @Test
    void convertedToMapID(){
        userService.add(MISHA,HANNA);
        Map<Integer, User> map=userService.getAllConvertedMap();

        assertAll(
                ()->assertThat(map).containsKeys(MISHA.getId(),HANNA.getId()),
                ()->assertThat(map).containsValues(MISHA,HANNA)

        );
    }
    @Test
    @Tag("login")
    void loginFailedIfPasswordIncorrect(){
        userService.add(MISHA);

        Optional<User> maybeUser=userService.login(MISHA.getUserName(),"dummy");
        assertTrue(maybeUser.isEmpty());
    }
    @Test
    @Tag("login")
    void loginFailedIfUserNameIncorrect(){
        userService.add(MISHA);

        Optional<User> maybeUser=userService.login("dummy",MISHA.getPassword());
        assertTrue(maybeUser.isEmpty());
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