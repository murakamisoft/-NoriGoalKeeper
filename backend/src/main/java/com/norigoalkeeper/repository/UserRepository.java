package com.norigoalkeeper.repository;

import com.norigoalkeeper.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ユーザー情報を操作するためのリポジトリインターフェース。
 */
@Mapper
public interface UserRepository {

  /**
   * ユーザーを新規登録します。
   *
   * @param user ユーザー情報
   * @return 登録したユーザーのID
   */
  Long insertUser(User user);

  /**
   * ユーザーIDをもとにユーザー情報を取得します。
   *
   * @param userId ユーザーID
   * @return ユーザー情報
   */
  User findById(@Param("userId") Long userId);

  /**
   * メールアドレスをもとにユーザー情報を取得します。
   *
   * @param email ユーザーのメールアドレス
   * @return ユーザー情報
   */
  User findByEmail(@Param("email") String email);

  /**
   * 全ユーザーのリストを取得します。
   *
   * @return ユーザー情報のリスト
   */
  List<User> findAll();

  /**
   * ユーザー情報を更新します。
   *
   * @param user 更新するユーザー情報
   * @return 更新件数
   */
  int updateUser(User user);

  /**
   * ユーザーを削除します。
   *
   * @param userId ユーザーID
   * @return 削除件数
   */
  int deleteUser(@Param("userId") Long userId);
}