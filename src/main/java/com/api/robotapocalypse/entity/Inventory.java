package com.api.robotapocalypse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "INVENTORY")
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_sequence")
    @SequenceGenerator(name = "inventory_sequence", sequenceName = "inventory_sequence", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name="RESOURCE")
    private String resource;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "survival_id", nullable = false)
    private Survival survival;

    public Inventory(String resource, Survival survival) {
        this.resource = resource;
        this.survival = survival;
    }
}
