package com.norigoalkeeper.service;

import com.norigoalkeeper.model.Goal;
import com.norigoalkeeper.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ゴール情報を管理するサービスクラス。
 */
@Service
public class GoalService {

  @Autowired
  private GoalRepository goalRepository;

  /**
   * 新しいゴールを登録します。
   *
   * @param goal 登録するゴール情報
   * @return 登録したゴールのID
   */
  public Long createGoal(Goal goal) {
    return goalRepository.save(goal);
  }

  /**
   * ゴールIDをもとにゴール情報を取得します。
   *
   * @param goalId ゴールID
   * @return ゴール情報
   */
  public Goal getGoalById(Long goalId) {
    return goalRepository.findById(goalId);
  }

  /**
   * 指定されたユーザーIDに関連するすべてのゴールを取得します。
   *
   * @param userId ユーザーID
   * @return ゴール情報のリスト
   */
  public List<Goal> getGoalsByUserId(Long userId) {
    return goalRepository.findByUserId(userId);
  }

  /**
   * ゴール情報を更新します。
   *
   * @param goal 更新するゴール情報
   * @return 更新件数
   */
  public int updateGoal(Goal goal) {
    return goalRepository.update(goal);
  }

  /**
   * ゴールIDをもとにゴールを削除します。
   *
   * @param goalId ゴールID
   * @return 削除件数
   */
  public int deleteGoal(Long goalId) {
    return goalRepository.delete(goalId);
  }
}
