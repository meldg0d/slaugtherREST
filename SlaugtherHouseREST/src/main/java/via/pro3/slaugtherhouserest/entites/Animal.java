package via.pro3.slaugtherhouserest.entites;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    private UUID id;
    private Double weight;
    private Timestamp registrationDate;

}
