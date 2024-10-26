package com.norigoalkeeper.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 目標情報を管理するためのモデルクラス。
 */
@Data
public class Goal {

  /**
   * 目標ID
   */
  private Long goalId;

  /**
   * ユーザーID
   */
  private Long userId;

  /**
   * 目標名称
   */
  private String title;

  /**
   * 目標詳細
   */
  private String description;

  /**
   * 目標達成予定日
   */
  private LocalDate targetDate;

  /**
   * 作成日時
   */
  private LocalDateTime createdAt;

  /**
   * 更新日時
   */
  private LocalDateTime updatedAt;
}
