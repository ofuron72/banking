CREATE TABLE user_emails (
                             id UUID PRIMARY KEY,
                             user_id UUID NOT NULL REFERENCES users(id),
                             email VARCHAR(255) NOT NULL UNIQUE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);