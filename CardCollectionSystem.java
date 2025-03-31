import java.util.*;

class CardCollection {
    private final Map<String, List<String>> cards = new HashMap<>();

    public void addCard(String symbol, String card) {
        cards.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cards.getOrDefault(symbol, Collections.emptyList());
    }
}

public class CardCollectionSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CardCollection collection = new CardCollection();

    public static void main(String[] args) {
        addSampleCards();

        while (true) {
            System.out.println("\n1. Search Cards  2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> searchCards();
                case 2 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addSampleCards() {
        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Spades", "Jack");
        collection.addCard("Diamonds", "10");
        collection.addCard("Clubs", "9");
    }

    private static void searchCards() {
        System.out.print("Enter card symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        List<String> cards = collection.getCardsBySymbol(symbol);

        if (cards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards: " + String.join(", ", cards));
        }
    }
}

