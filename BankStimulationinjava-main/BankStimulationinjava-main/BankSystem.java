import java.util.*;

class BankSystem {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the Bank System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists! Please choose another one.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        users.put(username, new User(username, password));
        System.out.println("Registration successful!");
    }

    public static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            userMenu(user);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public static void userMenu(User user) {
        while (true) {
            System.out.println("\nHello, " + user.getUsername() + "!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Inquiry");
            System.out.println("4. Transaction History");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    deposit(user);
                    break;
                case 2:
                    withdraw(user);
                    break;
                case 3:
                    balanceInquiry(user);
                    break;
                case 4:
                    transactionHistory(user);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void deposit(User user) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
        } else {
            user.deposit(amount);
            System.out.println("Deposited successfully! Current balance: " + user.getBalance());
        }
    }

    public static void withdraw(User user) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else if (amount > user.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            user.withdraw(amount);
            System.out.println("Withdrawn successfully! Current balance: " + user.getBalance());
        }
    }

    public static void balanceInquiry(User user) {
        System.out.println("Your current balance is: " + user.getBalance());
    }

    public static void transactionHistory(User user) {
        System.out.println("\nTransaction History:");
        for (String transaction : user.getTransactions()) {
            System.out.println(transaction);
        }
    }
}

class User {
    private String username;
    private String password;
    private double balance;
    private List<String> transactions;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactions.add("Withdrew: " + amount);
    }
}
