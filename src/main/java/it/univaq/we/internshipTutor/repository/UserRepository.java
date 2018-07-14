package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    User findUserById(Long id);

    <S extends User> S save(S user);

    void deleteUserById(Long id);
}
