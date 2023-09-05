package com.cojuny;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        App app= new App();
        app.hello_world();
    }

    public String hello_world() {
        String msg = "Hello World!";
        System.out.println(msg);
        return(msg);
    }
}
