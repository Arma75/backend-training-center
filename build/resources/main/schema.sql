CREATE TABLE IF NOT EXISTS todo_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(1000),
    is_completed BOOLEAN,
    start_dt DATE,
    create_dt DATE,
    update_dt DATE
);