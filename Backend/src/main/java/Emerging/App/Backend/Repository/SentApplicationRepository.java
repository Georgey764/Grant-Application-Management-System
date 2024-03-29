package Emerging.App.Backend.Repository;

import Emerging.App.Backend.Entities.SentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentApplicationRepository extends JpaRepository<SentApplication, Integer> {

    @Query("SELECT u FROM SentApplication u WHERE u.sender.userId = :userId")
    List<SentApplication> getStudentApplicationsList(@Param("userId") int userId);
}
