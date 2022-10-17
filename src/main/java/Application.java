import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        System.out.println("Lufthansa Fast Bag Drop");
        String allData="src/main/java/Data/assignment.csv";
        String bagContents="src/main/java/Data/baggage_content.txt";

        readDataTest(allData);
        readDataTest(bagContents);
        System.getProperty("line.separator");
    }

    public static void readDataTest(String filename) throws IOException {
       /* try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(filename));
            String line;
            while((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }

        }   catch (Exception e){
            e.printStackTrace();
       */
        List<String> myLIne=Files.readAllLines(Path.of(filename));
        for(String line: myLIne){
            System.out.println(line);
        }
    }



    public static void readTextFile(String filename) throws FileNotFoundException {
        File file=new File(filename);
        Scanner scan=new Scanner(file);
        System.out.println(scan.nextLine());

    }
}
