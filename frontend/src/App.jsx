// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { CssBaseline } from '@mui/material';
import Dashboard from './pages/Dashboard';
import GoalList from './pages/GoalList';
import GoalDetail from './pages/GoalDetail';
import GoalRegistrationPage from './pages/GoalRegistrationPage';
import GoalEditPage from './pages/GoalEditPage';
import Header from './components/Header';

const App = () => {
  return (
    <Router>
      <CssBaseline />
      <Header />
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/goals" element={<GoalList />} />
        <Route path="/goals/:goalId" element={<GoalDetail />} />
        <Route path="/goals/register" element={<GoalRegistrationPage />} />
        <Route path="/goals/:goalId/edit" element={<GoalEditPage />} />
        {/* 必要に応じて他のページも追加 */}
      </Routes>
    </Router>
  );
};

export default App;
