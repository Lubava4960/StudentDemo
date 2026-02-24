CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- Убедитесь, что расширение для генерации UUID включено

CREATE TABLE IF NOT EXISTS app_user
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    student_id UUID,
    created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE
);