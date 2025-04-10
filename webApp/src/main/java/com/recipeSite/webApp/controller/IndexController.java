package com.recipeSite.webApp.controller;

import com.recipeSite.webApp.model.Category;
import com.recipeSite.webApp.model.UnitOfMeasurement;
import com.recipeSite.webApp.repository.CategoryRepository;
import com.recipeSite.webApp.repository.UnitOfMeasurementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasurementRepository unitOfMeasurementRepository;

    public IndexController (CategoryRepository categoryRepository,
                            UnitOfMeasurementRepository unitOfMeasurementRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasurementRepository = unitOfMeasurementRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("Japanese");
        Optional<UnitOfMeasurement> unitOfMeasurementOptional = unitOfMeasurementRepository.findByDescription("Cup");

        System.out.println("Category ID: " + categoryOptional.get().getId());
        System.out.println("Unit of Measurement ID: " + unitOfMeasurementOptional.get().getId());

        return "index";
    }

}
