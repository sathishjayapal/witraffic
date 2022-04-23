package me.sathish.witrafficalerts.security.repos;

import me.sathish.witrafficalerts.security.models.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SecurityUser, Integer> {
    Optional<SecurityUser> findByUserName(String userName);
}
