CREATE TABLE accounts (
                          id UUID PRIMARY KEY,
                          user_id UUID NOT NULL UNIQUE REFERENCES users(id),
                          balance NUMERIC(15, 2) NOT NULL,
                          initial_deposit NUMERIC(15, 2) NOT NULL,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
