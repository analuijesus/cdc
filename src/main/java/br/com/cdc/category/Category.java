package br.com.cdc.category;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "category")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Category(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    @Deprecated
    public Category() {
    }
}
