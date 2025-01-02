package via.pro3.slaugtherhouserest.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import via.pro3.slaugtherhouserest.entites.enums.PartType;
import java.util.UUID;

@Entity
public class Tray {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PartType partType;

    private Double currentWeight;

    private Double maxCapacity;

    // Static constant for max capacity
    public static final double MAX_CAPACITY = 100.0;

    // Constructors

    public Tray() {
    }

    public Tray(UUID id, PartType partType, Double currentWeight, Double maxCapacity) {
        this.id = id;
        this.partType = partType;
        this.currentWeight = currentWeight;
        this.maxCapacity = maxCapacity;
    }

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
