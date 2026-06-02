package com.veteranresources.veteranresourcelocator.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResourceResponse {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String address;
    private String phone;
    private String website;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
