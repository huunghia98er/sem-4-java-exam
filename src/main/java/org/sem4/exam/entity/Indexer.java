package org.sem4.exam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "indexer")
public class Indexer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id")
    private Integer indexerId;
    @Column(name = "name")
    @NotNull(message = "Name is required")
    @Size(min = 1, max = 64, message = "Name must be between 1 and 64 characters")
    private String name;
    @NotNull(message = "Min value is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Min value must be at least 0")
    @Column(name = "valueMin")
    private Float valueMin;
    @NotNull(message = "Max value is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Max value must be at least 0")
    @Column(name = "valueMax")
    private Float valueMax;
}
