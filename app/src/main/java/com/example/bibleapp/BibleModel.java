package com.example.bibleapp;

public class BibleModel
{

    public BibleModel(String chapter, String verse, int backGroundColor) {

        this.chapter = chapter;
        this.verse = verse;
        BackGroundColor = backGroundColor;
    }

    String chapter;

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getVerse() {
        return verse;
    }
    public String getfullVerseText() {
        String fullVerse= verse+"\n"+chapter;

        return fullVerse;
    }
    public void setVerse(String verse) {
        this.verse = verse;
    }

    public int getBackGroundColor() {
        return BackGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        BackGroundColor = backGroundColor;
    }

    String verse;

    int BackGroundColor;
}
