-- ユーザ
delete from m_users;
INSERT INTO m_users (username, email, password, created_at, updated_at) VALUES
('nori', 'user1@example.com', 'pass', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('masami', 'user2@example.com', 'pass', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('saki', 'user3@example.com', 'pass', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('hiroki', 'user4@example.com', 'pass', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 目標
delete from t_goals;
INSERT INTO t_goals (user_id, title, description, target_date, created_at, updated_at) VALUES
(1, 'スカイダイビング', '人生で一度は空から飛び降りる', '2025-07-01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, '料理バトル', '友人と料理バトルを開催し、優勝する', '2024-10-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'マラソンコスプレ', 'マラソンにコスプレで参加する', '2025-04-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '一週間サバイバル', '自然の中で一週間サバイバル生活を体験する', '2025-08-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '自作楽器', '自分で楽器を作り、演奏する', '2025-05-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'キャンプで一晩', '友達とキャンプをして一晩過ごす', '2025-06-10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, '週一アートデー', '毎週アートを楽しむ日を作り、何かを創作する', '2025-12-31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, '無限ループ読書', '1ヶ月間、毎日同じ本を読み続ける', '2025-09-01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, '仮装大会', 'ハロウィンに仮装大会で優勝する', '2024-10-31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, '新しい趣味を始める', 'この年のうちに5つの新しい趣味を始める', '2025-12-31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

