-- Flyway migration script to insert test users and their profiles

-- Insert test users into the 'users' table
INSERT INTO users (id, name, email, password) VALUES
                                                  (1, 'user1', 'user1@example.com', 'password123'),
                                                  (2, 'user2', 'user2@example.com', 'password123'),
                                                  (5, 'user3', 'user3@example.com', 'password123');

-- Insert user profiles into the 'profiles' table
INSERT INTO profiles (id, bio, phone_number, date_of_birth, loyalty_points) VALUES
                                                                                (1, 'Bio for user1', '123-456-7890', '1990-01-01', 5),  -- user1 with 5 loyalty points
                                                                                (2, 'Bio for user2', '987-654-3210', '1985-05-15', 10), -- user2 with 10 loyalty points
                                                                                (5, 'Bio for user3', '555-555-5555', '1995-09-30', 20); -- user3 with 20 loyalty points

-- Insert products into the 'products' table
INSERT INTO products (id, name, price)
VALUES (1, 'Product 1', 10.99),
       (2, 'Product 2', 19.99),
       (3, 'Product 3', 29.99);
