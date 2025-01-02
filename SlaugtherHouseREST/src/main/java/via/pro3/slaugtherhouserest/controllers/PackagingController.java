package via.pro3.slaugtherhouserest.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.slaugtherhouserest.entites.Product;
import via.pro3.slaugtherhouserest.entites.enums.ProductType;
import via.pro3.slaugtherhouserest.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/station3/products")
public class PackagingController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/package")
    public Product createProduct(@RequestBody ProductRequest request) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setProductType(request.getProductType());

        if (request.getProductType() == ProductType.PACKAGE) {
            // For packages from trays
            product.setTrayIds(request.getTrayIds());
        } else if (request.getProductType() == ProductType.HALF_ANIMAL) {
            // For half an animal, select parts
            product.setAnimalPartIds(request.getAnimalPartIds());
        }

        return productRepository.save(product);
    }
}


@Getter
@Setter
class ProductRequest {
    private ProductType productType;
    private List<UUID> trayIds;
    private List<UUID> animalPartIds;

}
