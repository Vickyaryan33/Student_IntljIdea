package com.example.controller;

import com.example.payload.StudentDto;
import com.example.service.StudentSevice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
   private StudentSevice studentSevice;

    public StudentController(StudentSevice studentSevice) {
        this.studentSevice = studentSevice;
    }
    @PostMapping("/add")
public ResponseEntity<?> addStudent(
            @Valid @RequestBody
        StudentDto studentDto ,
        BindingResult result){
        if(result.hasErrors())
        {return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
            };

        StudentDto dto=studentSevice.addStudent(studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

}
   // add more methods for other operations like update, delete, get, etc.
    //http://localhost:8080/api/v1/employee/delete?id=1
    @DeleteMapping("/delete")
    public ResponseEntity<String>deleteStudent(
            @RequestParam Long id){
        studentSevice.deleteStudent(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/update?id=1
    @PutMapping("/update")
    public ResponseEntity<StudentDto>updateStudent(
            @RequestParam Long id,
            @RequestBody StudentDto studentDto

    ){
       StudentDto dto= studentSevice.updateStudent(id , studentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/student/get?PageNo=0&PageSize=5
    @GetMapping("/get")
    public ResponseEntity<List<StudentDto>> getALLStudents(
           @RequestParam(name = "PageNo", defaultValue = "0", required = false) int pageNo,
           @RequestParam(name = "PageSize", defaultValue = "5", required = false) int pageSize
    ){

        List<StudentDto> list=studentSevice.getAllStudents( pageNo, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
    //http://localhost:8181/api/v1/student/studentId/1

    @GetMapping("/studentId/{id}")
    public ResponseEntity<StudentDto> getEmployeeById(@PathVariable long id){

        StudentDto studentDto=  studentSevice.getStudentById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

}
