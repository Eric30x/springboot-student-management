package com.eric.student_system.service;

import com.eric.student_system.dao.StudentDao;
import com.eric.student_system.entity.Student;
import com.eric.student_system.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public Student findById(int id) {
        Student student = studentDao.findById(id);

        if (student == null) {
            throw new StudentNotFoundException("学生不存在");
        }

        return student;
    }

    public void add(Student student) {
        studentDao.insert(student);
    }

    public void update(int id, Student student) {
        int rows = studentDao.update(id, student);

        if (rows == 0) {
            throw new StudentNotFoundException("学生不存在");
        }
    }

    public void delete(int id) {
        int rows = studentDao.delete(id);

        if (rows == 0) {
            throw new StudentNotFoundException("学生不存在");
        }
    }
    // Service
    public List<Student> findByName(String name) {

        List<Student> students = studentDao.findByName(name);

        if (students.isEmpty()) {
            throw new StudentNotFoundException("学生不存在");
        }

        return students;
    }
}