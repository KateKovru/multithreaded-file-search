# FileSeeker
Java CLI application for searching text in files using regex and multithreading.

## Features
- Recursive file search (Files.walk)
- Regex support
- Parallel processing using ExecutorService
- Each file is processed in a separate task
- Returns file path, line number, and content
- Unit tests included

## Technologies
- Java
- ExecutorService
- java.nio.file
- Regex (Pattern)
- JUnit

## How to Run

Run Main class with arguments:
```bash
<directory> <regex> <threads>
```
Example:
```bash
"C:\test-data" hello 4
```

## Example Output
```bash
[file1.txt:1] hello world
[file2.txt:2] hello again
Found 2 match(es)
```

## What I Learned
- Multithreading in Java
- Working with file system
- Regex matching
- Handling concurrency with Futures

