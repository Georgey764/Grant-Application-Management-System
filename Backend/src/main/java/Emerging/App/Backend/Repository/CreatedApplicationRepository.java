package Emerging.App.Backend.Repository;

import Emerging.App.Backend.Entities.CreatedApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatedApplicationRepository extends JpaRepository<CreatedApplication, Integer> {

    //For Students
    @Query("SELECT u FROM CreatedApplication u WHERE CONCAT(u.user.userDetails.firstName, ' ', u.user.userDetails.lastName) LIKE :query")
    List<CreatedApplication> findByQuery(@Param("query") String query);

    // For Faculty
    @Query("SELECT u FROM CreatedApplication u WHERE u.user.userId = :id")
    Optional<CreatedApplication> findByCreatorUserId(@Param("id") int id);
}
