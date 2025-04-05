package via.pro3.slaugtherhouserest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.slaugtherhouserest.entites.Animal;
import via.pro3.slaugtherhouserest.entites.AnimalPart;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    Optional<Animal> findById(UUID id);
    //Optional<Animal> findByPart(AnimalPart animalPart);
}
