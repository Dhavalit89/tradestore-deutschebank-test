package com.deutschebank.repository;

import com.deutschebank.model.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeStoreRepository extends CrudRepository<Trade, Integer> {
    List<Trade> findByTradeId(String tradeId);

    void deleteByTradeId(String id);
}
