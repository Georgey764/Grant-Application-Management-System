package Emerging.App.Backend.Repository;

import Emerging.App.Backend.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    @Query("SELECT u FROM Users u WHERE u.username = :name")
    Optional<Users> findByUsername(@Param("name") String name);
}
