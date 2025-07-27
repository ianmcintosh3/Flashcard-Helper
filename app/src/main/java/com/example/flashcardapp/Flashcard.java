package com.example.flashcardapp;

public class Flashcard {

    private int flashcardID;
    private String subject;
    private String front;
    private String back;

    public Flashcard(){
        flashcardID = -1;
    }

    public int getFlashcardID(){
        return flashcardID;
    }
    public void setFlashcardID(int i){
        flashcardID = i;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String s){
        subject = s;
    }
    public String getFront(){
        return front;
    }
    public void setFront(String s){
        front = s;
    }
    public String getBack(){
        return back;
    }
    public void setBack(String s){
        back = s;
    }
}
