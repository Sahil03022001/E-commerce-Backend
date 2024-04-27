package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Model.Category;
import com.Shopping.Ecommerce.App.Model.Code;
import com.Shopping.Ecommerce.App.Repository.CategoryRepository;
import com.Shopping.Ecommerce.App.RequestDTO.CreateCategoryRequestDTO;
import com.Shopping.Ecommerce.App.ResponseDTO.Response;
import com.Shopping.Ecommerce.App.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @SuppressWarnings("uncheck")
    public Response createCategory(CreateCategoryRequestDTO request) {
        Response response = new Response();

        if (StringUtils.isEmpty(request.getName())) {
            response.setMessage("Given category name is Empty");
            response.setCode(Code.FAILED);
            return response;
        }

        Category category = categoryRepository.getCategoryByName(request.getName());
        if (category != null) {
            response.setMessage("Category Already exists with name" + request.getName());
            response.setCode(Code.FAILED);
            return response;
        }

        Category newCategory = new Category();
        newCategory.setName(request.getName());
        newCategory.setCreated(new Date());
        newCategory.setUpdated(new Date());

        categoryRepository.save(newCategory);

        response.setMessage("Category created");
        response.setCode(Code.SUCCESSFULL);
        return response;
    }
}
