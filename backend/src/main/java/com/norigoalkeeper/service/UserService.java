package com.norigoalkeeper.service;

import com.norigoalkeeper.mapper.UserMapper;
import com.norigoalkeeper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ユーザー情報を管理するサービスクラス。
 */
@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  /**
   * 新規ユーザーを登録します。
   *
   * @param user ユーザー情報
   * @return 登録したユーザーのID
   */
  public Long registerUser(User user) {
    return userMapper.insertUser(user);
  }

  /**
   * ユーザーIDをもとにユーザー情報を取得します。
   *
   * @param userId ユーザーID
   * @return ユーザー情報
   */
  public User findByUserId(Long userId) {
    return userMapper.findById(userId);
  }

  /**
   * メールアドレスをもとにユーザー情報を取得します。
   *
   * @param email ユーザーのメールアドレス
   * @return ユーザー情報
   */
  public User getUserByEmail(String email) {
    return userMapper.findByEmail(email);
  }

  /**
   * 全ユーザーのリストを取得します。
   *
   * @return ユーザー情報のリスト
   */
  public List<User> findAllUsers() {
    return userMapper.findAll();
  }

  /**
   * ユーザー情報を更新します。
   *
   * @param user 更新するユーザー情報
   * @return 更新件数
   */
  public int updateUser(User user) {
    return userMapper.updateUser(user);
  }

  /**
   * ユーザーを削除します。
   *
   * @param userId ユーザーID
   * @return 削除件数
   */
  public int deleteUser(Long userId) {
    return userMapper.deleteUser(userId);
  }
}
