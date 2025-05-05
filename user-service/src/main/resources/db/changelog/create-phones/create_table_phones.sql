CREATE TABLE user_phones (
                             id UUID PRIMARY KEY,
                             user_id UUID NOT NULL REFERENCES users(id),
                             phone VARCHAR(20) NOT NULL UNIQUE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);