package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Category;

import com.example.demo.enums.CategoryStatus;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByStatus(CategoryStatus status);

	boolean existsByCategory_Id(Integer parentId);
}
