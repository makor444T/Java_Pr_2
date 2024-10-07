import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TransactionCSVReaderTest {
    @Test
    public void testReadTransactions() {
        List<Transaction> transactions = TransactionCSVReader.readTransactions("https://informer.com.ua/dut/java/pr2.csv");

        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій порожній");
        Assertions.assertEquals("Дохід", transactions.get(0).getDescription(), "Опис першої транзакції повинен бути 'Дохід'");
    }
}
