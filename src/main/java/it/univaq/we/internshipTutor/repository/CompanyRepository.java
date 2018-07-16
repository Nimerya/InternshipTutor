package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.IBestCompanyProjection;
import it.univaq.we.internshipTutor.model.ICompanyStudentInternshipCountProjection;
import it.univaq.we.internshipTutor.model.IWorstCompanyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>{

    List<Company> findAll();

    Company findCompanyById(Long id);

    Page<Company> findAll(Pageable pageable);

    <S extends Company> S save(S company);

    void deleteCompanyById(Long id);

    @Query(value = "SELECT c.id as id, c.name as name, COUNT(si.student_id) as count " +
                   "FROM student_internship si " +
                   "LEFT JOIN internship i " +
                   "ON si.internship_id = i.id " +
                   "LEFT JOIN company c " +
                   "ON i.company_id = c.id " +
                   "WHERE si.accepted != 0 " +
                   "GROUP BY c.id " +
                   "ORDER BY count DESC " +
                   "LIMIT ?1", nativeQuery = true)
    List<ICompanyStudentInternshipCountProjection> companiesWithMostStudents(int limit);



    @Query(value = "SELECT AVG(si.review) as media, c.id as id, c.name as name" +
            "FROM student_internship si" +
            "LEFT JOIN internship i" +
            "ON si.internship_id=i.id" +
            "LEFT JOIN company c" +
            "ON i.company_id=c.id" +
            "GROUP BY c.id" +
            "ORDER BY media DESC" +
            "LIMIT ?1", nativeQuery = true)
    List<IBestCompanyProjection> bestCompanies(int limit);

    @Query(value = "SELECT AVG(si.review) as media, c.id as id, c.name as name" +
            "FROM student_internship si" +
            "LEFT JOIN internship i" +
            "ON si.internship_id=i.id" +
            "LEFT JOIN company c" +
            "ON i.company_id=c.id" +
            "GROUP BY c.id" +
            "ORDER BY media ASC" +
            "LIMIT ?1", nativeQuery = true)
    List<IWorstCompanyProjection> worstCompanies(int limit);

}