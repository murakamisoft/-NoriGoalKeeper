// src/components/Header.jsx
import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          NoriGoalKeeper
        </Typography>
        <Button color="inherit" component={Link} to="/">
          ダッシュボード
        </Button>
        <Button color="inherit" component={Link} to="/goals">
          目標
        </Button>
        <Button color="inherit" component={Link} to="/goals/register">
          目標追加
        </Button>
        <Button color="inherit" component={Link} to="/login">
          ログイン
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
