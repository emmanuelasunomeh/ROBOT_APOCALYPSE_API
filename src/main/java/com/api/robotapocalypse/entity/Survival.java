package com.api.robotapocalypse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "SURVIVAL")
@AllArgsConstructor
@NoArgsConstructor
public class Survival {

//    name, age, gender, ID and last location (latitude, longitude), is_infected

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survival_sequence")
    @SequenceGenerator(name = "survival_sequence", sequenceName = "survival_sequence", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="AGE")
    private Long age;

    @Column(name="GENDER")
    private String gender;

    @JsonIgnore
    @OneToMany(mappedBy = "survival", orphanRemoval = true)
    private List<Inventory> inventories = new ArrayList<>();

    @Column(name = "IS_INFECTED", nullable = false)
    private Boolean isInfected = false;

    @Column(name = "NO_OF_FLAGS")
    private Long noOfFlags = 0L;

    @JsonIgnore
    @OneToOne(mappedBy = "survival", cascade = CascadeType.ALL, orphanRemoval = true)
    private Location location;



    public Survival(Long id, String name, Long age, String gender, List<Inventory> inventories, Boolean isInfected, Location location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.inventories = inventories;
        this.isInfected = isInfected;
        this.location = location;
    }

    public Survival( String name, Long age, String gender, List<Inventory> inventories, Boolean isInfected, Location location) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.inventories = inventories;
        this.isInfected = isInfected;
        this.location = location;
    }
    public Survival(Long id, String name, Long age, String gender, List<Inventory> inventories, Location location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.inventories = inventories;
        this.location = location;
    }

    public Survival(Long id, String name, Long age, String gender, Location location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Survival{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", isInfected=" + isInfected +
                ", noOfFlags=" + noOfFlags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Survival)) return false;
        Survival survival = (Survival) o;
        return Objects.equals(getId(), survival.getId()) && Objects.equals(getName(), survival.getName()) && Objects.equals(getAge(), survival.getAge()) && Objects.equals(getGender(), survival.getGender()) && Objects.equals(getIsInfected(), survival.getIsInfected()) && Objects.equals(getNoOfFlags(), survival.getNoOfFlags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getGender(), getIsInfected(), getNoOfFlags());
    }
}
