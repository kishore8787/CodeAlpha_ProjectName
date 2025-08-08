import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private String symbol;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;

    public Transaction(String type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return timestamp + " - " + type.toUpperCase() + " " + quantity + " shares of " + symbol + " at $" + price;
    }
}
