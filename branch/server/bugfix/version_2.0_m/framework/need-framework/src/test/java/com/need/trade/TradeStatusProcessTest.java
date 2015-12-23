package com.need.trade;

import junit.framework.Assert;

import org.junit.Test;

import com.need.trade.enums.TradeStatus;

/**
 * 
 * @author david
 *
 */
public class TradeStatusProcessTest {
	
   @Test
   public void test_tradeProcess(){
	  TradeStatus tradeStatus= TradeStatus.WAIT_RECEIVE;
	  TradeStatus  processTradeStatus=TradeStatusProcess.tradeProcess(tradeStatus);
	  Assert.assertEquals(TradeStatus.TRADE_SUCCESS, processTradeStatus);
	   
   }

   @Test
   public void test_refundTradeProcess(){
	  TradeStatus tradeStatus= TradeStatus.WAIT_RECEIVE;
	  TradeStatus  processTradeStatus=TradeStatusProcess.refundProcess(tradeStatus);
	  Assert.assertEquals(TradeStatus.REFUND_SUCCESS, processTradeStatus);
	   
   }
   
   @Test
   public void test_cancelTradeProcess(){
	  TradeStatus tradeStatus= TradeStatus.WAIT_PAY;
	  TradeStatus  processTradeStatus=TradeStatusProcess.cancelTradeProcess(tradeStatus);
	  Assert.assertEquals(TradeStatus.TRADE_CLOSE, processTradeStatus);
	   
   }
   
   
}
