package model;

public class BookInSeries extends Book {
    int seriesSize;
    int numberInSeries;
    String nameOfSeries;

    public BookInSeries(String title, String genre, String author, int datePublished,
                        int seriesSize, int numberInSeries, String nameOfSeries) {
        super(title, genre, author, datePublished);
        this.numberInSeries = numberInSeries;
        this.seriesSize = seriesSize;
        this.nameOfSeries = nameOfSeries;
    }

    public int getSeriesSize() {
        return seriesSize;
    }

    public int getNumberInSeries() {
        return numberInSeries;
    }

    public String getNameOfSeries() {
        return nameOfSeries;
    }

    private String numberTranslator(int numberInSeries) {
        String numberEnding = "th";
        if (numberInSeries == 1) {
            return "1st";
        } else if (numberInSeries == 2) {
            return "2nd";
        } else if (numberInSeries == 3) {
            return "3rd";
        } else {
            return (numberInSeries) + numberEnding;
        }
    }

    public String bookInfo(BookInSeries bookInSeries) {
        String info = super.bookInfo(bookInSeries);
        info = info + ". This is the " + numberTranslator(bookInSeries.getNumberInSeries())
                + " book in the series " + bookInSeries.getNameOfSeries();
        return info;
    }
}
