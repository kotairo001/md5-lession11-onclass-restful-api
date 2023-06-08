package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.dto.reponse.ResponseMessage;
import rikkei.academy.model.Student;
import rikkei.academy.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public ResponseEntity<?> showListStudent() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("create_success"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailStudent(@PathVariable Long id){
        if(!studentService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentService.findById(id).get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
        public ResponseEntity<?> updateStudent (@PathVariable Long id, @RequestBody Student student){
        Student student1 = studentService.findById(id).get();
        if(!studentService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setId(student1.getId());
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("update_success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deteleStudent (@PathVariable Long id){
        if(!studentService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("delete_success"), HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<?>showPageStudent(@PageableDefault(size=3, sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>(studentService.findAll(pageable),HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> showListSearchContaining(@PathVariable String name){
        return new ResponseEntity<>(studentService.findByNameContaining(name), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> showListSearchQuery(@RequestParam("name") String name){
        return new ResponseEntity<>(studentService.findByNameFull(name), HttpStatus.OK);

    }
}
