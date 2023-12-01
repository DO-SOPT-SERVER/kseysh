package com.server.dosopt.seminar.domain.repository;


import com.server.dosopt.seminar.domain.Category;
import com.server.dosopt.seminar.domain.CategoryId;
import com.server.dosopt.seminar.domain.Post;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

}
