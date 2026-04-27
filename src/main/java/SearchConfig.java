import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class SearchConfig {
    private final Path rootPath;
    private final Pattern pattern;
    private final int threadCount;

    public SearchConfig(String rootPath, String pattern, int threadCount) {
        this.rootPath = Paths.get(rootPath);
        this.pattern = Pattern.compile(pattern);
        this.threadCount = threadCount;
    }

    public Path getRootPath() {
        return rootPath;
    }

    public String getPattern() {
        return pattern.toString();
    }

    public int getThreadCount() {
        return threadCount;
    }
}
