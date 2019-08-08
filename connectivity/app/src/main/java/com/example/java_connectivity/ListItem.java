package com.example.java_connectivity;

public class ListItem {

    private String question;
    private String answer;

    public ListItem(String question, String answer){

        this.question=question;
        this.answer=answer;

    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {

        return answer;
    }
}
