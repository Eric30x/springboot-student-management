package com.eric.student_system.controller;

import com.eric.student_system.common.Result;
import com.eric.student_system.dto.StudentRequest;
import com.eric.student_system.entity.Student;
import com.eric.student_system.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Result<List<Student>> findAll() {
        return Result.success(studentService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Student> findById(@PathVariable int id) {
        return Result.success(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Result<String>> add(
            @Valid @RequestBody StudentRequest request) {

        Student student = new Student(
                null,
                request.getName(),
                request.getAge()
        );

        studentService.add(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Result.success("添加成功"));
    }

    @PutMapping("/{id}")
    public Result<String> update(
            @PathVariable int id,
            @Valid @RequestBody StudentRequest request) {

        Student student = new Student(
                id,
                request.getName(),
                request.getAge()
        );

        studentService.update(id, student);

        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable int id) {
        studentService.delete(id);
        return Result.success("删除成功");
    }
    // Controller
    @GetMapping("/search")
    public Result<List<Student>> search(
            @RequestParam String name) {

        return Result.success(studentService.findByName(name));
    }
}
