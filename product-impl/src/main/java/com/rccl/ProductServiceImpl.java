package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.product.ProductRepository;

import javax.inject.Inject;
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
    public ServiceCall<NotUsed, Product> getProductByProductId(int productId) {
        
        return request -> productRepository.getProduct(productId, session);
        
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<NotUsed, String> getHealth() {
        return req -> CompletableFuture.completedFuture("Service is up");
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<Product, String> addProduct() {
        return req -> CompletableFuture.completedFuture("Product Added");
    }
    
    
}
    

