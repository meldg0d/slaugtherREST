package via.pro3.slaugtherhouserest.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import via.pro3.slaugtherhouserest.entites.enums.ProductType;

import java.util.List;
import java.util.UUID;

//Java Persistence API
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productName;

    @ElementCollection
    private List<UUID> trayIds;

    @ElementCollection
    private List<UUID> animalPartIds;

}
