package com.norigoalkeeper.controller;

import com.norigoalkeeper.model.Goal;
import com.norigoalkeeper.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ゴールに関するAPIを提供するコントローラクラス。
 */
@RestController
@RequestMapping("/goals")
@Validated
public class GoalController {

  @Autowired
  private GoalService goalService;

  /**
   * 新しいゴールを登録します。
   *
   * @param goal 登録するゴール情報
   * @return 登録したゴールのID
   */
  @PostMapping
  public ResponseEntity<Long> createGoal(@RequestBody Goal goal) {
    Long createdGoalId = goalService.createGoal(goal);
    return new ResponseEntity<>(createdGoalId, HttpStatus.CREATED);
  }

  /**
   * ゴールIDをもとにゴール情報を取得します。
   *
   * @param goalId ゴールID
   * @return ゴール情報
   */
  @GetMapping("/{goalId}")
  public ResponseEntity<Goal> getGoalById(@PathVariable Long goalId) {
    Goal goal = goalService.getGoalById(goalId);
    return goal != null ? new ResponseEntity<>(goal, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * 指定されたユーザーIDに関連するすべてのゴールを取得します。
   *
   * @param userId ユーザーID
   * @return ゴール情報のリスト
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Goal>> getGoalsByUserId(@PathVariable Long userId) {
    List<Goal> goals = goalService.getGoalsByUserId(userId);
    return new ResponseEntity<>(goals, HttpStatus.OK);
  }

  /**
   * ゴール情報を更新します。
   *
   * @param goal 更新するゴール情報
   * @return 更新結果
   */
  @PutMapping
  public ResponseEntity<Void> updateGoal(@RequestBody Goal goal) {
    int updatedCount = goalService.updateGoal(goal);
    return updatedCount > 0 ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * ゴールIDをもとにゴールを削除します。
   *
   * @param goalId ゴールID
   * @return 削除結果
   */
  @DeleteMapping("/{goalId}")
  public ResponseEntity<Void> deleteGoal(@PathVariable Long goalId) {
    int deletedCount = goalService.deleteGoal(goalId);
    return deletedCount > 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}