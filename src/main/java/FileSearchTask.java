import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearchTask implements Callable<List<SearchResult>> {
    private final Path file;
    private final Pattern pattern;

    public FileSearchTask(Path file, Pattern pattern) {
        this.file = file;
        this.pattern = pattern;
    }

    @Override
    public List<SearchResult> call() throws Exception {
        var results = new ArrayList<SearchResult>();
        try (var reader = Files.newBufferedReader(file)) {

            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    results.add(new SearchResult(file.toString(), lineNumber, line));
                }


            }
        }catch (IOException e){
            System.out.println("Error reading file: " + file + " -> " + e.getMessage());
        }

        return results;
    }
}
