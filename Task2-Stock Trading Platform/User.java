public class User {
    private String name;
    private double cash;
    private Portfolio portfolio;

    public User(String name) {
        this.name = name;
        this.cash = 10000;
        this.portfolio = new Portfolio();
    }

    public void buyStock(String symbol, int quantity, Market market) {
        Stock stock = market.getStock(symbol);
        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }
        double cost = stock.getPrice() * quantity;
        if (cash >= cost) {
            portfolio.buy(symbol, quantity, stock.getPrice());
            cash -= cost;
            System.out.printf("Bought %d shares of %s at $%.2f%n", quantity, symbol, stock.getPrice());
        } else {
            System.out.println("Not enough cash.");
        }
    }

    public void sellStock(String symbol, int quantity, Market market) {
        Stock stock = market.getStock(symbol);
        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }
        if (portfolio.sell(symbol, quantity, stock.getPrice())) {
            double revenue = quantity * stock.getPrice();
            cash += revenue;
            System.out.printf("Sold %d shares of %s at $%.2f%n", quantity, symbol, stock.getPrice());
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void viewPortfolio(Market market) {
        portfolio.viewHoldings(market);
        System.out.printf("Cash: $%.2f%n", cash);
        System.out.printf("Total Portfolio Value: $%.2f%n", (cash + portfolio.getTotalValue(market)));
    }

    public void viewHistory() {
        portfolio.viewHistory();
    }
}
