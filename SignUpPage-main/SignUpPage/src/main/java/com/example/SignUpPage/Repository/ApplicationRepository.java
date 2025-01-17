package com.example.SignUpPage.Repository;
import com.example.SignUpPage.Model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);
    void deleteByUsername(String username);

    @Query(value = "SELECT * FROM application_user  WHERE username = ?1", nativeQuery = true)
    Optional<ApplicationUser> findByUsernameIfExists(String username);

    @Query(value = "SELECT * FROM application_user  WHERE username = ?1", nativeQuery = true)
    Optional<Boolean> checkUsernameIfExists(String username);

    @Query(value = "SELECT * FROM application_user  WHERE phone_number = ?1", nativeQuery = true)
    Optional<ApplicationUser> findByPhoneNumberIfExists(String phoneNumber);

}
