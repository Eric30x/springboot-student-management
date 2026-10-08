package com.eric.student_system.dao;

import com.eric.student_system.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {

    private final JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 查询所有学生
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                )
        );
    }

    // 根据ID查询
    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";

        List<Student> list = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ),
                id
        );

        return list.isEmpty() ? null : list.get(0);
    }

    // 新增学生
    public int insert(Student student) {
        String sql = "INSERT INTO students(name, age) VALUES (?, ?)";

        return jdbcTemplate.update(
                sql,
                student.getName(),
                student.getAge()
        );
    }

    // 修改学生
    public int update(int id, Student student) {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                student.getName(),
                student.getAge(),
                id
        );
    }

    // 删除学生
    public int delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
    // DAO
    public List<Student> findByName(String name) {

        String sql = "SELECT * FROM students WHERE name = ?";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ),
                name
        );
    }
}
