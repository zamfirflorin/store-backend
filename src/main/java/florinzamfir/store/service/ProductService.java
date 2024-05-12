package florinzamfir.store.service;

import florinzamfir.store.model.Product;
import florinzamfir.store.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(Product product) {
        log.info("Adding a new product: {}", product);
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            log.error("Error occurred while adding product: {}", e.getMessage());
            throw new IllegalArgumentException("Product already exists or invalid data provided");
        }
    }

    @Transactional
    public Product getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);
        return productRepository.findById(id).orElseThrow(() -> {
            log.error("Product not found with ID: {}", id);
            return new NoSuchElementException("Product not found with ID: " + id);
        });
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProductById(Long id) {
        log.info("Deleting product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            log.error("Product not found with ID: {}", id);
            throw new NoSuchElementException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public Product updateProduct(Product product) {
        log.info("Updating product: {}", product);
        if (!productRepository.existsById(product.getId())) {
            log.error("Product not found with ID: {}", product.getId());
            throw new NoSuchElementException("Product not found with ID: " + product.getId());
        }
        return productRepository.save(product);
    }
}