package com.rccl.product;


import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.Product;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class ProductRepository {

public CompletionStage<Optional<Product>> getProduct(int productId, JdbcSession session) {
    
    return session.withConnection( connection ->
            {
                try (CallableStatement callableStatement = connection.prepareCall("select * from txns.Products where product_id = ?")) {
                    callableStatement.setInt(1, productId);
                    callableStatement.execute();
    
                    Product.ProductBuilder product = Product.builder();
                    ResultSet resultSet = callableStatement.getResultSet();
                    if (!resultSet.next()) {
                        return Optional.<Product>empty();
                    } else {
                        product.productId(resultSet.getLong("product_id"));
                        product.productName(resultSet.getString("product_name"));
                        product.price(resultSet.getBigDecimal("price"));
                        return Optional.of(product.build());
                    }
                }
            }
    );
}
    
    public CompletionStage<String> deleteProduct(int productId, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("delete from txns.Products where product_id = ?")) {
                        callableStatement.setInt(1, productId);
                        callableStatement.execute();
                        return "product deleted successfully!!";
                    }
                    
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
    public CompletionStage<String> addProduct(Product product, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("insert into txns.Products " +
                            "(product_id, product_name, price) values (?, ?, ?)")) {
                        callableStatement.setLong(1, product.getProductId());
                        callableStatement.setString(2, product.getProductName());
                        callableStatement.setBigDecimal(3, product.getPrice());
                        callableStatement.execute();
                        return "product added successfully!!";
                    }
                    
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
    public CompletionStage<String> updateProduct(int productId, Product product, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("UPDATE txns.Products  SET price = ? where product_id = ?")) {
                        callableStatement.setBigDecimal(1, product.getPrice());
                        callableStatement.setLong(2, productId);
                        callableStatement.execute();
                        return "product updated successfully!!";
                    }
                    
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }

}
