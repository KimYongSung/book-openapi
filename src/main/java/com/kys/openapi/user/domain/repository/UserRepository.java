package com.kys.openapi.user.domain.repository;

import com.kys.openapi.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User 레파지토리
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
