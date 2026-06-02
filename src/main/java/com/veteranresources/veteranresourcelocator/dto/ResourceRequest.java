package com.veteranresources.veteranresourcelocator.dto;

import com.veteranresources.veteranresourcelocator.model.ResourceCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResourceRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Category is required")
    private ResourceCategory category;

    @NotBlank(message = "Address is required")
    private String address;

    // @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must be in format XXX-XXX-XXXX")
    private String phone;

    private String website;
}
