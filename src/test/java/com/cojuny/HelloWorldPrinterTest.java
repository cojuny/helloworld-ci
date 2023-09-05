package com.cojuny;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloWorldPrinterTest {

    @Test
    public void testPrintHelloWorld() {
        App app = new App();
        assertEquals("Hello World!\n", app.hello_world());
    }
}