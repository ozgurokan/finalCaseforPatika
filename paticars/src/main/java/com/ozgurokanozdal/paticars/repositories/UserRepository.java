package com.ozgurokanozdal.paticars.repositories;

import com.ozgurokanozdal.paticars.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
