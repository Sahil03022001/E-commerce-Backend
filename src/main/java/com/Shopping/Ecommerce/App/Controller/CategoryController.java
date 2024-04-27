package com.Shopping.Ecommerce.App.Controller;

import com.Shopping.Ecommerce.App.Model.Code;
import com.Shopping.Ecommerce.App.RequestDTO.CreateCategoryRequestDTO;
import com.Shopping.Ecommerce.App.ResponseDTO.Response;
import com.Shopping.Ecommerce.App.Service.CategoryService;
import com.Shopping.Ecommerce.App.Validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public Response createCategory(@RequestBody CreateCategoryRequestDTO request) {

        Response response = new Response();
        if (!Validation.validateRequest(request).isBlank()) {
            response = new Response();
            response.setMessage("Request is not valid");
            response.setCode(Code.FAILED);
            return response;
        }

        try {
            response = categoryService.createCategory(request);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error encountered while creating category with name : " + request.getName());
            response.setCode(Code.FAILED);
        }
        return response;
    }
}
