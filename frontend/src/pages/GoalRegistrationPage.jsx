// src/pages/GoalRegistrationPage.jsx
import React, { useState } from 'react';
import { TextField, Button, Typography, Container } from '@mui/material';
import axios from 'axios';

const GoalRegistrationPage = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [deadline, setDeadline] = useState('');
  const [error, setError] = useState(null); // エラーハンドリング用の状態
  const [successMessage, setSuccessMessage] = useState(''); // 成功メッセージ用の状態

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (title && description && deadline) {
      try {
        const userId = localStorage.getItem('userId');
        const response = await axios.post('http://localhost:8080/api/goals', {
          userId,
          title,
          description,
          targetDate: deadline,
        });
        console.log('登録成功:', response.data);
        setSuccessMessage('目標が正常に登録されました！'); // 成功メッセージを設定
        setTitle('');
        setDescription('');
        setDeadline('');
        setError(null); // エラーをクリア
      } catch (err) {
        console.error('登録失敗:', err);
        setError('目標の登録に失敗しました。'); // エラーメッセージを設定
        setSuccessMessage(''); // 成功メッセージをクリア
      }
    }
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" gutterBottom sx={{ marginTop: 4 }}>
        新しい目標を登録
      </Typography>

      {error && <Typography color="error">{error}</Typography>} {/* エラーメッセージの表示 */}
      {successMessage && <Typography color="primary">{successMessage}</Typography>} {/* 成功メッセージの表示 */}

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
          value={deadline}
          onChange={(e) => setDeadline(e.target.value)}
          margin="normal"
          InputLabelProps={{
            shrink: true,
          }}
        />

        <Button type="submit" variant="contained" color="primary" fullWidth style={{ marginTop: '16px' }}>
          登録
        </Button>
      </form>
    </Container>
  );
};

export default GoalRegistrationPage;
