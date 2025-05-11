CREATE TABLE accounts (
                          id UUID PRIMARY KEY,
                          user_id UUID NOT NULL UNIQUE REFERENCES users(id),
                          balance NUMERIC(15, 2) NOT NULL CHECK (balance >= 0),
                          initial_deposit NUMERIC(15, 2) NOT NULL CHECK (initial_deposit >= 0),
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
