package com.Shopping.Ecommerce.App.Service;

import com.Shopping.Ecommerce.App.RequestDTO.CreateCategoryRequestDTO;
import com.Shopping.Ecommerce.App.ResponseDTO.Response;
import org.springframework.stereotype.Service;

public interface CategoryService {
    Response createCategory(CreateCategoryRequestDTO request);
}
