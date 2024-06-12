package com.example.zettaonline.restapi.model;
import jakarta.persistence.*;
@Entity
@Table(name = "users", schema = "public")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "age", nullable = false)
    private Integer age = 0;
    @Column(name = "salary", nullable = false)
    private Long salary = 1000L;
    @Column(name = "nationality", length = 2)
    private String nationality;
    public UserEntity() {
    }
    public UserEntity(Integer age, String nationality, Long salary) {
        this.age = age;
        this.nationality = nationality;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", age=" + age +
                ", salary=" + salary +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}

