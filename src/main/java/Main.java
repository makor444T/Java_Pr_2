import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        Transaction largestExpense = TransactionAnalyzer.findLargestExpense(transactions, "01-01-2023", "31-12-2023");
        Transaction smallestExpense = TransactionAnalyzer.findSmallestExpense(transactions, "01-01-2023", "31-12-2023");

        System.out.println("\nНайбільша витрата: " + (largestExpense != null ? largestExpense : "Витрат не знайдено"));
        System.out.println("\nНайменша витрата: " + (smallestExpense != null ? smallestExpense : "Витрат не знайдено"));

        TransactionReportGenerator.generateExpenseReport(transactions);
        System.out.println("\n");

        transactions.forEach(System.out::println);
    }
}
