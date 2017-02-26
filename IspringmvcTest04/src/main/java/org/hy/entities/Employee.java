package org.hy.entities;

/**
 * Created by andy on 2017/2/3.
 */
public class Employee {
    private Integer id;
    private String lastname;
    private String email;
    private Integer gender;
    private Department department;

    public Employee() {
    }

    public Employee(Integer id, String lastname, String email, Integer gender, Department department) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGender() {
        return gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", department=" + department +
                '}';
    }
}
