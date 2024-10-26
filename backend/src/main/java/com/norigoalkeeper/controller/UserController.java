package com.norigoalkeeper.controller;

import com.norigoalkeeper.model.User;
import com.norigoalkeeper.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * ユーザー関連の操作を提供するコントローラクラス。
 */
@Tag(name = "ユーザー")
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
  @Operation(summary = "すべてのユーザーを取得", description = "登録されているすべてのユーザーのリストを取得します。", tags = { "ユーザー" })
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
  @Operation(summary = "新しいユーザーを作成", description = "新しいユーザーを作成し、作成されたユーザーの情報を返します。", tags = { "ユーザー" })
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
  @Operation(summary = "特定のユーザーを取得", description = "指定したユーザーIDに基づいて、ユーザーの情報を取得します。", tags = { "ユーザー" })
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
  @Operation(summary = "ユーザー情報を更新", description = "指定したユーザー情報を更新し、更新後のユーザー情報を返します。", tags = { "ユーザー" })
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
  @Operation(summary = "特定のユーザーを削除", description = "指定したユーザーIDに基づいて、ユーザーを削除します。", tags = { "ユーザー" })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    int deleteId = userService.deleteUser(id);
    return deleteId > 0
        ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
