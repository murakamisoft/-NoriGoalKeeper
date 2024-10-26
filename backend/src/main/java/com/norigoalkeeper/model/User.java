package com.norigoalkeeper.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * ユーザー情報を表すクラス。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  /**
   * ユーザーを一意に識別するID。
   */
  private Long userId;

  /**
   * ユーザーの名前。
   */
  private String username;

  /**
   * ユーザーのメールアドレス。
   */
  private String email;

  /**
   * ユーザーのパスワード。
   */
  private String password;

  /**
   * アカウントが作成された日。
   */
  private LocalDateTime createdAt;

  /**
   * アカウントが更新された日。
   */
  private LocalDateTime updatedAt;
}
