package com.ethara.annotation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "text_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private String label;  // e.g., Positive, Negative, Neutral, or custom

    @Column(name = "category")
    private String category;  // e.g., Sentiment, Spam, Intent, etc.

    @Column(name = "annotated_by")
    private String annotatedBy;  // name of the person who labeled

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
