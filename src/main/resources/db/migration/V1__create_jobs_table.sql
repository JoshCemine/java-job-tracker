CREATE TABLE jobs (
                      id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      company      VARCHAR(255) NOT NULL,
                      position     VARCHAR(255) NOT NULL,
                      status       VARCHAR(50)  NOT NULL DEFAULT 'APPLIED',
                      applied_date DATE,
                      created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);