package com.ads.projetoteste.repository;

import com.ads.projetoteste.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByName(String name);

    public User update(Integer id, User user);

}
