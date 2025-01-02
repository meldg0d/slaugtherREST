package via.pro3.slaugtherhouserest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import via.pro3.slaugtherhouserest.entites.AnimalPart;

import java.util.List;
import java.util.UUID;

public interface AnimalPartRepository  extends JpaRepository<AnimalPart, UUID> {
    List<AnimalPart> findByAnimalId(UUID animalId);

}
