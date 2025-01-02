package via.pro3.slaugtherhouserest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import via.pro3.slaugtherhouserest.entites.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByProductId(UUID productId);
    Product findByProductName(String productName);
}
