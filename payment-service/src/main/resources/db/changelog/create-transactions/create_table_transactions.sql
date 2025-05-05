CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    from_account UUID NOT NULL,
    to_account UUID NOT NULL,
    amount NUMERIC(15, 2) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20),
    FOREIGN KEY (from_account) REFERENCES accounts(id),
    FOREIGN KEY (to_account) REFERENCES accounts(id)
);