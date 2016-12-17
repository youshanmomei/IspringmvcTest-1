package org.hy.entities;

import java.util.Date;

public class Employee {
    private Integer id;
    private String lastname;
    private String email;
    private Integer gender;
    private Department department;


    private Date birth;
    private Float salary;

    public Employee() {
        super();
    }

    public Employee(Integer id, String lastname, String email, Integer gender,
                    Department department) {
        super();
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }


    public Employee(Integer id, String lastname, String email, Integer gender,
                    Department department, Date bitrh, Float salary) {
        super();
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = bitrh;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", lastname=" + lastname + ", email="
                + email + ", gender=" + gender + ", department=" + department
                + ", bitrh=" + birth + ", salary=" + salary + "]";
    }


}
