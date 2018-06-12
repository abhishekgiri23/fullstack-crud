package com.rccl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.rccl.transaction.TransactionRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class TransactionServiceImpl implements TransactionService {
    
    private final JdbcSession session;
    private final TransactionRepository transactionRepository;
    
    @Inject
    public TransactionServiceImpl(JdbcSession session,
                                  TransactionRepository transactionRepository) {
        this.session = session;
        this.transactionRepository = transactionRepository;
    }
    
    /**
     *
     * @param productId
     * @param userId
     * @return
     */
    @Override
    public ServiceCall<NotUsed, Optional<Transaction>> getTxnByProdIdAndUserId(long userId, int productId) {
        return request -> transactionRepository.getTransaction(userId, productId, session);
    }
    
    @Override
    public ServiceCall<NotUsed, String> deleteTxn(long userId, int productId) {
        return request -> transactionRepository.deleteTxn(userId, productId, session);
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<NotUsed, String> getHealth() {
        return req -> CompletableFuture.completedFuture("Service is up");
    }
}
    

