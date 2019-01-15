package com.gusuran.authentication.repository.api;

import com.gusuran.authentication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
