package Emerging.App.Backend.Repository;

import Emerging.App.Backend.Entities.CreatedApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatedApplicationRepository extends JpaRepository<CreatedApplication, Integer> {
}
