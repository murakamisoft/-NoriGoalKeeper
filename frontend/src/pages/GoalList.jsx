// src/pages/GoalList.jsx
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // useNavigateをインポート
import { Container, Typography, Button, List, ListItem, ListItemText, Divider } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete'; // ごみ箱アイコンをインポート
import axios from 'axios';

const GoalList = () => {
  const [goals, setGoals] = useState([]);
  const navigate = useNavigate(); // useNavigateフックを使用

  // APIから目標一覧を取得
  const fetchGoals = async () => {
    try {
      const userId = localStorage.getItem('userId');
      const response = await axios.get(`http://localhost:8080/api/goals/user/${userId}`);
      console.log("goals user response.data : " + JSON.stringify(response.data));
      setGoals(response.data);
    } catch (error) {
      console.error("Failed to fetch goals:", error);
    }
  };

  useEffect(() => {
    fetchGoals();
  }, []);

  // 目標削除処理
  const handleDelete = async (goalId) => {
    if (window.confirm('本当にこの目標を削除しますか？')) { // 確認ダイアログを追加
      try {
        await axios.delete(`http://localhost:8080/api/goals/${goalId}`); // 削除APIを呼び出す
        console.log(`Goal with ID ${goalId} deleted successfully.`);
        navigate('/goals'); // 一覧画面に遷移
      } catch (error) {
        console.error("Failed to delete goal:", error);
      }
    }
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom sx={{ marginTop: 4 }}>
        Goals
      </Typography>
      <Button
        variant="contained"
        color="primary"
        component={Link}
        to="/goals/new"
        sx={{ marginBottom: 2 }}
      >
        Create New Goal
      </Button>

      <List>
        {goals.map(goal => (
          <React.Fragment key={goal.goalId}>
            <ListItem
              component={Link} to={`/goals/${goal.goalId}`}
              onClick={(e) => {
                e.stopPropagation(); // アイコンがクリックされたときにリストアイテムのクリックイベントを停止
              }}
            >
              <ListItemText
                primary={goal.title}
                secondary={goal.description}
              />
              <Button
                onClick={(e) => {
                  e.stopPropagation(); // リストアイテムのクリックイベントを停止
                  handleDelete(goal.goalId);
                }}
                color="secondary"
                size="small" // 小さめのボタン
                sx={{ marginLeft: 2 }} // アイコンの間隔を調整
              >
                <DeleteIcon />
              </Button>
            </ListItem>
            <Divider />
          </React.Fragment>
        ))}
      </List>
    </Container>
  );
};

export default GoalList;
