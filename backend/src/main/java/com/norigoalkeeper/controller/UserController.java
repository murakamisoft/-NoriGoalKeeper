package com.norigoalkeeper.controller;

import com.norigoalkeeper.model.User;
import com.norigoalkeeper.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ユーザー関連の操作を提供するコントローラクラス。
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * すべてのユーザーを取得する。
   *
   * @return ユーザーのリスト
   */
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.findAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  /**
   * 新しいユーザーを作成する。
   *
   * @param user ユーザー情報
   * @return 作成されたユーザー
   */
  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    Long id = userService.registerUser(user);
    // 作成したユーザーの詳細を取得
    User createdUser = userService.findByUserId(id);

    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  /**
   * 特定のユーザーを取得する。
   *
   * @param id ユーザーID
   * @return ユーザー情報
   */
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.findByUserId(id);
    return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * ユーザー情報を更新する。
   *
   * @param user 更新するユーザー情報
   * @return 更新されたユーザー
   */
  @PutMapping("/")
  public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
    int updatedUser = userService.updateUser(user);
    return updatedUser > 0
        ? new ResponseEntity<>(user, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * 特定のユーザーを削除する。
   *
   * @param id ユーザーID
   * @return 削除結果
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    int deleteId = userService.deleteUser(id);
    return deleteId > 0
        ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
