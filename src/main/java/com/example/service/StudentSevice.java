package com.example.service;

import com.example.entity.Student;
import com.example.exception.ResourceNotFound;
import com.example.payload.StudentDto;
import com.example.repository.EmployeeRespository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSevice {
    private ModelMapper modelMapper;
    private EmployeeRespository employeeRespository;


    public StudentSevice( ModelMapper modelMapper,EmployeeRespository employeeRespository) {
        this.modelMapper = modelMapper;
        this.employeeRespository = employeeRespository;
    }
    StudentDto mapToDto(Student student) {
     StudentDto studentDto=modelMapper.map(student,StudentDto.class);
     return studentDto;
    }

    Student maptoEnitiy(StudentDto studentDto){
      Student student=modelMapper.map(studentDto,Student.class);
        return student;

    }

    public StudentDto addStudent(StudentDto Dto){
        Student student=maptoEnitiy(Dto);
        Student stu=employeeRespository.save(student);
        StudentDto studentDto=mapToDto(stu);
        return studentDto;
    }

    public void deleteStudent(Long id) {
        employeeRespository.deleteById(id);

    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student=maptoEnitiy(studentDto);
        student.setId(id);
        Student stu=employeeRespository.save(student);
        StudentDto dto=mapToDto(stu);
        return dto;



    }

    public List<StudentDto> getAllStudents( int PageNo, int pageSize) {
//        List<Student> students=employeeRespository.findAll();
        Pageable pageable = PageRequest.of(PageNo, pageSize);
        List<Student> students=employeeRespository.findAll(pageable).getContent();
        List<StudentDto> dtos=students.stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
        return dtos;
    }

    public StudentDto getStudentById(long id) {
       Student student=employeeRespository.findById(id).orElseThrow(
        ()-> new ResourceNotFound("Student not found: "+id)
        );
        StudentDto studentDto=mapToDto(student);
        return studentDto;
    }
}
