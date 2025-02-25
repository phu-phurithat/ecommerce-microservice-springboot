package com.phu.product_service;

import com.phu.product_service.exception.ProductPurchaseException;
import com.phu.product_service.model.ProductPurchaseRequest;
import com.phu.product_service.model.ProductPurchaseResponse;
import com.phu.product_service.model.ProductRequest;
import com.phu.product_service.model.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public String createProduct(@Valid ProductRequest request) {
        Product product = productMapper.toProduct(request);
        return productRepository.save(product).getId().toString();

    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        List<Long> productIds = requests.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        List<Product> products = productRepository.findAllByIdInOrderById(productIds);
        if (products.size() != productIds.size()) {
            throw new ProductPurchaseException("One or more products not found");
        }

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < requests.size(); i++) {
            ProductPurchaseRequest request = requests.get(i);
            Product product = products.get(i);
            if (product.getAvailableQuantity() < request.getQuantity()) {
                throw new ProductPurchaseException(format("Not enough quantity of product %s", product.getName()));
            }
            product.setAvailableQuantity(product.getAvailableQuantity() - request.getQuantity());
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product));
        }

        return purchasedProducts;
    }

    public ProductResponse findById(String productId) {
        Long id = Long.parseLong(productId);
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("No product found with provided ID :: %s" + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
