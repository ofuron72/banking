CREATE TABLE accounts (
                          id UUID PRIMARY KEY,            -- UUID аккаунта (генерируется автоматически)
                          user_id UUID UNIQUE NOT NULL,          -- UUID пользователя
                          balance NUMERIC(15, 2) NOT NULL CHECK (balance >= 0),  -- Текущий баланс пользователя
                          initial_deposit NUMERIC(15, 2) NOT NULL CHECK (initial_deposit >= 0),  -- Начальная сумма на счете (когда аккаунт был создан)
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Время создания аккаунта
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Время последнего обновления
);