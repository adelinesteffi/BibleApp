package com.example.bibleapp;

public class BibleItem {

    public BibleItem(String bookName, String chapterNumber, String verseNumber, String verseText, String timeStap, boolean isFav) {
        this.bookName = bookName;
        this.chapterNumber = chapterNumber;
        this.verseNumber = verseNumber;
        this.verseText = verseText;
        this.timeStap = timeStap;
        this.isFav = isFav;
    }

    String bookName;
    String chapterNumber;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(String verseNumber) {
        this.verseNumber = verseNumber;
    }

    public String getVerseText() {
        return verseText;
    }

    public void setVerseText(String verseText) {
        this.verseText = verseText;
    }

    public String getTimeStap() {
        return timeStap;
    }

    public void setTimeStap(String timeStap) {
        this.timeStap = timeStap;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    String verseNumber;

    String verseText;

    String timeStap;

    boolean isFav;

}
