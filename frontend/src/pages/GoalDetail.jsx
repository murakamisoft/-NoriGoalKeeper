// src/pages/GoalDetail.jsx
import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { Container, Typography, Button, CircularProgress } from '@mui/material';
import axios from 'axios';

const GoalDetail = () => {
  const { goalId } = useParams(); // URLパラメータから目標IDを取得
  const [goal, setGoal] = useState(null); // 目標データを保存するステート
  const [loading, setLoading] = useState(true); // ローディング状態を管理

  // APIから目標詳細を取得
  useEffect(() => {
    const fetchGoal = async () => {
      try {
        console.log("start2");
        const response = await axios.get(`http://localhost:8080/api/goals/${goalId}`);
        console.log("goalDetail : " + response.data);
        setGoal(response.data);
      } catch (error) {
        console.error("Failed to fetch goal:", error);
      } finally {
        setLoading(false); // ローディング完了
      }
    };
    if (goalId) {
      console.log("start1");
      fetchGoal();
    } else {
      console.log("not start1 ; " + goalId);
    }
  }, [goalId]);

  console.log("aaa : " + goalId);
  if (loading) {
    return (
      <Container>
        <CircularProgress />
      </Container>
    ); // ローディング中はスピナーを表示
  }

  if (!goal) {
    return (
      <Container>
        <Typography variant="h6" color="error">
          Goal not found!
        </Typography>
      </Container>
    ); // 目標が見つからない場合のメッセージ
  }

  return (
    <Container>
      <Typography variant="h4" gutterBottom sx={{ marginTop: 4 }}>
        {goal.title}
      </Typography>
      <Typography variant="body1" gutterBottom>
        {goal.description || 'No description available.'}
      </Typography>
      <Typography variant="subtitle1">
        Target Date: {goal.targetDate ? new Date(goal.targetDate).toLocaleDateString() : 'N/A'}
      </Typography>

      <Button
        variant="contained"
        color="primary"
        component={Link}
        to={`/goals/${goal.goalId}/edit`}
        sx={{ marginTop: 2 }}
      >
        Edit Goal
      </Button>
      <Button
        variant="contained"
        color="secondary"
        component={Link}
        to="/goals"
        sx={{ marginTop: 2, marginLeft: 2 }}
      >
        Back to Goals
      </Button>
    </Container>
  );
};

export default GoalDetail;
