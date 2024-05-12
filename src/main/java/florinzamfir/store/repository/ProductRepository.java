package florinzamfir.store.repository;

import florinzamfir.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Product save(Product product);


    Optional<Product> findById(Long id);


    void deleteById(Long id);
}