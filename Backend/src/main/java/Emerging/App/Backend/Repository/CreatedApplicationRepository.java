package Emerging.App.Backend.Repository;

import Emerging.App.Backend.Entities.CreatedApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreatedApplicationRepository extends JpaRepository<CreatedApplication, Integer> {

    @Query("SELECT u FROM CreatedApplication u WHERE u.user.userId = :id")
    Optional<CreatedApplication> findByCreatorUserId(@Param("id") int id);
}
