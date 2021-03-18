package com.erkmen.service;

import com.erkmen.domain.factory.ApplicationFactory;
import com.erkmen.domain.Product;
import com.erkmen.dto.ProductInfoDTO;
import com.erkmen.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, ProductRepository> {

    private final ApplicationFactory applicationFactory;

    @Autowired
    public ProductService(ProductRepository repository, ApplicationFactory applicationFactory) {
        super(repository);
        this.applicationFactory = applicationFactory;
    }

    public Product getByProductCode(String code) {
        return getRepository().findByCode(code);
    }

    public Integer deleteByCode(String code) {
        return getRepository().deleteByCode(code);
    }

    public void add(ProductInfoDTO productInfoDTO) {
        Product product = applicationFactory.createProduct(productInfoDTO);
        save(product);
    }
}
