CREATE TABLE wishlist
(
    product_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT pk_wishlist PRIMARY KEY (product_id, user_id),
    CONSTRAINT fk_wishlist_product FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_wishlist_user FOREIGN KEY (user_id) REFERENCES users (id)
);


