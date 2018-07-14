package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        public Page<Department> findAll(Pageable pageable){return departmentRepository.findAll(pageable);}

        @Override
        public Department findDepartmentById(Long id){
            return departmentRepository.findDepartmentById(id);
        }

        @Override
        public <S extends Department>S save(S department){
            return departmentRepository.save(department);
        }

        @Override
        public void deleteDepartmentById(Long id){
        departmentRepository.deleteDepartmentById(id);
    }


}
