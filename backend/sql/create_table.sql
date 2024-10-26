-- Project Name : noname
-- Date/Time    : 2024/10/26 12:33:48
-- Author       : nori
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- ユーザーマスタ
DROP TABLE if exists m_users CASCADE;

CREATE TABLE m_users (
  user_id serial NOT NULL
  , username character varying(50) NOT NULL
  , email character varying(100) NOT NULL
  , password character varying(255) NOT NULL
  , created_at timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP
  , updated_at timestamp(6) without time zone
  , CONSTRAINT m_users_PKC PRIMARY KEY (user_id)
) ;

ALTER TABLE m_users ADD CONSTRAINT m_users_email_key
  UNIQUE (email) ;

-- 目標
DROP TABLE if exists t_goals CASCADE;

CREATE TABLE t_goals (
  goal_id serial NOT NULL
  , user_id integer NOT NULL
  , title character varying(100) NOT NULL
  , description text
  , target_date date
  , created_at timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP
  , updated_at timestamp(6) without time zone
  , CONSTRAINT t_goals_PKC PRIMARY KEY (goal_id)
) ;

-- 進捗
DROP TABLE if exists t_progress CASCADE;

CREATE TABLE t_progress (
  progress_id serial NOT NULL
  , goal_id integer NOT NULL
  , recorded_date date NOT NULL
  , progress_rate numeric
  , comment text
  , created_at timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP
  , updated_at timestamp(6) without time zone
  , CONSTRAINT t_progress_PKC PRIMARY KEY (progress_id)
) ;

-- タスク
DROP TABLE if exists t_tasks CASCADE;

CREATE TABLE t_tasks (
  task_id serial NOT NULL
  , goal_id integer NOT NULL
  , title character varying(100) NOT NULL
  , description text
  , status integer DEFAULT 0
  , due_date date
  , created_at timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP
  , updated_at timestamp(6) without time zone
  , CONSTRAINT t_tasks_PKC PRIMARY KEY (task_id)
) ;

COMMENT ON TABLE m_users IS 'ユーザーマスタ';
COMMENT ON COLUMN m_users.user_id IS 'ユーザーID';
COMMENT ON COLUMN m_users.username IS 'ユーザー名';
COMMENT ON COLUMN m_users.email IS 'メールアドレス';
COMMENT ON COLUMN m_users.password IS 'パスワード';
COMMENT ON COLUMN m_users.created_at IS '作成日';
COMMENT ON COLUMN m_users.updated_at IS '更新日';

COMMENT ON TABLE t_goals IS '目標';
COMMENT ON COLUMN t_goals.goal_id IS '目標ID';
COMMENT ON COLUMN t_goals.user_id IS 'ユーザーID';
COMMENT ON COLUMN t_goals.title IS '目標名称';
COMMENT ON COLUMN t_goals.description IS '目標詳細';
COMMENT ON COLUMN t_goals.target_date IS '目標達成予定日';
COMMENT ON COLUMN t_goals.created_at IS '作成日';
COMMENT ON COLUMN t_goals.updated_at IS '更新日';

COMMENT ON TABLE t_progress IS '進捗';
COMMENT ON COLUMN t_progress.progress_id IS '進捗ID';
COMMENT ON COLUMN t_progress.goal_id IS '目標ID';
COMMENT ON COLUMN t_progress.recorded_date IS '進捗記録日';
COMMENT ON COLUMN t_progress.progress_rate IS '進捗の割合';
COMMENT ON COLUMN t_progress.comment IS 'コメント';
COMMENT ON COLUMN t_progress.created_at IS '作成日';
COMMENT ON COLUMN t_progress.updated_at IS '更新日';

COMMENT ON TABLE t_tasks IS 'タスク';
COMMENT ON COLUMN t_tasks.task_id IS 'タスクID';
COMMENT ON COLUMN t_tasks.goal_id IS '目標ID';
COMMENT ON COLUMN t_tasks.title IS 'タスク名称';
COMMENT ON COLUMN t_tasks.description IS 'タスク詳細';
COMMENT ON COLUMN t_tasks.status IS 'タスク進捗状況';
COMMENT ON COLUMN t_tasks.due_date IS 'タスク完了予定日';
COMMENT ON COLUMN t_tasks.created_at IS '作成日';
COMMENT ON COLUMN t_tasks.updated_at IS '更新日';

