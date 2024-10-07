import java.util.List;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("\nКількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\n10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void generateExpenseReport(List<Transaction> transactions) {
        String[] categories = new String[transactions.size()];
        double[] sums = new double[transactions.size()];
        int categoryCount = 0;

        System.out.println("\nЗвіт:");
        for (Transaction transaction : transactions) {
            String category = transaction.getDescription();
            double amount = transaction.getAmount();

            boolean categoryFound = false;
            for (int i = 0; i < categoryCount; i++) {
                if (categories[i].equals(category)) {
                    sums[i] += amount;
                    categoryFound = true;
                    break;
                }
            }

            if (!categoryFound) {
                categories[categoryCount] = category;
                sums[categoryCount] = amount;
                categoryCount++;
            }
        }

        for (int i = 0; i < categoryCount; i++) {
            String category = categories[i];
            double amount = sums[i];
            if (amount < 0) {
                int symbolCount = (int) (Math.abs(amount) / 1000);
                System.out.println(category + ": " + "*".repeat(Math.max(0, symbolCount)));
            } else {
                System.out.println(category + ": " + amount);
            }
        }
    }
}
