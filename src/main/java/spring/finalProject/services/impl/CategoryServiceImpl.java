package spring.finalProject.services.impl;

import spring.finalProject.entities.models.CategoryEntity;
import spring.finalProject.repositories.CategoryRepository;
import spring.finalProject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addFromController(String name) {
        CategoryEntity category = new CategoryEntity();
        category.setCategory(name);
        this.add(category);
    }

    @Override
    public void updateFromController(Long id, String name) {
        CategoryEntity category = this.getCategoryById(id);
        if (category != null) {
            category.setCategory(name);
            category.setUpdatedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            this.save(category);
        }
    }

    @Override
    public void deleteFromController(Long id) {
        CategoryEntity category = this.getCategoryById(id);
        if (category != null) {
            this.delete(category);
        }
    }

    @Override
    public CategoryEntity add(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(CategoryEntity category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.getOne(id);
    }
}
