package com.example.ecommerce.service;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> users = userRepository.findByName(user.getName());
        if (!users.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public void update(Long id, String name) {
        User one = userRepository.findOne(id);
        one.setName(name);
    }
}
