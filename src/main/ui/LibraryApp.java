// some methods (ex: runLibrary) methods are based on methods in the EdX TellerApp example

package ui;

import model.Book;
import model.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class LibraryApp {
    private Library lib;
    private Scanner input;

    public LibraryApp() {
        runLibrary();
    }

    private void runLibrary() {
        boolean keepGoing = true;

        initiate();

        while (keepGoing) {
            displayMenu();
            String command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you later!");
    }

    private void initiate() {
        lib = new Library();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("Welcome to your library!");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add or delete a book");
        System.out.println("\tv -> view a list of your books");
        System.out.println("\tr -> add a rating or read status for a book");
        System.out.println("\ts -> search for a book");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        if (command.equalsIgnoreCase("a")) {
            addOrDeleteMenu();
        } else if (command.equalsIgnoreCase("v")) {
            doView();
        } else if (command.equalsIgnoreCase("r")) {
            ratingOrStatusMenu();
        } else if (command.equalsIgnoreCase("s")) {
            searchMenu();
        } else {
            System.out.println("Invalid response. Returning to main menu...");
        }
    }

    private void addOrDeleteMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("\ta -> add a book");
        System.out.println("\td -> delete a book");

        String addOrDeleteChoice = input.next();

        if (addOrDeleteChoice.equalsIgnoreCase("a")) {
            doAdd();
        } else if (addOrDeleteChoice.equalsIgnoreCase("d")) {
            doDelete();
        } else {
            System.out.println("Invalid response. Returning to main menu...");
        }
    }

    private Book bookInformation() {
        System.out.println("What is the title?");
        String title = input.next();

        System.out.println("What is the genre of tbe book?");
        String genre = input.next();

        System.out.println("What is the name of the author?");
        String author = input.next();

        System.out.println("When is the publication year?");
        int year = Integer.parseInt(input.next());

        Book book = new Book(title, genre, author, year);
        return book;
    }


    private void doAdd() {
        Book toAddBook = bookInformation();

        if (toAddBook.getDatePublished() <= 2022) {
            lib.addBook(toAddBook);
            System.out.println("Book added! Returning to main menu...");
        } else {
            System.out.println("Invalid year. Returning to main menu...");
        }
    }

    private void doDelete() {
        Book toDeleteBook = bookInformation();

        if (lib.removeBook(toDeleteBook.getTitle())) {
            System.out.println("Book deleted! Returning to main menu...");
        } else {
            System.out.println("No such book exists. Returning to main menu...");
        }
    }

    private void doView() {
        System.out.println("There are " + lib.getLibSize() + " books in your library, including: ");
        System.out.println(lib.libBookInfo(lib.returnArrayLib()));
    }

    private void ratingOrStatusMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("\ta -> add a rating");
        System.out.println("\tr -> add a read status");

        String ratingOrStatusChoice = input.next();

        if (ratingOrStatusChoice.equalsIgnoreCase("a")) {
            doRating();
        } else if (ratingOrStatusChoice.equalsIgnoreCase("r")) {
            doStatus();
        } else {
            System.out.println("Invalid response. Returning to main menu...");
        }
    }


    private void doRating() {
        Book ratingBook = bookInformation();

        if (!lib.containsBook(ratingBook.getTitle())) {
            System.out.println("Invalid book. Returning to main menu...");
        } else {
            System.out.println("What rating would you like to give (out of 5)?");
            int rating = Integer.parseInt(input.next());

            if (1 <= rating && rating <= 5) {
                lib.replaceRating(ratingBook.getTitle(), rating);
            } else {
                System.out.println("Invalid rating. Returning to main menu...");
            }
        }
    }

    private void doStatus() {
        Book statusBook = bookInformation();

        if (!lib.containsBook(statusBook.getTitle())) {
            System.out.println("Invalid book. Returning to main menu...");
        } else {
            System.out.println("Insert t if you have read the book and f if you have not.");
            String status = input.next();

            if (status.equalsIgnoreCase("t")) {
                lib.replaceReadStatus(statusBook.getTitle(), true);
            } else if (status.equalsIgnoreCase("f")) {
                lib.replaceReadStatus(statusBook.getTitle(), false);
            } else {
                System.out.println("Invalid response. Returning to main menu...");
            }
        }
    }

    private void searchMenu() {
        System.out.println("\nSearch by: ");
        System.out.println("\tr -> rating");
        System.out.println("\tt -> title");
        System.out.println("\tg -> genre");
        System.out.println("\tu -> previously unread books");

        String searchChoice = input.next();

        if (searchChoice.equalsIgnoreCase("r")) {
            searchRating();
        } else if (searchChoice.equalsIgnoreCase("t")) {
            searchTitle();
        } else if (searchChoice.equalsIgnoreCase("g")) {
            searchGenre();
        } else if (searchChoice.equalsIgnoreCase("u")) {
            searchUnread();
        } else {
            System.out.println("Invalid response. Returning to main menu...");
        }
    }

    private void searchRating() {
        List<Integer> ratings = getRatingList();
        System.out.println(lib.libBookInfo(lib.searchRating(ratings)));
    }


    private List<Integer> getRatingList() {
        boolean keepGoing = true;
        List<Integer> ratings = new ArrayList<>();

        while (keepGoing) {
            System.out.println("What rating would you like to search for?");
            System.out.println("\t# -> a number from 1 to 5");
            System.out.println("\tq -> I'm done adding ratings to my search list!");
            String command = input.next();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else {
                int ratingA = Integer.parseInt(command);
                if (1 <= ratingA && ratingA <= 5) {
                    ratings.add(ratingA);
                }
                System.out.println("Try a valid number next time!");
            }
        }
        return ratings;
    }


    private void searchTitle() {
        System.out.println("What title would you like to search for?");
        String title = input.next();

        if (!lib.searchLibByTitles(title).isEmpty()) {
            System.out.println(lib.libBookInfo(lib.searchLibByTitles(title)));
        } else {
            System.out.println("Sorry, no books were found with that name.");
        }
    }

    private void searchGenre() {
        System.out.println("What genre would you like to search for?");
        String genre = input.next();

        if (!lib.searchLibByGenre(genre).isEmpty()) {
            System.out.println(lib.libBookInfo(lib.searchLibByGenre(genre)));
        } else {
            System.out.println("Sorry, no books were found with that genre.");
        }
    }

    private void searchUnread() {
        if (!lib.searchUnreadBooks().isEmpty()) {
            System.out.println(lib.libBookInfo(lib.searchUnreadBooks()));
        } else {
            System.out.println("Sorry, you've read everything :( ");
        }
    }

}