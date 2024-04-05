package Emerging.App.Backend.Repository;

import Emerging.App.Backend.Entities.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<MyUserDetails, Integer> {

}
