package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.Optional;

import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.transport.Method.DELETE;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;
import static com.lightbend.lagom.javadsl.api.transport.Method.POST;
import static com.lightbend.lagom.javadsl.api.transport.Method.PUT;

public interface ProductService extends Service {
    
    ServiceCall<NotUsed, Optional<Product>> getProductByProductId(int productId);
    
    ServiceCall<Product, String> addProduct();
    
    ServiceCall<Product, String> updateProduct(int productId);
    
    ServiceCall<NotUsed, String> deleteProduct(int productId);
    
    ServiceCall<NotUsed, String> getHealth();
    
    @Override
    default Descriptor descriptor() {
        return Service.named("products")
                .withCalls(
                        restCall(GET, "/api/product/:productId", this::getProductByProductId),
                        restCall(POST, "/api/add/product", this::addProduct),
                        restCall(PUT, "/api/update/product/:productId", this::updateProduct),
                        restCall(DELETE, "/api/delete/product/:productId", this::deleteProduct),
                        restCall(GET, "/health", this::getHealth))
                .withAutoAcl(true);
    }
}
