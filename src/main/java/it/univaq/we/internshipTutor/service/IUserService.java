package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.User;
import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findUserById(Long id);

}
