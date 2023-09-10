package com.cojuny;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        App app= new App();
        app.hello_world();
        System.out.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for the Enter key
        scanner.close();
    }

    public String hello_world() {
        String msg = "Hello World!";
        System.out.println(msg);
        return(msg);
    }
}
