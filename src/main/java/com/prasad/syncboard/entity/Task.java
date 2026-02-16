package com.prasad.syncboard.entity; // Ensure this matches your folder!

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // This automatically creates getTitle() and setDescription()
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}