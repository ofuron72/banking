CREATE TABLE accounts (
                          id UUID PRIMARY KEY,            -- UUID аккаунта (генерируется автоматически)
                          user_id UUID NOT NULL,          -- UUID пользователя
                          balance NUMERIC(15, 2) NOT NULL,  -- Текущий баланс пользователя
                          initial_deposit NUMERIC(15, 2) NOT NULL,  -- Начальная сумма на счете (когда аккаунт был создан)
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Время создания аккаунта
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Время последнего обновления
);