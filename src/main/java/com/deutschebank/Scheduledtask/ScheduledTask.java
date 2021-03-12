package com.deutschebank.Scheduledtask;

import com.deutschebank.model.Trade;
import com.deutschebank.service.TradeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Scheduler will start with application start and will keep checking everyone 1 min if there is any trades which
//trade date is more then maturity date
@Service
public class ScheduledTask {

    private static final int SCHEDULED_TASK_INITIAL_DELAY = 0;
    private static final int SCHEDULED_TASK_DELAY_BETWEEN_RUNS = 1;
    private static final String MINUTES = "MINUTES";
    private static final String EXPIRED = "Y";

    @Autowired
    private TradeStoreService tradeStoreService;

    @PostConstruct
    private void init() {
        configure();
    }

    public void configure() {
        ScheduledExecutorService h2Scheduler = Executors.newScheduledThreadPool(1);
        h2Scheduler.scheduleWithFixedDelay(scheduledTask, SCHEDULED_TASK_INITIAL_DELAY,
                SCHEDULED_TASK_DELAY_BETWEEN_RUNS, TimeUnit.valueOf(MINUTES));
    }

    Runnable scheduledTask = () -> {
        List<Trade> tradeList = tradeStoreService.getAllTradeStore();
        if (!tradeList.isEmpty()) {
            tradeList.forEach(trade -> checkAndUpdateExpiry(trade));
        }
    };

    private void checkAndUpdateExpiry(Trade trade) {
        if (trade.getCreatedDate().after(trade.getMaturityDate())) {
            trade.setExpired(EXPIRED);
            tradeStoreService.saveOrUpdate(trade);
        }
    }
}

