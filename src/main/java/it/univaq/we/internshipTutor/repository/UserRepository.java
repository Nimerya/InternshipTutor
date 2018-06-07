package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> findAll();

    public User findBy(Long id);
}
