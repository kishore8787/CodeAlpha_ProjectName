import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();
        User user = new User("Alice");

        while (true) {
            market.updateMarket();
            market.displayMarket();

            System.out.println("\nOptions: 1) Buy 2) Sell 3) View Portfolio 4) View History 5) Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQty = Integer.parseInt(scanner.nextLine());
                    user.buyStock(buySymbol, buyQty, market);
                    break;

                case "2":
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQty = Integer.parseInt(scanner.nextLine());
                    user.sellStock(sellSymbol, sellQty, market);
                    break;

                case "3":
                    user.viewPortfolio(market);
                    break;

                case "4":
                    user.viewHistory();
                    break;

                case "5":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
