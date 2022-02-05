package model;

public class Series extends Book {
    int seriesSize;
    int numberInSeries;

    public Series(String title, String genre, String author, int datePublished, int seriesSize, int numberInSeries) {
        super(title, genre, author, datePublished);
        this.numberInSeries = numberInSeries;
        this.seriesSize = numberInSeries;
    }


}
