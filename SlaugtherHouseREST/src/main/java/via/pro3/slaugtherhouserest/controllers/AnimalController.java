package via.pro3.slaugtherhouserest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import via.pro3.slaugtherhouserest.entites.Animal;
import via.pro3.slaugtherhouserest.repository.AnimalRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController // ioc
@RequestMapping("/station1/animals")
public class AnimalController {
    @Autowired //dependency injection
    private AnimalRepository animalRepository;

    @PostMapping // To define an endpoint that handles POST requests.
    public Animal registerAnimal(@RequestBody Animal animal) {
        animal.setId(UUID.randomUUID());
        animal.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
        return animalRepository.save(animal);
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }
}
