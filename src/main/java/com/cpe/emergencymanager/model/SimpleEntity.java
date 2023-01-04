package com.cpe.emergencymanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_name")
public class SimpleEntity {
    @Id
    @Column(name = "example_id")
    private long id;

    @Column(name = "example_name")
    private String name;
}
