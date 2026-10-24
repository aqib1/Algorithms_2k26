package ocp;

import java.util.Scanner;

public class BasicInput {
    /*
    * This is a multi-line comment
    *
    * */

    /**
     * This is a documentation comment - Javadoc
     * */
    static void main() {
//        var name = IO.readln("Enter your name: ");
//        System.out.println("Name : "+ name);

        try(var scanner = new Scanner(System.in)) {
            var line = "";
            System.out.println("Enter: ");
            while (!(line = scanner.next()).equals("Exit")) {
                System.out.println(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
