package com.czarnecki.clinicservicesystem.user;

import org.springframework.data.repository.CrudRepository;

public interface SqlUserRepository extends UserRepository, CrudRepository<User, Integer> {
}
