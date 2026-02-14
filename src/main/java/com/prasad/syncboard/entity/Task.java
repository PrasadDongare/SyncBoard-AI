package com.prasad.syncboard.entity;

@jakarta.persistence.Entity
@lombok.Data
public class Task {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status; // e.g.,TODO, IN_PROGRESS, DONE
}
