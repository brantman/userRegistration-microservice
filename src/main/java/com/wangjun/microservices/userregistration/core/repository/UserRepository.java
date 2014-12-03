package com.wangjun.microservices.userregistration.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wangjun.microservices.userregistration.core.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
