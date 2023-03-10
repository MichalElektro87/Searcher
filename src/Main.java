import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        File folder = new File("C:\\Users\\Z0216921\\files");
        File[] listOfFiles = folder.listFiles();
        FileWriter fin = new FileWriter("output.txt");

        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                 System.out.println("Name of directory: " + file.getName());
                 File[] listCurrentFiles = file.listFiles();
                 for (File currentFile : listCurrentFiles) {
                     if (currentFile.isFile()) {
                         FileReader fr = new FileReader(currentFile);
                         int c;
                         while ((c= fr.read()) != -1)
                             fin.write((char) (c));
                         fr.close();

                     }
                 }
            }
            if (file.isFile()) {
                FileReader fr = new FileReader(file);
                int c;
                while ((c= fr.read()) != -1)
                    fin.write((char) (c));
                fr.close();
            }
        }
        fin.close();


        if (args.length > 0) {

        }


    }
}