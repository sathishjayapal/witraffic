package me.sathish.witrafficreader.security.repos;

import me.sathish.witrafficreader.security.models.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SecurityUser, Integer> {
    Optional<SecurityUser> findByUserName(String userName);
}
