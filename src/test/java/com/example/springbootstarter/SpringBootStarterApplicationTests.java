package com.example.springbootstarter;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

import com.example.springbootstarter.Controllers.UserController;

import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SpringBootStarterApplicationTests {

    @Autowired
    private UserController _userController;

    @Test
    void contextLoads() {
        assert _userController != null;
    }

    @Test
    void Hello_ReturnsHello(){
        var result = _userController.hello();

        Assert.notNull(result);
        assertEquals("Hello World!", result.getBody());
        assertInstanceOf(ResponseEntity.class, result);
    }
}
