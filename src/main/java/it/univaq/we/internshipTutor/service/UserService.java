package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.univaq.we.internshipTutor.model.User;

import javax.transaction.Transactional;
import java.util.List;



@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id){
        return userRepository.findUserById(id);
    }

    @Override
    public <S extends User>S save(S user){
        return userRepository.save(user);
    }
}
