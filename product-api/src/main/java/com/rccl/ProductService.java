package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.transport.Method.DELETE;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;
import static com.lightbend.lagom.javadsl.api.transport.Method.POST;
import static com.lightbend.lagom.javadsl.api.transport.Method.PUT;

public interface ProductService extends Service {
    
    ServiceCall<NotUsed, Product> getProductByProductId(int productId);
    
    ServiceCall<Product, String> addProduct();
    
    ServiceCall<NotUsed, String> getHealth();
    
    @Override
    default Descriptor descriptor() {
        return Service.named("transactions")
                .withCalls(
                        restCall(GET, "/api/product/:productId", this::getProductByProductId),
                        restCall(GET, "/api/add/product", this::addProduct),
                        restCall(GET, "/health", this::getHealth))
                .withAutoAcl(true);
    }
}
