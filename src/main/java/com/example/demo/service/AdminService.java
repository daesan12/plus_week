package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {
        // 한 번의 쿼리로 모든 사용자 조회
        List<User> users = userRepository.findAllById(userIds);

        if (users.size() != userIds.size()) {
            throw new IllegalArgumentException("잘못된 ID가 존재합니다.");
        }

        // 상태 변경
        users.forEach(User::updateStatusToBlocked);

        // 한 번의 쿼리로 모든 사용자 상태 저장
        userRepository.saveAll(users);
    }
        }
