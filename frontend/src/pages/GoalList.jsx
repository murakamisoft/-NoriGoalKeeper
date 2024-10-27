// src/pages/GoalList.jsx
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Container, Typography, Button, List, ListItem, ListItemText, Divider } from '@mui/material';
import axios from 'axios';

const GoalList = () => {
  const [goals, setGoals] = useState([]);

  // APIから目標一覧を取得
  useEffect(() => {
    const fetchGoals = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/goals/user/1');
        console.log("goals user response.data : " + JSON.stringify(response.data));
        setGoals(response.data);
      } catch (error) {
        console.error("Failed to fetch goals:", error);
      }
    };
    fetchGoals();
  }, []);

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
            <ListItem button component={Link} to={`/goals/${goal.goalId}`}>
              <ListItemText
                primary={goal.title}
                secondary={goal.description}
              />
            </ListItem>
            <Divider />
          </React.Fragment>
        ))}
      </List>
    </Container>
  );
};

export default GoalList;
