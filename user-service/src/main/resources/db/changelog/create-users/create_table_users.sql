CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       birth_date DATE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);