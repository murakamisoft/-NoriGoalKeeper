// src/pages/Dashboard.jsx
import React, { useEffect, useState } from 'react';
import { Container, Typography, Grid, Card, CardContent, LinearProgress } from '@mui/material';
import axios from 'axios';

const Dashboard = () => {
  const [goals, setGoals] = useState([]);

  useEffect(() => {
    // APIから目標データを取得
    axios.get('http://localhost:8080/api/goals/user/1')
      .then(response => {
        setGoals(response.data);
      })
      .catch(error => {
        console.error('目標データの取得に失敗しました:', error);
      });
  }, []);

  return (
    <Container>
      <Typography variant="h4" gutterBottom>
        ダッシュボード
      </Typography>
      <Grid container spacing={3}>
        {goals.map(goal => (
          <Grid item xs={12} sm={6} md={4} key={goal.goalId}>
            <Card>
              <CardContent>
                <Typography variant="h6">
                  {goal.title}
                </Typography>
                <Typography color="textSecondary" gutterBottom>
                  目標達成予定日: {goal.target_date}
                </Typography>
                <Typography variant="body2" paragraph>
                  {goal.description}
                </Typography>
                <LinearProgress variant="determinate" value={goal.progress_rate || 0} />
                <Typography color="textSecondary" align="right">
                  進捗率: {goal.progress_rate || 0}%
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default Dashboard;
