package com.ooad.InClassComp.doa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ooad.InClassComp.model.User;

@Transactional
public interface UserDAO extends CrudRepository<User, Long>{	
	public List<User> findByUserName(String name);
	public List<User> findByType(Integer type);
	public List<User> findByAccepted(Boolean accepted);
	public List<User> findByEmail(String email);
}
