package via.pro3.slaugtherhouserest.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.slaugtherhouserest.entites.AnimalPart;
import via.pro3.slaugtherhouserest.entites.enums.PartType;
import via.pro3.slaugtherhouserest.entites.Tray;
import via.pro3.slaugtherhouserest.repository.AnimalPartRepository;
import via.pro3.slaugtherhouserest.repository.TrayRepository;

import java.util.UUID;

@RestController
@RequestMapping("/station2")
public class CuttingController {

    @Autowired
    private AnimalPartRepository animalPartRepository;

    @Autowired
    private TrayRepository trayRepository;

    @PostMapping("/cut")
    public AnimalPart cutAnimal(@RequestBody AnimalPartRequest request) {
        // Create AnimalPart
        AnimalPart part = new AnimalPart();
        part.setId(UUID.randomUUID());
        part.setAnimalId(request.getAnimalId());
        part.setPartType(request.getPartType());
        part.setWeight(request.getWeight());

        // Find or create a Tray
        Tray tray = trayRepository.findFirstByPartTypeAndCurrentWeightLessThan(
                request.getPartType(), Tray.MAX_CAPACITY);

        if (tray == null) {
            tray = new Tray();
            tray.setId(UUID.randomUUID());
            tray.setPartType(request.getPartType());
            tray.setCurrentWeight(0.0);
            tray.setMaxCapacity(Tray.MAX_CAPACITY); // Use the static constant
            trayRepository.save(tray);
        }

        // Update Tray
        tray.setCurrentWeight(tray.getCurrentWeight() + part.getWeight());
        trayRepository.save(tray);

        // Set Tray ID in AnimalPart
        part.setTrayId(tray.getId());
        return animalPartRepository.save(part);
    }
}

@Getter
@Setter
class AnimalPartRequest {
    private UUID animalId;
    private PartType partType;
    private Double weight;

}
