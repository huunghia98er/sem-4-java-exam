package org.sem4.exam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;
    @Column(name = "name")
    @NotNull(message = "Name is required")
    @Size(min = 1, max = 64, message = "Name must be between 1 and 64 characters")
    private String name;
    @Column(name = "full_name")
    @NotNull(message = "Full name is required")
    @Size(min = 1, max = 128, message = "Full name must be between 1 and 128 characters")
    private String fullName;
    @Column(name = "age")
    @NotNull(message = "Age is required")
    @Pattern(regexp = "^[0-9]+$", message = "Age must be a number")
    private String age;
    @Column(name = "indexerId")
    @NotNull(message = "Indexer ID is required")
    private Integer indexerId;
}
