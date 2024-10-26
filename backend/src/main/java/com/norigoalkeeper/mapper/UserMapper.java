package com.norigoalkeeper.mapper;

import com.norigoalkeeper.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ユーザー情報を操作するためのマッパークラス。
 */
@Mapper
public interface UserMapper {

  /**
   * ユーザーを新規登録します。
   *
   * @param user ユーザー情報
   * @return 登録したユーザーのID
   */
  @Insert("INSERT INTO m_users (username, email, password, created_at) " +
      "VALUES (#{username}, #{email}, #{password}, #{createdAt})")
  @Options(useGeneratedKeys = true, keyProperty = "userId")
  Long insertUser(User user);

  /**
   * ユーザーIDをもとにユーザー情報を取得します。
   *
   * @param userId ユーザーID
   * @return ユーザー情報
   */
  @Select("SELECT * FROM m_users WHERE user_id = #{userId}")
  User findById(@Param("userId") Long userId);

  /**
   * メールアドレスをもとにユーザー情報を取得します。
   *
   * @param email ユーザーのメールアドレス
   * @return ユーザー情報
   */
  @Select("SELECT * FROM m_users WHERE email = #{email}")
  User findByEmail(@Param("email") String email);

  /**
   * 全ユーザーのリストを取得します。
   *
   * @return ユーザー情報のリスト
   */
  @Select("SELECT * FROM m_users")
  List<User> findAll();

  /**
   * ユーザー情報を更新します。
   *
   * @param user 更新するユーザー情報
   * @return 更新件数
   */
  @Update("UPDATE m_users SET username = #{username}, email = #{email}, password = #{password}, " +
      "updated_at = CURRENT_TIMESTAMP WHERE user_id = #{userId}")
  int updateUser(User user);

  /**
   * ユーザーを削除します。
   *
   * @param userId ユーザーID
   * @return 削除件数
   */
  @Delete("DELETE FROM m_users WHERE user_id = #{userId}")
  int deleteUser(@Param("userId") Long userId);
}
