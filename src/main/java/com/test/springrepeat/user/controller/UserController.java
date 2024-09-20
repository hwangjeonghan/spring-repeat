package com.test.springrepeat.user.controller;


import com.test.springrepeat.user.entity.UserEntity;
import com.test.springrepeat.user.model.UserDTO;
import com.test.springrepeat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService Service) {
        this.service = Service;
    }

    //전체조회
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(){
        List<UserEntity> user = service.findAllUser();
        if(user.isEmpty()){
            return ResponseEntity.status(404)
                    .header("message","등록된 유저가 없습니다.")
                    .build();
        }
        return ResponseEntity.ok(user);
    }
    //상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        try {
            Optional<UserEntity> getUser = service.findUserById(id);
            return getUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404)
                    .header("message", "조회한 유저의 데이터가 없습니다.")
                    .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .header("message", e.getMessage())
                    .build();
        }
    }
    //등록
    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO userDto) {
        try {
            Optional<UserEntity> savedUser = service.saveUser(userDto);
            if (savedUser.isPresent()) {
                return ResponseEntity.ok(savedUser.get());
            } else {
                return ResponseEntity.status(500)
                        .header("message", "유저 저장에 실패했습니다.")
                        .build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .header("message", e.getMessage())
                    .build();
        }
    }
    //수정
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDto) {
        try {
            UserEntity updatedUser = service.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .header("message", e.getMessage())
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .header("message", "유저 업데이트에 실패했습니다.")
                    .build();
        }
    }
    //삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .header("message", e.getMessage())
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .header("message", "유저 삭제에 실패했습니다.")
                    .build();
        }
    }
}
