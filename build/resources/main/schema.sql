DROP TABLE todo_item;
CREATE TABLE IF NOT EXISTS todo_item (
    seq SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(1000),
    completed BOOLEAN,
    startDt DATE,
    createDt DATE,
    updateDt DATE
);