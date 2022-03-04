package com.api.robotapocalypse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

//    location (latitude, longitude)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_sequence")
    @SequenceGenerator(name = "location_sequence", sequenceName = "location_sequence", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name="LATITUDE")
    private String latitude;

    @Column(name="LONGITUDE")
    private String longitude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SURVIVAL_ID")
    private Survival survival;

    public Location(String latitude, String longitude, Survival survival) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.survival = survival;
    }

    public Location(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}