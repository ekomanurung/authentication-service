package com.gusuran.authentication.repository.api;

import com.gusuran.authentication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Long deleteByUsername(String username);
}
