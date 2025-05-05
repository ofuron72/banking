CREATE TABLE credentials (
                             id UUID PRIMARY KEY,
                             username VARCHAR(255) UNIQUE NOT NULL,
                             password_hash VARCHAR(255) NOT NULL,
                             user_id UUID UNIQUE NOT NULL, -- связь с user-service
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);