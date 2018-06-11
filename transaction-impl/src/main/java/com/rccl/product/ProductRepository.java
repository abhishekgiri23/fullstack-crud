package com.rccl.product;


import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.Product;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletionStage;

public class ProductRepository {

public CompletionStage<Product> getProduct(int productId, JdbcSession session) {
    
    return session.withConnection( connection ->
            {
                try (CallableStatement callableStatement = connection.prepareCall("select * from txns.Products where product_id = ?")) {
                    callableStatement.setInt(1, productId);
                    callableStatement.execute();
                    
                    Product.ProductBuilder product = Product.builder();
                    ResultSet resultSet = callableStatement.getResultSet();
                    resultSet.next();
                    product.productId(resultSet.getLong("product_id"));
                    product.productName(resultSet.getString("product_name"));
                    product.price(resultSet.getBigDecimal("price"));
                    return product.build();
                }
                
            }
    ).exceptionally(throwable -> {
        throw new RuntimeException("something went wrong", throwable);
    });
    
}

}
