package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    User findUserById(Long id);

    <S extends User> S save(S user);

    void deleteUserById(Long id);

}
