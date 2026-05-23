package com.veteranresources.veteranresourcelocator.repository;

import com.veteranresources.veteranresourcelocator.model.Resource;
import com.veteranresources.veteranresourcelocator.model.ResourceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Using JpaRepository, you get these methods for free without writing a single line of SQL:
 *
 * findAll() — get every resource
 * findById(Long id) — get one by ID
 * save(resource) — insert or update
 * deleteById(Long id) — delete one
 * count() — how many rows exist
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {


    List<Resource> findByCategory(ResourceCategory category);
    // SELECT * FROM resources WHERE category = ?

    List<Resource> findByNameContainingIgnoreCase(String name);
    // SELECT * FROM resources WHERE LOWER(name) LIKE LOWER('%?%')

    List<Resource> findByCategoryAndNameContainingIgnoreCase(ResourceCategory category, String name);
    // SELECT * FROM resources WHERE category = ? AND LOWER(name) LIKE LOWER('%?%')
}