package via.pro3.slaugtherhouserest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.slaugtherhouserest.entites.AnimalPart;
import via.pro3.slaugtherhouserest.entites.Product;
import via.pro3.slaugtherhouserest.repository.AnimalPartRepository;
import via.pro3.slaugtherhouserest.repository.ProductRepository;

import java.util.*;

@RestController
@RequestMapping("/recall")
public class RecallController {
    @Autowired
    private AnimalPartRepository animalPartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{animalId}")
    public List<Product> recallProducts(@PathVariable UUID animalId) {
        // Find all parts from the animal
        List<AnimalPart> parts = animalPartRepository.findByAnimalId(animalId);
        Set<UUID> partIds = new HashSet<>();
        for (AnimalPart part : parts) {
            partIds.add(part.getId());
        }

        // Find all products containing these parts
        List<Product> allProducts = productRepository.findAll();
        List<Product> affectedProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getAnimalPartIds() != null) {
                for (UUID partId : product.getAnimalPartIds()) {
                    if (partIds.contains(partId)) {
                        affectedProducts.add(product);
                        break;
                    }
                }
            }
            // Additional logic for trays can be added here
        }

        return affectedProducts;
    }
}
