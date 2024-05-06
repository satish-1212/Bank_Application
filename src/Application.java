import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Customer> customers = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCustomer");
            System.out.println("1. Customer Login");
            System.out.println("2. New Customer Sign in");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exited Application successfully...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Customer customer = customers.get(name);
        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Welcome " + name + ".....");
            performBankOperations(customer);
        } else {
            System.out.println("Not a valid customer...");
        }
    }

    private static void register() {
        System.out.print("Enter New Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        

        if (customers.containsKey(name)) {
            System.out.println("Customer already exists.");
            return;
        }

        Customer newCustomer = new Customer(name, password);
        customers.put(name, newCustomer);
        System.out.println("Customer registered successfully.");
    }

    private static void performBankOperations(Customer customer) {
        while (true) {
            System.out.println("\n Account Details");
            System.out.println("a. Amount Deposit");
            System.out.println("b. Amount Withdrawal");
            System.out.println("c. Check Balance");
            System.out.println("d. Exit");

            System.out.print("Choose an option: ");
            String option = scanner.next();

            switch (option.toLowerCase()) {
                case "a":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount > 0) {
                        customer.deposit(depositAmount);
                        System.out.println("New balance: " + customer.getBalance());
                    } else {
                        System.out.println("Invalid amount. Please enter a positive number.");
                    }
                    break;
                case "b":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount > 0) {
                        if (!customer.withdraw(withdrawAmount)) {
                            System.out.println("Insufficient balance to withdraw that amount.");
                        } else {
                            System.out.println("New balance: " + customer.getBalance());
                        }
                    } else {
                        System.out.println("Invalid amount. Please enter a positive number.");
                    }
                    break;
                case "c":
                    System.out.println("Current balance: " + customer.getBalance());
                    break;
                case "d":
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }

    static class Customer {
        private String name;
        private String password;
        private double balance;

        public Customer(String name, String password) {
            this.name = name;
            this.password = password;
            this.balance = 0.0;
        }

        public String getPassword() {
            return password;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }
}
