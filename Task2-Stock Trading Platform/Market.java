import java.util.HashMap;
import java.util.Map;

public class Market {
    private Map<String, Stock> stocks = new HashMap<>();

    public Market() {
        stocks.put("AAPL", new Stock("AAPL", 150));
        stocks.put("GOOG", new Stock("GOOG", 2800));
        stocks.put("TSLA", new Stock("TSLA", 700));
    }

    public void updateMarket() {
        for (Stock stock : stocks.values()) {
            stock.updatePrice();
        }
    }

    public void displayMarket() {
        System.out.println("\n--- Market Prices ---");
        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol.toUpperCase());
    }
}
