package com.java.api.modules.service.servicemapper;

import com.java.api.modules.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet , int rowNum) throws SQLException {
        /* method for retrieve Object students Table than store to Student Class */
        Student studentObj = new Student();
        studentObj.setOrder(resultSet.getLong("orderstudent"));
        studentObj.setFullname(resultSet.getString("fullnamestudent"));
        studentObj.setEmail(resultSet.getString("emailstudent"));
        studentObj.setAge(resultSet.getInt("agestudent"));
        studentObj.setCity(resultSet.getString("city"));
        studentObj.setSalary(resultSet.getDouble("salary"));
        studentObj.setDatetime(resultSet.getString("datetime"));
        return studentObj;
    }


}
