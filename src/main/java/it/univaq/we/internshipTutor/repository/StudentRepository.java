package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

     List<Student> findAll();

     Page<Student> findAll(Pageable pageable);

     Student findStudentById(Long id);

     <S extends Student> S save(S student);

     void deleteStudentById(Long id);

}
