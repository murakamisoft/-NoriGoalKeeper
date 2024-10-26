# NoriGoalKeeper - バックエンド

NoriGoalKeeperのバックエンドは、Spring Bootで構築されたRESTful APIです。目標設定と進捗管理のためのAPIエンドポイントを提供し、データベースとしてPostgreSQLを使用しています。

## 目次
- [NoriGoalKeeper - バックエンド](#norigoalkeeper---バックエンド)
  - [目次](#目次)
  - [機能概要](#機能概要)
  - [技術スタック](#技術スタック)
  - [セットアップ手順](#セットアップ手順)
    - [前提条件](#前提条件)
    - [手順](#手順)
  - [環境設定](#環境設定)
  - [APIエンドポイント](#apiエンドポイント)
    - [目標管理](#目標管理)
    - [タスク管理](#タスク管理)
  - [今後の計画](#今後の計画)
  - [ライセンス](#ライセンス)

---

## 機能概要
- **目標管理**: 目標の作成・更新・削除・取得。
- **タスク管理**: 目標達成に必要なタスクの管理。
- **進捗追跡**: 達成度や進捗率を計算し、フィードバックとして提供。
- **ユーザー認証**: 基本的なユーザー登録と認証（今後実装予定）。

## 技術スタック
- **フレームワーク**: Spring Boot
- **データベース**: PostgreSQL
- **ORM**: MyBatis
- **セキュリティ**: Spring Security（今後の実装予定）

## セットアップ手順

### 前提条件
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 以上
- [PostgreSQL](https://www.postgresql.org/) データベース

### 手順

1. リポジトリをクローンします。
   ```bash
   git clone https://github.com/yourusername/NoriGoalKeeper.git
   cd NoriGoalKeeper/backend
   ```

2. **PostgreSQLデータベースの設定**  
   - PostgreSQLで`norigoalkeeper`データベースを作成します。
   - `src/main/resources/application.yml`に以下のようにデータベース設定を追加してください。
     ```yaml
     spring:
       datasource:
         url: jdbc:postgresql://localhost:5432/norigoalkeeper
         username: your_db_username
         password: your_db_password
       mybatis:
         config-location: classpath:mybatis-config.xml
         mapper-locations: classpath:mappers/*.xml
         type-aliases-package: com.example.norigoalkeeper.domain
     ```

3. **アプリケーションの起動**
   ```bash
   ./gradlew bootRun
   ```

4. 正常に起動したら、`http://localhost:8080`でアクセスできます。

## 環境設定
- **アプリケーションのポート**: `8080`
- **データベース設定**: `application.yml` に記載

## APIエンドポイント

### 目標管理
- `GET /api/goals` - 全目標の一覧を取得
- `POST /api/goals` - 新しい目標を作成
- `GET /api/goals/{id}` - 特定の目標を取得
- `PUT /api/goals/{id}` - 目標を更新
- `DELETE /api/goals/{id}` - 目標を削除

### タスク管理
- `GET /api/goals/{goalId}/tasks` - 目標に紐づくタスクの一覧を取得
- `POST /api/goals/{goalId}/tasks` - 目標に紐づく新しいタスクを追加
- `PUT /api/goals/{goalId}/tasks/{taskId}` - タスクを更新
- `DELETE /api/goals/{goalId}/tasks/{taskId}` - タスクを削除

## 今後の計画
- **ユーザー認証**: Spring Securityを使用してユーザー登録とログインを実装予定。
- **進捗分析機能**: ユーザーが目標達成のペースや進捗状況を分析できるようにする予定。
- **通知機能**: タスクのリマインダーを送信する機能を追加予定。

## ライセンス
このプロジェクトはMITライセンスのもとで公開されています。
