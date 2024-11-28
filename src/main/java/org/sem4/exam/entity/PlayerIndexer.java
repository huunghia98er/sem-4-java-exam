package org.sem4.exam.entity;

import jakarta.persistence.*;
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
@Table(name = "player_index")
public class PlayerIndexer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "index_id")
    private Indexer indexer;
    @Column(name = "value")
    private Float value;
}
