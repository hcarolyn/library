package ui;

import java.util.Scanner;

public class Tester {
    private Scanner input;
    private Tester tester;

    public Tester() {
        this.input = new Scanner(System.in);
        input.useDelimiter("\n");
        tryOut();
    }

    private void tryOut() {
        System.out.println("\nSelect from: ");
        System.out.println("\td -> deposit");
    }
}
