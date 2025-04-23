DELIMITER $$
CREATE PROCEDURE findProductsPriceRange(
    minPrice  DECIMAL(10, 2),
    maxPrice  DECIMAL(10, 2)
)
BEGIN
    SELECT p.id, p.name, p.price, p.category_id
    FROM products p
    WHERE p.price between minPrice and maxPrice
    order by p.name;
END $$

DELIMITER ;
