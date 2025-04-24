package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    /**
     * We need the EntityManager because we're going to work directly with
     * Hibernate to create the Dynamic Query
     */
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Product> findByCriteria(String productName, BigDecimal minPrice, BigDecimal maxPrice) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        //FROM products
        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (productName != null) {
            //name like %name%
            predicates.add(cb.equal(root.get("name"), "%" + productName + "%"));
        }
        if (minPrice != null) {
            //Product.price >= minPrice
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            //price <= maxPrice
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).getResultList();
    }
}
