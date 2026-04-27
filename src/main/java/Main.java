import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        if(args.length<3){
            System.out.println("Usage: fileseeker <path> <regex> [threads]");
            return;
        }

        String directory = args[0];
        String redex = args[1];
        int threadCount = Integer.parseInt(args[2]);

        var config = new SearchConfig(directory, redex, threadCount);
        var results = new SearchEngine().search(config);

        if(results.isEmpty()){
            System.out.println("No matches found.");
        }else{

            results.forEach(r ->{
                System.out.printf("[%s:%d] %s%n", r.filePath(), r.lineNumber(), r.lineContent());
            });
            System.out.printf("%nFound %d match(es)%n", results.size());

        }

    }
}
