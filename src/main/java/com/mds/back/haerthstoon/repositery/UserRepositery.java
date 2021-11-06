package com.mds.back.haerthstoon.repositery;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.back.haerthstoon.domain.User;

public interface UserRepositery extends CrudRepository<User, Long> {
	List<User> findByEmail(String email);
	User findById(long id);
}
