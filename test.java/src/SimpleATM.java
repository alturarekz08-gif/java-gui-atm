import java.util.Scanner;

public class SimpleATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 1000.00; // Starting balance
        int choice;

        do {
            System.out.println("\n===========================");
            System.out.println("   WELCOME TO JAVA ATM");
            System.out.println("===========================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nYour current balance: ₱" + balance);
                    break;

                case 2:
                    System.out.print("\nEnter deposit amount: ₱");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("✅ Successfully deposited ₱" + deposit);
                        System.out.println("Your new balance: ₱" + balance);
                    } else {
                        System.out.println("⚠ Invalid deposit amount.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter withdrawal amount: ₱");
                    double withdraw = scanner.nextDouble();
                    if (withdraw > 0 && withdraw <= balance) {
                        balance -= withdraw;
                        System.out.println("💸 You withdrew ₱" + withdraw);
                        System.out.println("Your new balance: ₱" + balance); // optional, for learning
                    } else if (withdraw > balance) {
                        System.out.println("⚠ Insufficient balance!");
                    } else {
                        System.out.println("⚠ Invalid amount.");
                    }
                    break;

                case 4:
                    System.out.println("\nThank you for using Java ATM!");
                    break;

                default:
                    System.out.println("\n⚠ Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
