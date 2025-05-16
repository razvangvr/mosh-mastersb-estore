-- Flyway migration script to insert test users and their profiles

-- Insert test users into the 'users' table
INSERT INTO users (id, name, email, password) VALUES
                                                  (1, 'user1', 'user1@example.com', 'password123'),
                                                  (2, 'user2', 'user2@example.com', 'password123'),
                                                  (5, 'user3', 'user3@example.com', 'password123');


