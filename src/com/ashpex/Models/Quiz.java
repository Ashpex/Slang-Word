package com.ashpex.Models;

import java.util.Random;

public class Quiz {
    public String[] questions;
    private int answer;
    private int score;

    public Quiz() {
        questions = new String[5];
    }

    public Quiz setQuestions(SlangHashMap slangsHashMap, int type){
        Random random = new Random();
        int index = random.nextInt(4) + 1;
        SlangWord word = slangsHashMap.RandomSlang();
        if (type == 1){
            questions[0] = word.getSlang();
            for(int i = 1; i < questions.length; i++){
                if (i != index){
                    questions[i] = slangsHashMap.RandomSlang().getDefinition();
                }
            }
            answer = index;
            questions[index] = word.getDefinition();
            return this;
        }
        else{
            questions[0] = word.getDefinition();
            for(int i = 1; i < questions.length; i++){
                if (i != index){
                    questions[i] = slangsHashMap.RandomSlang().getSlang();
                }
            }
            answer = index;
            questions[index] = word.getSlang();
            return this;
        }

    }
    public boolean checkAnswer(int answer){
        if (this.answer == answer){
            return true;
        }
        return false;
    }

    public String[] getQuestion() {
        return questions;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }

}
