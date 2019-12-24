package jsonparser.util;

import java.util.Scanner;

public class IoManager {

    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any json string: ");
        return scanner.nextLine();
    }

}
