package ui;

import java.io.FileNotFoundException;

// Class that plays the library application
public class Main {
    public static void main(String[] args) {
        try {
            new LibraryApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file");
        }
    }
}
