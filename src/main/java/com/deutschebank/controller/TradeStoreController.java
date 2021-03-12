package com.deutschebank.controller;

import com.deutschebank.model.Trade;
import com.deutschebank.service.TradeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeStoreController {
    @Autowired
    TradeStoreService tradeStoreService;

    @GetMapping("/tradeStore")
    private List<Trade> getTradeStore() {

        return tradeStoreService.getAllTradeStore();
    }

    @GetMapping("/tradeStore/{tradeId}")
    private List<Trade> getTradeStoreByTradeId(@PathVariable("tradeId") String tradeId) {
        return tradeStoreService.getTradeByTradeId(tradeId);
    }

    @DeleteMapping("/tradeStore/{tradeId}")
    private void deleteTradeStore(@PathVariable("tradeId") String tradeId) {
        tradeStoreService.delete(tradeId);
    }

    @PostMapping("/tradeStore")
    private String saveTradeStore(@RequestBody Trade trade) {
        tradeStoreService.saveOrUpdate(trade);
        return trade.getTradeId();
    }
}
