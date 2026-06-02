package com.veteranresources.veteranresourcelocator.service;

import com.veteranresources.veteranresourcelocator.dto.ResourceRequest;
import com.veteranresources.veteranresourcelocator.dto.ResourceResponse;
import com.veteranresources.veteranresourcelocator.exception.ResourceNotFoundException;
import com.veteranresources.veteranresourcelocator.model.Resource;
import com.veteranresources.veteranresourcelocator.model.ResourceCategory;
import com.veteranresources.veteranresourcelocator.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RequiredArgsConstructor combined with private final ResourceRepository
 * is Lombok generating a constructor that Spring uses to inject the repository
 * — this is called constructor injection
 */
@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public List<ResourceResponse> getAllResources() {
        return resourceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ResourceResponse getResourceById(Long id) {
        return toResponse(findById(id));
    }

    public List<ResourceResponse> searchResources(ResourceCategory category, String name) {
        if (category != null && name != null) {
            return resourceRepository.findByCategoryAndNameContainingIgnoreCase(category, name)
                    .stream().map(this::toResponse).toList();
        } else if (category != null) {
            return resourceRepository.findByCategory(category)
                    .stream().map(this::toResponse).toList();
        } else if (name != null) {
            return resourceRepository.findByNameContainingIgnoreCase(name)
                    .stream().map(this::toResponse).toList();
        }
        return resourceRepository.findAll()
                .stream().map(this::toResponse).toList();
    }

    public ResourceResponse createResource(ResourceRequest request) {
        Resource resource = toEntity(request);
        return toResponse(resourceRepository.save(resource));
    }

    public ResourceResponse updateResource(Long id, ResourceRequest request) {
        Resource existing = findById(id);
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setCategory(request.getCategory());
        existing.setAddress(request.getAddress());
        existing.setPhone(request.getPhone());
        existing.setWebsite(request.getWebsite());
        return toResponse(resourceRepository.save(existing));
    }

    public void deleteResource(Long id) {
       findById(id);
       resourceRepository.deleteById(id);
    }

    private Resource findById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private Resource toEntity(ResourceRequest request) {
        return Resource.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .address(request.getAddress())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .build();
    }

    private ResourceResponse toResponse(Resource resource) {
        return ResourceResponse.builder()
                .id(resource.getId())
                .name(resource.getName())
                .description(resource.getDescription())
                .category(resource.getCategory().name())
                .address(resource.getAddress())
                .phone(resource.getPhone())
                .website(resource.getWebsite())
                .createdAt(resource.getCreatedAt())
                .updatedAt(resource.getUpdatedAt())
                .build();
    }
}