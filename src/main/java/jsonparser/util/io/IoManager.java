package jsonparser.util.io;

import jsonparser.model.json.value.JsonValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IoManager {

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String userInput = scanner.nextLine();
            System.out.println("Choose input method [1 - file, 2 - console input]. (Type \"\\return\" to stop)");
            switch (userInput){
                case "1":
                    System.out.println("Where is your file located? :");
                    String jsonString = readFromFile(scanner.nextLine());
                    
                    break;

                case "2":


                    break;
                case "\\return":
                    return;
            }
        }
    }

    private String readFromFile(String filePath){  //"/home/INTEXSOFT/leonid.keda/Downloads/json.txt"
        try {
            return new Scanner(new File(filePath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File ["+filePath+"] wasn't found!");
        }
    }




}
