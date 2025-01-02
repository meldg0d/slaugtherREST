package via.pro3.slaugtherhouserest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import via.pro3.slaugtherhouserest.entites.Animal;
import via.pro3.slaugtherhouserest.entites.AnimalPart;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    Animal findByAnimalId(UUID animalId);
    Animal findByAnimalPart(AnimalPart animalPart);
}
