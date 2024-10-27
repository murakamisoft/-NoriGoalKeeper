// src/pages/GoalEditPage.jsx
import React, { useEffect, useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Typography } from '@mui/material';
import axios from 'axios';

const GoalEditPage = () => {
  const { goalId } = useParams(); // URLから目標IDを取得
  const [goal, setGoal] = useState(null); // 目標データを保存するステート
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [targetDate, setTargetDate] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate(); // ナビゲーション用フック

  useEffect(() => {
    const fetchGoal = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/goals/${goalId}`);
        setGoal(response.data);
        setTitle(response.data.title);
        setDescription(response.data.description);
        setTargetDate(response.data.targetDate);
      } catch (error) {
        console.error("目標データの取得に失敗:", error);
        setError("目標データを取得できませんでした。");
      }
    };

    if (goalId) {
      fetchGoal();
    }
  }, [goalId]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const userId = localStorage.getItem('userId'); // ローカルストレージからユーザーIDを取得
      const response = await axios.put(`http://localhost:8080/api/goals`, {
        goalId,       // 編集する目標のID
        userId,      // ユーザーID
        title,       // 新しい目標タイトル
        description, // 新しい目標の説明
        targetDate,  // 新しい締め切り
        createdAt: goal.createdAt, // 既存の作成日時
        updatedAt: new Date().toISOString(), // 更新日時を現在に設定
      });
      console.log('更新成功:', response.data);
      navigate(`/goals/${goalId}`); // 編集後、目標詳細ページにリダイレクト
    } catch (err) {
      console.error('更新失敗:', err);
      setError("目標の更新に失敗しました。");
    }
  };

  if (!goal) {
    return (
      <Container>
        <Typography variant="h6" color="error">
          目標が見つかりません！
        </Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" gutterBottom>
        目標を編集
      </Typography>
      {error && <Typography color="error">{error}</Typography>}

      <form onSubmit={handleSubmit}>
        <TextField
          label="目標タイトル"
          fullWidth
          required
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          margin="normal"
        />
        <TextField
          label="目標の説明"
          fullWidth
          required
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          margin="normal"
          multiline
          rows={4}
        />
        <TextField
          label="締め切り"
          type="date"
          fullWidth
          required
          value={targetDate ? new Date(targetDate).toISOString().split('T')[0] : ''}
          onChange={(e) => setTargetDate(e.target.value)}
          margin="normal"
          InputLabelProps={{
            shrink: true,
          }}
        />
        <Button type="submit" variant="contained" color="primary" fullWidth style={{ marginTop: '16px' }}>
          更新
        </Button>
        <Button
          variant="outlined"
          component={Link}
          to={`/goals/${goalId}`}
          style={{ marginTop: '8px', marginLeft: '8px' }}
        >
          キャンセル
        </Button>
      </form>
    </Container>
  );
};

export default GoalEditPage;
