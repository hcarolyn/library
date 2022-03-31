//// some methods (ex: runLibrary) are based on methods in the EdX TellerApp example
//// some methods are also based on the EdX JsonSerializationDemo example!
//
//package ui;
//
//import model.Book;
//import model.Library;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//// Library application with various features (ex: adding books)
//public class LibraryApp {
//    private static final String JSON_STORE = "./data/library.json";
//    private Library lib;
//    private Scanner input;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // EFFECTS: runs the library application
//    public LibraryApp() throws FileNotFoundException {
//        start();
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runLibrary();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: starts the library and scanner systems
//    private void start() {
//        lib = new Library("Library");
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user inputs
//    private void runLibrary() {
//        boolean keepGoing = true;
//
//        System.out.println("Welcome to your library!");
//
//        while (keepGoing) {
//            displayMenu();
//            String command = input.next();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nSee you later!");
//    }
//
//    // EFFECTS: displays main menu options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ta -> add or delete a book");
//        System.out.println("\tv -> view a list of your books");
//        System.out.println("\tr -> add a rating or read status for a book");
//        System.out.println("\tf -> find/search for a book");
//        System.out.println("\ts -> save library to file");
//        System.out.println("\tl -> load library from file");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user inputs
//    private void processCommand(String command) {
//        if (command.equalsIgnoreCase("a")) {
//            addOrDeleteMenu();
//        } else if (command.equalsIgnoreCase("v")) {
//            doView();
//        } else if (command.equalsIgnoreCase("r")) {
//            ratingOrStatusMenu();
//        } else if (command.equalsIgnoreCase("f")) {
//            searchMenu();
//        } else if (command.equalsIgnoreCase("s")) {
//            saveLibrary();
//        } else if (command.equalsIgnoreCase("l")) {
//            loadLibrary();
//        } else {
//            System.out.println("Invalid response. Returning to main menu...");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: displays add or delete menu to user, processes following user inputs
//    private void addOrDeleteMenu() {
//        System.out.println("\nSelect from: ");
//        System.out.println("\ta -> add a book");
//        System.out.println("\td -> delete a book");
//
//        String addOrDeleteChoice = input.next();
//
//        if (addOrDeleteChoice.equalsIgnoreCase("a")) {
//            doAdd();
//        } else if (addOrDeleteChoice.equalsIgnoreCase("d")) {
//            doDelete();
//        } else {
//            System.out.println("Invalid response. Returning to main menu...");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds a book to the library
//    private void doAdd() {
//        System.out.println("What is the title?");
//        String title = input.next();
//
//        System.out.println("What is the genre of tbe book?");
//        String genre = input.next();
//
//        System.out.println("What is the name of the author?");
//        String author = input.next();
//
//        System.out.println("When is the publication year?");
//        int year = Integer.parseInt(input.next());
//
//        Book toAddBook = new Book(title, genre, author, year);
//
//        if (toAddBook.getDatePublished() <= 2022) {
//            lib.addBook(toAddBook);
//            System.out.println("Book added! Returning to main menu...");
//        } else {
//            System.out.println("Invalid year. Returning to main menu...");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: deletes a book from the library
//    private void doDelete() {
//        System.out.println("What is the title?");
//        String title = input.next();
//
//        if (lib.removeBook(title)) {
//            System.out.println("Book deleted! Returning to main menu...");
//        } else {
//            System.out.println("No such book exists. Returning to main menu...");
//        }
//    }
//
//    // EFFECTS: tells users how many books are in the library
//    private void doView() {
//        if (lib.getLibSize() == 0) {
//            System.out.println("Sorry, there are no books in your library.");
//        } else {
//            System.out.println("There are " + lib.getLibSize() + " books in your library, including: ");
//            System.out.println(lib.libBookInfo(lib.getArrayLib()));
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: displays change rating/reading status to users, processes following user inputs
//    private void ratingOrStatusMenu() {
//        System.out.println("\nSelect from: ");
//        System.out.println("\ta -> add a rating");
//        System.out.println("\tr -> add a read status");
//
//        String ratingOrStatusChoice = input.next();
//
//        if (ratingOrStatusChoice.equalsIgnoreCase("a")) {
//            doRating();
//        } else if (ratingOrStatusChoice.equalsIgnoreCase("r")) {
//            doStatus();
//        } else {
//            System.out.println("Invalid response. Returning to main menu...");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: changes the rating of a book in the library
//    private void doRating() {
//        System.out.println("What is the title?");
//        String title = input.next();
//
//        if (!lib.containsBook(title)) {
//            System.out.println("Invalid book. Returning to main menu...");
//        } else {
//            System.out.println("What rating would you like to give (out of 5)?");
//            int rating = Integer.parseInt(input.next());
//
//            if (1 <= rating && rating <= 5) {
//                lib.replaceRating(title, rating);
//            } else {
//                System.out.println("Invalid rating. Returning to main menu...");
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: changes the reading status of a book in the library
//    private void doStatus() {
//        System.out.println("What is the title?");
//        String title = input.next();
//
//        if (!lib.containsBook(title)) {
//            System.out.println("Invalid book. Returning to main menu...");
//        } else {
//            System.out.println("Insert t if you have read the book and f if you have not.");
//            String status = input.next();
//
//            if (status.equalsIgnoreCase("t")) {
//                lib.replaceReadStatus(title, true);
//            } else if (status.equalsIgnoreCase("f")) {
//                lib.replaceReadStatus(title, false);
//            } else {
//                System.out.println("Invalid response. Returning to main menu...");
//            }
//        }
//    }
//
//    // EFFECTS: Displays the search options to users, processes the following user commands
//    private void searchMenu() {
//        System.out.println("\nSearch by: ");
//        System.out.println("\tr -> rating");
//        System.out.println("\tt -> title");
//        System.out.println("\tg -> genre");
//        System.out.println("\tu -> previously unread books");
//
//        String searchChoice = input.next();
//
//        if (searchChoice.equalsIgnoreCase("r")) {
//            searchRating();
//        } else if (searchChoice.equalsIgnoreCase("t")) {
//            searchTitle();
//        } else if (searchChoice.equalsIgnoreCase("g")) {
//            searchGenre();
//        } else if (searchChoice.equalsIgnoreCase("u")) {
//            searchUnread();
//        } else {
//            System.out.println("Invalid response. Returning to main menu...");
//        }
//    }
//
//    // EFFECTS: shows readers the books in their library that match their desired rating
//    private void searchRating() {
//        List<Integer> ratings = getRatingList();
//        ArrayList<Book> searchResult = lib.searchRating(ratings);
//
//        if (!ratings.isEmpty()) {
//            System.out.println("\tThere were " + searchResult.size() + " books with your ratings, including: ");
//            System.out.println(lib.libBookInfo(searchResult));
//        } else {
//            System.out.println("Sorry, no books were found with that rating.");
//        }
//    }
//
//    // EFFECTS: allows readers to input the number ratings they wish to search for
//    private List<Integer> getRatingList() {
//        boolean keepGoing = true;
//        List<Integer> ratings = new ArrayList<>();
//
//        while (keepGoing) {
//            System.out.println("Type n to add a rating to search for, or type q to search.");
//            String command = input.next();
//            if (command.equalsIgnoreCase("q")) {
//                keepGoing = false;
//            } else if (command.equalsIgnoreCase("n")) {
//                System.out.println("Add a number from 1 to 5");
//                int ratingA = Integer.parseInt(input.next());
//                if (1 <= ratingA && ratingA <= 5) {
//                    ratings.add(ratingA);
//                    System.out.println("Number added!");
//                } else {
//                    System.out.println("Try a valid number next time!");
//                }
//            } else {
//                System.out.println("Invalid response. Let's try again!");
//            }
//        }
//        return ratings;
//    }
//
//    // EFFECTS: shows readers the books in their library that match their desired title
//    private void searchTitle() {
//        System.out.println("What title would you like to search for?");
//        String title = input.next();
//
//        ArrayList<Book> searchTitleList = lib.searchTitle(title);
//
//        if (!searchTitleList.isEmpty()) {
//            System.out.println("\tThere were " + searchTitleList.size() + " books with your title, including: ");
//            System.out.println(lib.libBookInfo(searchTitleList));
//        } else {
//            System.out.println("Sorry, no books were found with that name.");
//        }
//    }
//
//    // EFFECTS: shows readers the books in their library that match their desired genre
//    private void searchGenre() {
//        System.out.println("What genre would you like to search for?");
//        String genre = input.next();
//
//        ArrayList<Book> searchGenreList = lib.searchGenre(genre);
//
//        if (!searchGenreList.isEmpty()) {
//            System.out.println("\tThere were " + searchGenreList.size() + " books in your genre, including: ");
//            System.out.println(lib.libBookInfo(searchGenreList));
//        } else {
//            System.out.println("Sorry, no books were found with that genre.");
//        }
//    }
//
//    // EFFECTS: shows readers the unread books in their library
//    private void searchUnread() {
//        if (!lib.searchUnreadBooks().isEmpty()) {
//            System.out.println("\tThere were " + lib.searchUnreadBooks().size() + " unread books, including: ");
//            System.out.println(lib.libBookInfo(lib.searchUnreadBooks()));
//        } else {
//            System.out.println("Sorry, you've read everything :( ");
//        }
//    }
//
//    // EFFECTS: saves the library to file
//    private void saveLibrary() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(lib);
//            jsonWriter.close();
//            System.out.println("Saved " + lib.getLibName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads the library from file
//    private void loadLibrary() {
//        try {
//            lib = jsonReader.read();
//            System.out.println("Loaded " + lib.getLibName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//}