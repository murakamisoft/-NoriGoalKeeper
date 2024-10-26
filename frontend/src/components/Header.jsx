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
          DashBoard
        </Button>
        <Button color="inherit" component={Link} to="/goals">
          Goals
        </Button>
        <Button color="inherit" component={Link} to="/goals/create">
          Add Goal
        </Button>
        <Button color="inherit" component={Link} to="/login">
          Login
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
