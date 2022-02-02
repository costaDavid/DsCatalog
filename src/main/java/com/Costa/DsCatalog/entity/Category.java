package com.Costa.DsCatalog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_category")
@Entity
public class Category implements Serializable{
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
}
