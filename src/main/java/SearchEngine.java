import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Pattern;

public class SearchEngine {

    public List<SearchResult> search(SearchConfig config) throws IOException, InterruptedException {

        List<Path> files = Files.walk(config.getRootPath()).filter(Files::isRegularFile).toList();

        var pattern = Pattern.compile(config.getPattern());
        var executorService = Executors.newFixedThreadPool(config.getThreadCount());

        List<Future<List<SearchResult>>> futures = new ArrayList<>();

        for(Path file: files){
            var task = new FileSearchTask(file, pattern);
            Future<List<SearchResult>> future = executorService.submit(task);
            futures.add(future);
        }

        List<SearchResult> allResults = new ArrayList<>();

        for(Future<List<SearchResult>> f : futures){
            try {
                allResults.addAll(f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        return allResults;
    }


}
