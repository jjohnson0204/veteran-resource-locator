package com.veteranresources.veteranresourcelocator.service;

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

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Resource> searchResources(ResourceCategory category, String name) {
        if (category != null && name != null) {
            return resourceRepository.findByCategoryAndNameContainingIgnoreCase(category, name);
        } else if (category != null) {
            return resourceRepository.findByCategory(category);
        } else if (name != null) {
            return resourceRepository.findByNameContainingIgnoreCase(name);
        }
        return resourceRepository.findAll();
    }

    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long id, Resource updatedResource) {
        Resource existing = getResourceById(id);
        existing.setName(updatedResource.getName());
        existing.setDescription(updatedResource.getDescription());
        existing.setCategory(updatedResource.getCategory());
        existing.setAddress(updatedResource.getAddress());
        existing.setPhone(updatedResource.getPhone());
        existing.setWebsite(updatedResource.getWebsite());
        return resourceRepository.save(existing);
    }

    public void deleteResource(Long id) {
        getResourceById(id);
        resourceRepository.deleteById(id);
    }
}