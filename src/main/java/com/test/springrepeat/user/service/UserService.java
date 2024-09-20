package com.test.springrepeat.user.service;


import com.test.springrepeat.user.entity.UserEntity;

import com.test.springrepeat.user.model.UserDTO;
import com.test.springrepeat.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //전체조회
    @Transactional
    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }

    //상세조회
    @Transactional
    public Optional<UserEntity> findUserById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID는 0보다 커야 합니다.");
        }
        return userRepository.findById(id);
    }
    //등록
    @Transactional
    public Optional<UserEntity> saveUser(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.builder()
                .userName(userDTO.getUserName())
                .userAge(userDTO.getUserAge())
                .addressPost(userDTO.getAddressPost())
                .addressDefault(userDTO.getAddressDefault())
                .addressDetail(userDTO.getAddressDetail())
                .userCreateAt(LocalDateTime.now())
                .userUpdateAt(LocalDateTime.now())
                .build();

        validateUser(userEntity);
        UserEntity savedUser = userRepository.save(userEntity);
        return Optional.of(savedUser);
    }
    private void validateUser(UserEntity userEntity) {
        if (userEntity.getUserName() == null || userEntity.getUserName().length() != 3) {
            throw new IllegalArgumentException("이름은 3글자여야 합니다.");
        }
        if (userEntity.getUserAge() == null || userEntity.getUserAge() < 20) {
            throw new IllegalArgumentException("회원의 나이는 20살 이상이어야 합니다.");
        }
        if (userEntity.getAddressPost() == null || !userEntity.getAddressPost().matches("\\d{5}")) {
            throw new IllegalArgumentException("우편번호는 5자리 숫자여야 합니다.");
        }
    }
    //수정
    @Transactional
    public UserEntity updateUser(Integer id, UserDTO userDto) {
        UserEntity findUser = userRepository.findById(id).orElse(null);
        if (findUser == null) {
            throw new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다.");
        }

        UserEntity updatedUser = UserEntity.builder()
                .id(findUser.getId())
                .userName(userDto.getUserName() != null ? userDto.getUserName() : findUser.getUserName())
                .userAge(userDto.getUserAge() != null ? userDto.getUserAge() : findUser.getUserAge())
                .addressPost(userDto.getAddressPost() != null ? userDto.getAddressPost() : findUser.getAddressPost())
                .addressDefault(userDto.getAddressDefault() != null ? userDto.getAddressDefault() : findUser.getAddressDefault())
                .addressDetail(userDto.getAddressDetail() != null ? userDto.getAddressDetail() : findUser.getAddressDetail())
                .userCreateAt(findUser.getUserCreateAt())
                .userUpdateAt(LocalDateTime.now())
                .build();

        validateUser(updatedUser);
        return userRepository.save(updatedUser);
    }
    //삭제
    @Transactional
    public void deleteUser(Integer id) {
        UserEntity findUser = userRepository.findById(id).orElse(null);
        if (findUser == null) {
            throw new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다.");
        }
        userRepository.delete(findUser);
    }
}

