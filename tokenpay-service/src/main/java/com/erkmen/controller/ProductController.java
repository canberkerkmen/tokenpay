package com.erkmen.controller;

import com.erkmen.domain.Product;
import com.erkmen.dto.ProductInfoDTO;
import com.erkmen.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/products")
@Api(tags = "products")
public class ProductController {

    private ProductService productService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "${ProductController.add}")
    public void add(@RequestBody @Valid ProductInfoDTO productInfoDTO) {
        productService.add(productInfoDTO);

    }

    @GetMapping("/getAll")
    @ApiOperation(value = "${ProductController.getall}", response = Product.class, responseContainer = "List")
    public List<Product> getAll() {
        return productService.getAll();
    }

}
