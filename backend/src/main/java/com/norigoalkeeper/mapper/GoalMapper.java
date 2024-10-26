package com.norigoalkeeper.mapper;

import com.norigoalkeeper.model.Goal;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Goalエンティティに対するデータベース操作を行うマッパーインターフェース。
 */
@Mapper
public interface GoalMapper {

  /**
   * 新しいゴールを挿入します。
   *
   * @param goal ゴール情報
   * @return 挿入された行数
   */
  @Insert("INSERT INTO t_goals (user_id, title, description, target_date, created_at, updated_at) " +
      "VALUES (#{userId}, #{title}, #{description}, #{targetDate}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
  @Options(useGeneratedKeys = true, keyProperty = "goalId")
  int insertGoal(Goal goal);

  /**
   * ゴールIDをもとにゴール情報を取得します。
   *
   * @param goalId ゴールID
   * @return ゴール情報
   */
  @Select("SELECT * FROM t_goals WHERE goal_id = #{goalId}")
  Goal findGoalById(Long goalId);

  /**
   * 指定されたユーザーIDに関連するすべてのゴールを取得します。
   *
   * @param userId ユーザーID
   * @return ゴール情報のリスト
   */
  @Select("SELECT * FROM t_goals WHERE user_id = #{userId}")
  List<Goal> findGoalsByUserId(Long userId);

  /**
   * ゴール情報を更新します。
   *
   * @param goal 更新するゴール情報
   * @return 更新された行数
   */
  @Update("UPDATE t_goals SET title = #{title}, description = #{description}, target_date = #{targetDate}, " +
      "updated_at = CURRENT_TIMESTAMP WHERE goal_id = #{goalId}")
  int updateGoal(Goal goal);

  /**
   * ゴールIDをもとにゴールを削除します。
   *
   * @param goalId ゴールID
   * @return 削除された行数
   */
  @Delete("DELETE FROM t_goals WHERE goal_id = #{goalId}")
  int deleteGoal(Long goalId);
}
