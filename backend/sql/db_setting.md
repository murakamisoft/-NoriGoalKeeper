# データベース設定

## 概要
このドキュメントでは、NoriGoalKeeperプロジェクトのためのPostgreSQLデータベースの設定手順を説明します。

## 手順

### 1. データベースの作成

まず、PostgreSQLでデータベースを作成します。

```sql
-- データベースを作成
CREATE DATABASE norigoalkeeper;
```

### 2. アプリ用ユーザーの作成

次に、アプリ用のユーザーを作成し、データベースへの接続権限を付与します。

```sql
-- アプリ用のユーザーを作成
CREATE USER nori_goalkeeper_user WITH PASSWORD 'pass';

-- データベースへの接続権限を付与
GRANT CONNECT ON DATABASE norigoalkeeper TO nori_goalkeeper_user;
```

### 3. データベース接続後の権限設定

PostgreSQLのコマンドラインから、作成したデータベースに接続します。

```sql
\c norigoalkeeper;  -- norigoalkeeperデータベースに接続
```

その後、以下のSQLを実行します。

```sql
-- テーブルへの権限を付与
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO nori_goalkeeper_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO nori_goalkeeper_user;
```

### 4. ユーザーをスーパーユーザーにする

最後に、`nori_goalkeeper_user` をスーパーユーザーにします。

```sql
-- nori_goalkeeper_userをスーパーユーザーにする
ALTER USER nori_goalkeeper_user WITH SUPERUSER;
```

## SQL スクリプトの全体

以下は、上記の手順をまとめたSQLスクリプトです。

```sql
-- データベースを作成
CREATE DATABASE norigoalkeeper;

-- アプリ用のユーザーを作成
CREATE USER nori_goalkeeper_user WITH PASSWORD 'pass';

-- データベースへの接続権限を付与
GRANT CONNECT ON DATABASE norigoalkeeper TO nori_goalkeeper_user;

-- 作成したデータベースに接続する
\c norigoalkeeper;  -- ここでデータベースに接続します。

-- テーブルへの権限を付与
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO nori_goalkeeper_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO nori_goalkeeper_user;

-- nori_goalkeeper_userをスーパーユーザーにする
ALTER USER nori_goalkeeper_user WITH SUPERUSER;
```

## 注意事項
- スーパーユーザー権限は強力な権限であるため、適切なユーザー管理を行ってください。
- データベース名やユーザー名、パスワードは必要に応じて変更してください。
- 