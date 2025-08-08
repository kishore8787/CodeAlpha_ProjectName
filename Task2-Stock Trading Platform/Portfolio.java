import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private List<Transaction> history = new ArrayList<>();

    public void buy(String symbol, int quantity, double price) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        history.add(new Transaction("buy", symbol, quantity, price));
    }

    public boolean sell(String symbol, int quantity, double price) {
        int current = holdings.getOrDefault(symbol, 0);
        if (current >= quantity) {
            holdings.put(symbol, current - quantity);
            history.add(new Transaction("sell", symbol, quantity, price));
            return true;
        }
        return false;
    }

    public void viewHoldings(Market market) {
        System.out.println("\n--- Your Portfolio ---");
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            Stock stock = market.getStock(symbol);
            double value = qty * stock.getPrice();
            System.out.printf("%s: %d shares | Value: $%.2f%n", symbol, qty, value);
        }
    }

    public double getTotalValue(Market market) {
        double total = 0;
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            Stock stock = market.getStock(symbol);
            total += qty * stock.getPrice();
        }
        return total;
    }

    public void viewHistory() {
        System.out.println("\n--- Transaction History ---");
        for (Transaction t : history) {
            System.out.println(t);
        }
    }
}
