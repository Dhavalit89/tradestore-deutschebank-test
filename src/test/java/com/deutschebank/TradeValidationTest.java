package com.deutschebank;

import com.deutschebank.exception.TradeException;
import com.deutschebank.model.Trade;
import com.deutschebank.service.TradeStoreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeValidationTest {

    @Autowired
    private TradeStoreService tradeStoreService;

    @Before
    public void setUp() throws Exception {
        tradeStoreService.saveAll(createTradeStoreList());
    }

    @Test
    public void shouldCreateNewTrade() throws Exception {
        Trade trade = new Trade("T5", 1, "CP-5", "B5", setDate("2021-05-20"), new Date(), "N");
        trade = tradeStoreService.generateTrade(trade);
        assertEquals(1, trade.getVersion());
    }

    @Test
    public void shouldOverrideTradeIfSameVersion() throws Exception {
        Trade trade = new Trade("T2", 2, "CP-5", "B5", setDate("2021-05-20"), new Date(), "N");
        trade = tradeStoreService.generateTrade(trade);
        assertEquals(2, trade.getVersion());
        assertEquals("CP-5", trade.getCounterPartyId());
    }

    @Test(expected = TradeException.class)
    public void shouldThrowExceptionIfTradeVersionIsLower() throws Exception {
        Trade trade = new Trade("T2", 1, "CP-2", "B1", setDate("2021-05-20"), new Date(), "N");
        tradeStoreService.generateTrade(trade);
    }

    @Test
    public void shouldAllowTradeHavingLessMaturityDateThenTodayDate() throws Exception {
        Trade trade = new Trade("T3", 3, "CP-2", "B1", setDate("2021-05-20"), new Date(), "N");
        trade = tradeStoreService.generateTrade(trade);
        assertEquals("CP-2", trade.getCounterPartyId());
    }

    @Test(expected = TradeException.class)
    public void shouldNotAllowTradeHavingLessMaturityDateThenTodayDate() throws Exception {
        Trade trade = new Trade("T3", 3, "CP-2", "B1", setDate("2020-05-20"), new Date(), "N");
        tradeStoreService.generateTrade(trade);
    }

    private List<Trade> createTradeStoreList() throws Exception {
        Trade trade1 = new Trade("T1", 1, "CP-1", "B1", setDate("2020-05-20"), new Date(), "N");
        Trade trade2 = new Trade("T2", 1, "CP-2", "B1", setDate("2021-05-20"), new Date(), "N");
        Trade trade3 = new Trade("T2", 2, "CP-1", "B1", setDate("2021-05-20"), new Date(2015 - 03 - 14), "N");
        Trade trade4 = new Trade("T3", 3, "CP-3", "B2", setDate("2014-05-20"), new Date(), "Y");
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade1);
        tradeList.add(trade2);
        tradeList.add(trade3);
        tradeList.add(trade4);
        return tradeList;
    }

    public Date setDate(String date) throws ParseException {
        SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
        return sdfo.parse(date);
    }
}
