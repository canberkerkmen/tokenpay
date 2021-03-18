package com.erkmen.service;


import com.erkmen.domain.Product;
import com.erkmen.domain.ProductObjectMother;
import com.erkmen.domain.enums.QuantityType;
import com.erkmen.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@DataJpaTest
public class ProductServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void shouldFindAllReturnsEmpty() {
        List<Product> productList = productRepository.findAll();

        assertTrue(productList.isEmpty());
    }

    @Test
    public void shouldSaveSuccessfullyWithAllParameters() {
        Product product = productRepository.save(new Product("00011", "productName", "productDescription", QuantityType.PACKET, 15d, true));

        assertThat(product).hasFieldOrPropertyWithValue("code", "00011");
        assertThat(product).hasFieldOrPropertyWithValue("name", "productName");
        assertThat(product).hasFieldOrPropertyWithValue("description", "productDescription");
        assertThat(product).hasFieldOrPropertyWithValue("quantityType", QuantityType.PACKET);
        assertThat(product).hasFieldOrPropertyWithValue("price", 15d);
        assertThat(product).hasFieldOrPropertyWithValue("isActive", Boolean.TRUE);

    }

    @Test
    public void shouldGetAllProductsWhenThreeProductsPersist() {
        Product prod1 = new Product("00011", "productName1", "productDescription1", QuantityType.LITRE, 15d, true);
        entityManager.persist(prod1);

        Product prod2 = new Product("00012", "productName2", "productDescription2", QuantityType.PACKET, 20d, true);
        entityManager.persist(prod2);

        Product prod3 = new Product("00013", "productName3", "productDescription3", QuantityType.PIECE, 25d, true);
        entityManager.persist(prod3);

        Iterable<Product> tutorials = productRepository.findAll();

        assertThat(tutorials).hasSize(3).containsAll(ProductObjectMother.getProductList());
    }

    @Test
    public void shouldGetByIdReturnsProductWhenProductPersist() {
        Product prod1 = new Product("00011", "productName1", "productDescription1", QuantityType.LITRE, 15d, true);
        entityManager.persist(prod1);

        Product prod2 = new Product("00012", "productName2", "productDescription2", QuantityType.PACKET, 20d, true);
        entityManager.persist(prod2);

        Product prod3 = new Product("00013", "productName3", "productDescription3", QuantityType.PIECE, 25d, true);
        entityManager.persist(prod3);

        Product foundTutorial = productRepository.findById(prod2.getId()).get();

        assertThat(foundTutorial).isEqualTo(prod2);
    }

    @Test
    public void shouldUpdateProductsWithAllFieldsWhenSetNewParametersAfterPersist() {
        Product prod1 = new Product("00011", "productName1", "productDescription1", QuantityType.LITRE, 15d, true);
        entityManager.persist(prod1);

        Product prod2 = new Product("00012", "productName2", "productDescription2", QuantityType.PACKET, 20d, true);
        entityManager.persist(prod2);

        Product updatedProd = new Product("00022", "productName22", "productDescription22", QuantityType.PIECE, 25d, true);

        Product product = productRepository.findById(prod2.getId()).get();
        product.setCode(updatedProd.getCode());
        product.setDescription(updatedProd.getDescription());
        product.setName(updatedProd.getName());
        product.setQuantityType(updatedProd.getQuantityType());
        product.setPrice(updatedProd.getPrice());
        productRepository.save(product);

        Product checkTut = productRepository.findById(prod2.getId()).get();

        assertThat(checkTut.getId()).isEqualTo(prod2.getId());
        assertThat(checkTut.getCode()).isEqualTo(updatedProd.getCode());
        assertThat(checkTut.getDescription()).isEqualTo(updatedProd.getDescription());
        assertThat(checkTut.getName()).isEqualTo(updatedProd.getName());
        assertThat(checkTut.getQuantityType()).isEqualTo(updatedProd.getQuantityType());
        assertThat(checkTut.getPrice()).isEqualTo(updatedProd.getPrice());
    }

    @Test
    public void shouldDeleteByIdReturnSuccessfull() {
        Product prod1 = new Product("00011", "productName1", "productDescription1", QuantityType.LITRE, 15d, true);
        entityManager.persist(prod1);

        Product prod2 = new Product("00012", "productName2", "productDescription2", QuantityType.PACKET, 20d, true);
        entityManager.persist(prod2);

        Product prod3 = new Product("00013", "productName3", "productDescription3", QuantityType.PIECE, 25d, true);
        entityManager.persist(prod3);

        productRepository.deleteById(prod2.getId());

        Iterable<Product> tutorials = productRepository.findAll();

        assertThat(tutorials).hasSize(2).contains(prod1, prod3);
    }

    @Test
    public void shouldDeleteAllReturnSuccessfull() {
        entityManager.persist(new Product("00011", "productName1", "productDescription1", QuantityType.LITRE, 15d, true));
        entityManager.persist(new Product("00012", "productName2", "productDescription2", QuantityType.PACKET, 20d, true));

        productRepository.deleteAll();

        assertThat(productRepository.findAll()).isEmpty();
    }
}
