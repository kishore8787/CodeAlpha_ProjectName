public class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() { return symbol; }

    public double getPrice() { return price; }

    public void updatePrice() {
        double change = price * (Math.random() * 0.1 - 0.05);
        price = Math.round((price + change) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return symbol + ": $" + price;
    }
}
