import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        return (int) transactions.stream()
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
                    String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                    return transactionMonthYear.equals(monthYear);
                })
                .count();
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Transaction findSmallestExpense(List<Transaction> transactions, String startDate, String endDate) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> isWithinDateRange(t.getDate(), startDate, endDate))
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    public static Transaction findLargestExpense(List<Transaction> transactions, String startDate, String endDate) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> isWithinDateRange(t.getDate(), startDate, endDate))
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    private static boolean isWithinDateRange(String transactionDate, String startDate, String endDate) {
        LocalDate date = LocalDate.parse(transactionDate, dateFormatter);
        LocalDate start = LocalDate.parse(startDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);
        return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
    }
}
