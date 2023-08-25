package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

 class UserServiceTest {

    @Test
    void ifUserIsEmpty(){
        var userService=new UserService();
        var all = userService.getAll();

        Assertions.assertTrue(all.isEmpty());
    }
}
