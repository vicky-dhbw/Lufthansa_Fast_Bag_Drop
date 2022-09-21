import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Lufthansa Fast Bag Drop");
        String allData="src/main/java/Data/assignment.csv";
        String bagContents="src/main/java/Data/baggage_content.txt";

        readDataTest(allData);
        //readTextFile(bagContents);
        System.getProperty("line.separator");
    }

    public static void readDataTest(String filename){
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(filename));
            String line;
            while((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }

        }   catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void readTextFile(String filename) throws FileNotFoundException {
        File file=new File(filename);
        Scanner scan=new Scanner(file);

        while (scan.hasNextLine()){
            System.out.println(scan.nextLine());
        }
    }
}
