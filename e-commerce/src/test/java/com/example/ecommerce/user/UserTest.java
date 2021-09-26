package com.example.ecommerce.user;

import com.example.ecommerce.domain.Address;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class UserTest {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setAddress(new Address("Seoul","dobonggu",123));
        user.setName("jihuhwan");
        Long join = userService.join(user);
        Assertions.assertEquals(user, userRepository.findOne(join));
    }

    @Test
    public void userDuplicationTest() throws Exception {
        User user1 = new User();
        user1.setAddress(new Address("Seoul","dobonggu",123));
        user1.setName("jihuhwan");
        User user2 = new User();
        user2.setAddress(new Address("Seoul","dobonggu",123));
        user2.setName("jihuhwan");
        userService.join(user1);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            userService.join(user2);
        });
    }
}
