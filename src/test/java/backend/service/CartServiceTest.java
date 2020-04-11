package backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import backend.model.Cart;
import backend.model.Product;
import backend.repository.CartRepository;
import backend.repository.ProductRepository;
import io.swagger.annotations.Example;

@SpringBootTest(classes = CartServiceImpl.class)
class CartServiceTest {

	@Autowired
	private CartService cartService;
	
	@MockBean
	private ProductRepository productRepository;


	@MockBean
	private CartRepository cartRepository;
	
	
	
	@Test
	void addProductTest() {
		Product product = new Product();
		product.setId(1l);
		product.setUserId(1l);
		product.setCarts(new HashSet<>());
		
		Cart cart = new Cart();
		cart.setId(1l);
		cart.setProducts(new HashSet<>());
		
		when(cartRepository.findById(1l)).thenReturn(Optional.of(cart));
		when(productRepository.findById(1l)).thenReturn(Optional.of(product));
		
		cartService.addProduct(1, 1);
		
		verify(productRepository, times(1)).findById(1l);
		verify(cartRepository, times(1)).findOne(any());
		verify(cartRepository, times(1)).save(any(Cart.class));
	}

}
