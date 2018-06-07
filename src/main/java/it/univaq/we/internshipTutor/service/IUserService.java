package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.User;
import java.util.List;

public interface IUserService {

    public List<User> findAll();

    public User findBy(Long id);

}
