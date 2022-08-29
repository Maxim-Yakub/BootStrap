package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value="SELECT user from User user where user.username LIKE  CONCAT('%',:keyword,'%')"
            + "OR user.email LIKE CONCAT('%',:keyword,'%')")
           // + "OR user.lastName LIKE CONCAT('%',:keyword,'%')")
    List<User> search(@Param("keyword") String keyword);

    @Query(value = "SELECT user FROM User user JOIN FETCH user.roles WHERE user.username = :username")
    User findByUsername(@Param("username") String username);

}