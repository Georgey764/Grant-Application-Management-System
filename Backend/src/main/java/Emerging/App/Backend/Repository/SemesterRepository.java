package Emerging.App.Backend.Repository;

import Emerging.App.Backend.Entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
}
