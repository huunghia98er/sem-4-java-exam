package org.sem4.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {
    private Integer id;
    private String name;
    private String age;
    private String indexName;
    private Float value;
}
