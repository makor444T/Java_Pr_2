import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public interface DataReader {
    BufferedReader read(String source) throws IOException;
}
