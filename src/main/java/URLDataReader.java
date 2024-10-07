import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class URLDataReader implements DataReader {
    @Override
    public BufferedReader read(String source) throws IOException {
        URL url = new URL(source);
        return new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
    }
}
