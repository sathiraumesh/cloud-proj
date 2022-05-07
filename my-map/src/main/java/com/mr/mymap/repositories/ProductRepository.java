package com.mr.mymap.repositories;

import com.mr.mymap.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public ProductEntity save(ProductEntity product) {
        em.persist(product);
        return product;
    }

    public ProductEntity update(ProductEntity product) {
        em.persist(product);
        return product;
    }

    public Optional<ProductEntity> findProductByName(String name) {
        return em.createQuery("select p from product p " +
                        "where p.name = :name ", ProductEntity.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList().stream()
                .findFirst();
    }

    public Optional<ProductEntity> findProductByMerchantProductId(String merchantProductId) {
        return em.createQuery("select p from ProductEntity p " +
                        "where p.productId = :prdId ", ProductEntity.class)
                .setParameter("prdId", merchantProductId)
                .setMaxResults(1)
                .getResultList().stream()
                .findFirst();
    }

    public Optional<ProductEntity> findProductByProductId(Long id) {
        return em.createQuery("select p from ProductEntity p " +
                "where p.id = :id ", ProductEntity.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList().stream()
                .findFirst();
    }

    public List<ProductEntity> findProductsByName(String name) {
        return em.createQuery("select p from ProductEntity p " +
                "where lower(trim(p.name)) like :name", ProductEntity.class)
                .setParameter("name", "%"+name.toLowerCase().trim()+"%")
                .setMaxResults(5)
                .getResultList();
    }

    public List<ProductEntity> findProdudctsBymerchantId(BigInteger merchantId) {
        return  em.createQuery("select p from ProductEntity p " +
                "where p.merchantId = :mId", ProductEntity.class).
                setParameter("mId", merchantId).getResultList();
    }
}
