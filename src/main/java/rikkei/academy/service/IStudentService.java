package rikkei.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rikkei.academy.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();
    Page<Student> findAll(Pageable pageable);

    Optional<Student> findById(Long id);

    void save(Student student);

    void deleteById(Long id);
    List<Student> findByNameContaining(String name);
    List<Student> findByNameFull(@Param("name") String name);

}
