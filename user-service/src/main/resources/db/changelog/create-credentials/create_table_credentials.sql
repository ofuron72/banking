CREATE TABLE credentials (
                             user_id UUID PRIMARY KEY REFERENCES users(id),
                             login VARCHAR(50) UNIQUE NOT NULL,
                             password_hash VARCHAR(255) NOT NULL
);