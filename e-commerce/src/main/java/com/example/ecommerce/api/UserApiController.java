package com.example.ecommerce.api;

import com.example.ecommerce.domain.Address;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public CreateUserResponse saveUserV1(@RequestBody @Valid User user) {
        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @PostMapping("/api/v2/users")
    public CreateUserResponse saveUserV2(@RequestBody @Valid CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @PutMapping("/api/v2/users/{id}")
    public UpdateUserResponse updateUserV2(@PathVariable("id") Long id,
                                           @RequestBody @Valid UpdateUserRequest request) {
        userService.update(id, request.getName());
        User user = userService.findOne(id);
        return new UpdateUserResponse(user.getName(), user.getId());
    }

    @GetMapping("/api/v1/users")
    public List<User> usersV1() {
        return userService.findUsers();
    }

    @GetMapping("/api/v2/users")
    public Result usersV2() {
        List<User> users = userService.findUsers();
        List<UserDto> collect = users.stream()
                .map(m -> new UserDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateUserRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateUserResponse {
        private String name;
        private Long id;
    }

    @Data
    static class UpdateUserRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class UserDto {
        private String name;
    }
}
