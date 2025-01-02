package via.pro3.slaugtherhouserest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import via.pro3.slaugtherhouserest.entites.Tray;
import via.pro3.slaugtherhouserest.entites.enums.PartType;

import java.util.UUID;

public interface TrayRepository extends JpaRepository<Tray, UUID> {
    Tray findFirstByPartTypeAndCurrentWeightLessThan(PartType partType, Double maxCapacity);

}
