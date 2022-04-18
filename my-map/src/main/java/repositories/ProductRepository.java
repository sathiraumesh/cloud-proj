package repositories;

import com.google.api.services.content.model.Product;
import entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    private ProductEntity save(ProductEntity product) {
        em.persist(product);
        return product;
    }

    private ProductEntity update(ProductEntity product) {
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
}
