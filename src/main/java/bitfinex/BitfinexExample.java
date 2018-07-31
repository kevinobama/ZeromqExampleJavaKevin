package bitfinex;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import org.knowm.xchange.bitfinex.v1.BitfinexExchange;
import org.knowm.xchange.tulipex.RightbtcExchange;

public class BitfinexExample {

	public static void main(String[] args) {
		try {
			//getTickerRightbtc();
			getOrderBook();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getTickerRightbtc() throws IOException {
		try {			
			Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(RightbtcExchange.class.getName());
	
			MarketDataService marketDataService = bitstamp.getMarketDataService();
	
			Ticker ticker = marketDataService.getTicker(CurrencyPair.LTC_BTC);
			
			System.out.println(ticker.toString());			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
   }
	
	public static void getOrderBook() throws IOException {
		try {			
			Exchange exchange = ExchangeFactory.INSTANCE.createExchange(RightbtcExchange.class.getName());
	
			MarketDataService marketDataService = exchange.getMarketDataService();
	
			OrderBook orderBooks = marketDataService.getOrderBook(CurrencyPair.LTC_BTC);
			
			System.out.println(orderBooks.toString());			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
   }	
}
