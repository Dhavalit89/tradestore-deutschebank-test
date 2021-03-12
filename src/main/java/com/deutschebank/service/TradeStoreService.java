package com.deutschebank.service;

import com.deutschebank.exception.TradeException;
import com.deutschebank.model.Trade;
import com.deutschebank.repository.TradeStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TradeStoreService {
    @Autowired
    TradeStoreRepository tradeStoreRepository;

    public List<Trade> getAllTradeStore() {
        List<Trade> trades = new ArrayList<Trade>();
        tradeStoreRepository.findAll().forEach(trade -> trades.add(trade));
        return trades;
    }

    public Trade generateTrade(Trade trade) throws TradeException {
        tradeValidation(trade);
        return tradeStoreRepository.save(trade);
    }

    private void tradeValidation(Trade trade) throws TradeException {
        List<Trade> tradeItemList = tradeStoreRepository.findByTradeId(trade.getTradeId());
        maturityDateValidation(trade);
        versionValidation(trade, tradeItemList);
    }

    private void versionValidation(Trade trade, List<Trade> tradeItemList) throws TradeException {
        for (Trade tradeItem : tradeItemList) {
            if (trade.getVersion() < tradeItem.getVersion()) {
                throw new TradeException("Trade version is lower than version already saved in database");
            }
        }
    }

    private void maturityDateValidation(Trade trade) throws TradeException {
        if (trade.getMaturityDate().before(new Date())) {
            throw new TradeException("Maturity Date is less then today's date");
        }
    }

    public List<Trade> getTradeByTradeId(String tradeId) {
        return tradeStoreRepository.findByTradeId(tradeId);
    }

    public void saveOrUpdate(Trade trade) {
        tradeStoreRepository.save(trade);
    }

    public void saveAll(List<Trade> tradeList) {
        tradeStoreRepository.saveAll(tradeList);
    }

    public void delete(String id) {
        tradeStoreRepository.deleteByTradeId(id);
    }
}