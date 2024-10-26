-- ユーザー情報を管理するテーブル
CREATE TABLE m_users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE users IS 'アプリケーションのユーザー情報を保存するテーブル';
COMMENT ON COLUMN users.user_id IS 'ユーザーを一意に識別するID';
COMMENT ON COLUMN users.username IS 'ユーザーの名前';
COMMENT ON COLUMN users.email IS 'ユーザーのメールアドレス';
COMMENT ON COLUMN users.password IS 'ユーザーのパスワード';
COMMENT ON COLUMN users.created_at IS 'アカウントが作成された日';

-- ユーザーが設定する目標を管理するテーブル
CREATE TABLE t_goals (
    goal_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    target_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

COMMENT ON TABLE goals IS '各ユーザーが設定する目標を管理するテーブル';
COMMENT ON COLUMN goals.goal_id IS '目標を一意に識別するID';
COMMENT ON COLUMN goals.user_id IS '目標の所有者であるユーザーを示すID';
COMMENT ON COLUMN goals.title IS '目標の名称';
COMMENT ON COLUMN goals.description IS '目標の詳細';
COMMENT ON COLUMN goals.created_at IS '目標が作成された日';
COMMENT ON COLUMN goals.target_date IS '目標を達成する予定の日';

-- 目標達成のために設定されたタスクを管理するテーブル
CREATE TABLE t_tasks (
    task_id SERIAL PRIMARY KEY,
    goal_id INTEGER NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status VARCHAR(20) DEFAULT '未着手',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date DATE,
    FOREIGN KEY (goal_id) REFERENCES goals(goal_id) ON DELETE CASCADE
);

COMMENT ON TABLE tasks IS '目標に関連付けられたタスクを管理するテーブル';
COMMENT ON COLUMN tasks.task_id IS 'タスクを一意に識別するID';
COMMENT ON COLUMN tasks.goal_id IS 'タスクが属する目標を示すID';
COMMENT ON COLUMN tasks.title IS 'タスクの名称';
COMMENT ON COLUMN tasks.description IS 'タスクの詳細';
COMMENT ON COLUMN tasks.status IS 'タスクの進捗状況';
COMMENT ON COLUMN tasks.created_at IS 'タスクが作成された日';
COMMENT ON COLUMN tasks.due_date IS 'タスクの完了予定日';

-- 目標に対する進捗状況を管理するテーブル
CREATE TABLE t_progress (
    progress_id SERIAL PRIMARY KEY,
    goal_id INTEGER NOT NULL,
    recorded_date DATE NOT NULL,
    progress_rate NUMERIC CHECK (progress_rate >= 0 AND progress_rate <= 100),
    comment TEXT,
    FOREIGN KEY (goal_id) REFERENCES goals(goal_id) ON DELETE CASCADE
);

COMMENT ON TABLE progress IS '目標に対する進捗状況を記録するテーブル';
COMMENT ON COLUMN progress.progress_id IS '進捗を一意に識別するID';
COMMENT ON COLUMN progress.goal_id IS '進捗が関連する目標を示すID';
COMMENT ON COLUMN progress.recorded_date IS '進捗が記録された日';
COMMENT ON COLUMN progress.progress_rate IS '目標に対する進捗の割合';
COMMENT ON COLUMN progress.comment IS '進捗に関するコメント';
