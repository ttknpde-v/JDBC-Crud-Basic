package com.java.api.modules.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student {

    /* These are Attribute For Json to Sql */
    private Long order;
    private String fullname;
    private String email;
    private Integer age;
    private String city;
    private Double salary;
    private String datetime;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");

    public Long getOrder() {

        return order;
    }

    public void setOrder(Long order) {

        this.order = order;
    }

    public String getFullname() {

        return fullname;
    }

    public void setFullname(String fullname) {

        this.fullname = fullname;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public Double getSalary() {

        return salary;
    }

    public void setSalary(Double salary) {

        this.salary = salary;
    }

    public void setDatetime(String datetime) {
        /* this method for retrieve to show datetime */
        this.datetime = datetime;
    }

    public String getDatetime() {
        /* this method for retrieve to show datetime */
         return datetime;
    }

    public String forInsertDatetime() {
        this.datetime = this.localDateTime.format(this.formatter);
        return this.datetime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "order=" + order +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                ", datetime=" + getDatetime() +
                '}';
        /* remember The attribute name we use to into database Not name field database */
    }
}
