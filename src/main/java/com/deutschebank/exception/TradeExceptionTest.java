package com.deutschebank.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TradeExceptionTest {

    public static final String TRADE_EXCEPTION_OCCURRED = "Trade exception occurred";

    @Test
    public void whenDaoExceptionIsCreated_InternalServerErrorCodeIsThrown() {
        TradeException tradeException = new TradeException(TRADE_EXCEPTION_OCCURRED);

        assertEquals(tradeException.message, TRADE_EXCEPTION_OCCURRED);
    }

    @Test(expected = TradeException.class)
    public void DaoExceptionIsThrown() throws TradeException {

        throw new TradeException(TRADE_EXCEPTION_OCCURRED);
    }
}
