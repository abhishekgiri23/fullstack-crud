package com.rccl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.rccl.transaction.TransactionRepository;

import javax.inject.Inject;
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
    public ServiceCall<NotUsed, Transaction> getTxnByProdIdAndUserId(int productId, int userId) {
        return request -> transactionRepository.getTransaction(userId, productId, session);
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
    

