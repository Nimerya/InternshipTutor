package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Role;
import it.univaq.we.internshipTutor.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> findAll(Pageable pageable){return roleRepository.findAll(pageable);}

    @Override
    public Role findRoleById(Long id){
        return roleRepository.findRoleById(id);
    }

    @Override
    public <S extends Role>S save(S role){
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id){ roleRepository.deleteRoleById(id); }



}
