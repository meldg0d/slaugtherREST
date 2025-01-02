package via.pro3.slaugtherhouserest.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import via.pro3.slaugtherhouserest.entites.enums.PartType;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalPart {
    @Id
    private UUID id;
    private UUID animalId; // reference to Animal
    @Enumerated(EnumType.STRING)
    private PartType partType;

    private Double weight;
    private UUID trayId; // reference to Tray


}
