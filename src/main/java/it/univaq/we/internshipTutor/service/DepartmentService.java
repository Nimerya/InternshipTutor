package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService implements IDepartmentService{

        @Autowired
        private DepartmentRepository departmentRepository;

        @Override
        public List<Department> findAll(){
            return departmentRepository.findAll();
        }

        @Override
        public Department findDepartmentById(Long id){
            return departmentRepository.findDepartmentById(id);
        }

        @Override
        public <S extends Department>S save(S department){
            return departmentRepository.save(department);
        }

    }
