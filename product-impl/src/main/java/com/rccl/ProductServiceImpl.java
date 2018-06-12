package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.product.ProductRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ProductServiceImpl implements ProductService {
    
    private final JdbcSession session;
    private final ProductRepository productRepository;
    
    @Inject
    public ProductServiceImpl(JdbcSession session,
                              ProductRepository productRepository) {
        this.session = session;
        this.productRepository = productRepository;
    }
    
    /**
     *
     * @param productId
     * @return
     */
    @Override
    public ServiceCall<NotUsed, Optional<Product>> getProductByProductId(int productId) {
        
        return request -> productRepository.getProduct(productId, session).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
        
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<NotUsed, String> getHealth() {
        return req -> CompletableFuture.completedFuture("Service is up");
    }
    
    
    @Override
    public ServiceCall<NotUsed, String> deleteProduct(int productId) {
        return req -> productRepository.deleteProduct(productId, session);
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<Product, String> addProduct() {
        return prod -> productRepository.addProduct(prod, session);
    }
    
    @Override
    public ServiceCall<Product, String> updateProduct(int productId) {
        return prod -> productRepository.updateProduct(productId, prod, session);
    }
    
    
}
    

