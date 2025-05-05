CREATE TABLE interest_accruals (
                                   id UUID PRIMARY KEY,
                                   account_id UUID NOT NULL,
                                   old_balance NUMERIC(15, 2) NOT NULL,
                                   interest_amount NUMERIC(15, 2) NOT NULL,
                                   new_balance NUMERIC(15, 2) NOT NULL,
                                   accrued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
