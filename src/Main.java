import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("error");
        } else {
            Set<String> listOfFiles = listFilesUsingFileWalkAndVisitor(args[0]);

            FileWriter out = new FileWriter("output.txt");
            int c;
            for (String fileName : listOfFiles) {
                FileReader in = new FileReader(fileName);
                while ((c = in.read()) != -1) {
                    out.write((char) c);
                }
                in.close();
            }
            out.close();

            FileReader out2 = new FileReader("output.txt");
            Scanner scanner = new Scanner(out2);
            String line = "";
            String result = "";
            int count = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(args[1])) {
                    count++;
                    result += String.valueOf(count);
                    result += "---> ";
                    result += line;
                    result += '\n';
                }
            }
            out2.close();
            scanner.close();

            System.out.println(count);


            FileWriter in2 = new FileWriter("result.txt");
            in2.write(result);
            in2.close();

        }
    }

    public static Set<String> listFilesUsingFileWalkAndVisitor(String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (!Files.isDirectory(file)) {
                    fileList.add(file.toAbsolutePath().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;
    }


}