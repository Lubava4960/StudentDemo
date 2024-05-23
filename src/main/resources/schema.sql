CREATE TABLE IF NOT EXISTS students (
         id UUID PRIMARY KEY,
         surname VARCHAR(255),
         course INTEGER,
                  birthday DATE
              );
              INSERT INTO students (id, surname, course, birthday)
              VALUES (UUID_GENERATE_V4(), 'Иванов', 2, '2000-01-01');