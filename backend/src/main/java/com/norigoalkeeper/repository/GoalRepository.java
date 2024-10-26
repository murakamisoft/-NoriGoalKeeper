package com.norigoalkeeper.repository;

import com.norigoalkeeper.mapper.GoalMapper;
import com.norigoalkeeper.model.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ゴール情報に対するデータベース操作を行うリポジトリクラス。
 */
@Repository
public class GoalRepository {

  @Autowired
  private GoalMapper goalMapper;

  /**
   * 新しいゴールを登録します。
   *
   * @param goal ゴール情報
   * @return 登録されたゴールのID
   */
  public Long save(Goal goal) {
    goalMapper.insertGoal(goal);
    return goal.getGoalId();
  }

  /**
   * ゴールIDをもとにゴール情報を取得します。
   *
   * @param goalId ゴールID
   * @return ゴール情報
   */
  public Goal findById(Long goalId) {
    return goalMapper.findGoalById(goalId);
  }

  /**
   * 指定されたユーザーIDに関連するすべてのゴールを取得します。
   *
   * @param userId ユーザーID
   * @return ゴール情報のリスト
   */
  public List<Goal> findByUserId(Long userId) {
    return goalMapper.findGoalsByUserId(userId);
  }

  /**
   * ゴール情報を更新します。
   *
   * @param goal 更新するゴール情報
   * @return 更新件数
   */
  public int update(Goal goal) {
    return goalMapper.updateGoal(goal);
  }

  /**
   * ゴールIDをもとにゴールを削除します。
   *
   * @param goalId ゴールID
   * @return 削除件数
   */
  public int delete(Long goalId) {
    return goalMapper.deleteGoal(goalId);
  }
}
