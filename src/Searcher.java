import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Searcher {

    static boolean outputFileCreated = false;
    static boolean resultFileCreated = false;

    static String extension = "";
    public static void main(String[] args) throws IOException {

        Files.deleteIfExists(Paths.get("output123Ver1.txt"));
        Files.deleteIfExists(Paths.get("result123Ver1.txt"));
        String search = "";
        String path = "";
        int numberOfLinesAfterResult = 1;
        boolean verbose = false;

        if (args.length == 1) {
            search = args[0];
            path = Paths.get("").toAbsolutePath().toString();
        }
        else if (args.length == 2) {
            search = args[0];
            path = args[1];
        }
        else if (args.length == 3) {
            search = args[0];
            path = args[1];
            numberOfLinesAfterResult = Integer.valueOf(args[2]);
        }
        else if (args.length == 4) {
            search = args[0];
            path = args[1];
            numberOfLinesAfterResult = Integer.valueOf(args[2]);
            if (args[3].equals("-verbose")) {
                verbose = true;
            }
            else {
                verbose = false;
            }
        }

        else if (args.length == 5) {
            search = args[0];
            path = args[1];
            numberOfLinesAfterResult = Integer.valueOf(args[2]);
            if (args[3].equals("-verbose")) {
                verbose = true;
            }
            else {
                verbose = false;
            }

            extension = args[4];
        }

        else {
            System.out.println("Usage: java Searcher search_text <path> <number_of_lines_after_result> <-verbose>");
            System.out.println("Min usage: java Searcher search_text");
            System.exit(255);
        }

            Set<String> listOfFiles = listFiles(path);
            System.out.print("Creating output123Ver1.txt");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!outputFileCreated) {
                            Thread.sleep(100);
                            System.out.print(".");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();

            
            FileWriter out = new FileWriter("output123Ver1.txt");
            int c;
            for (String fileName : listOfFiles) {
                FileReader in = new FileReader(fileName);
                while ((c = in.read()) != -1) {
                    out.write((char) c);
                }
                in.close();
            }
            out.close();
            outputFileCreated = true;

            System.out.print("\nCreating result123Ver1.txt");
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!resultFileCreated) {
                            Thread.sleep(100);
                            System.out.print(".");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread2.start();

            FileReader out2 = new FileReader("output123Ver1.txt");
            Scanner scanner = new Scanner(out2);
            String line = "";
            String result = "";
            int occurrences = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(search)) {
                    occurrences++;
                    result += String.valueOf(occurrences);
                    result += "---> ";
                    result += line;
                    result += '\n';
                    for (int i = 0; i < numberOfLinesAfterResult; i++) {
                        if (scanner.hasNextLine()) {
                            result+=scanner.nextLine();
                            result+="\n";
                        }
                    }
                    result+="\n";
                }
            }
            out2.close();
            scanner.close();

            resultFileCreated = true;


            FileWriter in2 = new FileWriter("result123Ver1.txt");
            in2.write(result);
            in2.close();

            if (verbose) {
                System.out.println(result);
            }

    }

    public static Set<String> listFiles(String path) throws IOException {
        Set<String> files = new HashSet<>();
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (!Files.isDirectory(file) && !file.toString().contains(".class")
                && file.toString().contains(extension)) {
                    files.add(file.toAbsolutePath().toString());
                }

                return FileVisitResult.CONTINUE;
            }
        });
        return files;

    }
}