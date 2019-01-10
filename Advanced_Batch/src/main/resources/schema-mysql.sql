USE springbatch_advanced;
CREATE TABLE customers IF NOT EXISTS  (
    id INT(11) IDENTITY NOT NULL PRIMARY KEY,
    name NVARCHAR(45),
    date_of_birth DATE
)