package rikkei.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.Student;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository <Student, Long> {
    List<Student> findByNameContaining(String name);
    @Query("select st from Student as st where st.name like concat('%',:name,'%') ")
    List<Student> findByNameFull(@Param("name") String name);

}
