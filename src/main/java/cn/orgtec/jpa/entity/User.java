package cn.orgtec.jpa.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private int age;
    @Convert(
            converter = GenderEnumConverter.class
    )
    private GenderEnum gender;
    @Version
    private int version;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public GenderEnum getGender() {
        return gender;
    }
 
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
 
    public int getVersion() {
        return version;
    }
 
    public void setVersion(int version) {
        this.version = version;
    }
 
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", version=" + version +
                '}';
    }
}