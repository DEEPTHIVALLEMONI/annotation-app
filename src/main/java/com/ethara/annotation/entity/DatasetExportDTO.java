package com.ethara.annotation.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatasetExportDTO {
    private Long id;
    private String text;
    private String label;
    private String category;
    private String annotatedBy;
    private String createdAt;
}
