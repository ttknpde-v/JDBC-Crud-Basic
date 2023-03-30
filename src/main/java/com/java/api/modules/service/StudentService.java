package com.java.api.modules.service;

import com.java.api.modules.model.Student;
import com.java.api.modules.repository.StudentRepository;
import com.java.api.modules.service.servicemapper.StudentMapper;
import com.java.api.modules.service.servicesql.DynamicSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;


@Service
public class StudentService implements StudentRepository<Student> {

    private JdbcTemplate jdbcTemplate; /* inject by CDI */ private DynamicSql dynamicSql;
    @Autowired
    public StudentService(DataSource dataSource) {
        /* Construct Dependency Injection */
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dynamicSql = new DynamicSql();
    }

    @Override
    public ResponseEntity<Student> create(Student request) {
        ResponseEntity response;
        if (request.getFullname().isEmpty() || request.getCity().isEmpty() || request.getAge() < 10 || request.getSalary() < 1 || request.getEmail().isEmpty()) {

            response = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        }
        else {

            this.jdbcTemplate.update(this.dynamicSql.getCREATE()
            , request.getFullname() , request.getEmail() ,request.getAge()
            , request.getCity() , request.getSalary() , request.forInsertDatetime());

            response = new ResponseEntity(request, HttpStatus.CREATED);
        }

        return response;
    }

    @Override
    public HashMap<String, List<Student>> readAtAll() {
        /* must mapper student */
        StudentMapper studentMapper = new StudentMapper();
        List<Student> studentList = this.jdbcTemplate.query(this.dynamicSql.getREAD() , studentMapper);
        HashMap<String , List<Student> > response = new HashMap<>();
        response.put("students",studentList);
        return response;
    }

    @Override
    public HashMap<String, Student> readById(Long request) {
        HashMap<String , Student> response = new HashMap<>();
        StudentMapper studentMapper = new StudentMapper();
        Object []objectsRequest = {request}; /* using Object class because queryForObject method need it */
        Student resultStudent;
        try {

             resultStudent = this.jdbcTemplate.queryForObject(this.dynamicSql.getREADBYID()
                     , objectsRequest , studentMapper);
             /* response.put("student order "+request , resultStudent); */
             response.put("student" , resultStudent);
             return response;

            } catch (EmptyResultDataAccessException error) {

              System.out.println(error);
        }

        response.put("student order "+request , new Student());
        return response;
    }


    @Override
    public ResponseEntity<Student> update(Student request , int id) {
        ResponseEntity response;

        if (request.getFullname().isEmpty() || request.getCity().isEmpty() || request.getAge() < 10 || request.getSalary() < 1 || request.getEmail().isEmpty()) {

            response = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

        }
        else {

            this.jdbcTemplate.update(this.dynamicSql.getUPDATE()
                    , request.getFullname() , request.getEmail() , request.getAge()
                    , request.getCity() , request.getSalary() , request.forInsertDatetime() , id);
            response = new ResponseEntity<>(request , HttpStatus.CREATED);

        }

        return response;
    }

    @Override
    public HashMap<String, Student> deleteById(Long request) {

        HashMap<String,Student> findStudent = this.readById(request);
        try {

            if (findStudent.get("student").getFullname() != null) {

                this.jdbcTemplate.update(this.dynamicSql.getDELETE() , request);
                return findStudent;
            }

        } catch (NullPointerException error) {
            System.out.println(error);
        }

        return findStudent;
    }
}
