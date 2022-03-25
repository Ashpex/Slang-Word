package com.ashpex;

import java.io.FileNotFoundException;
import java.util.Random;

public class Game {
    public String[] questions;
    private int answer;
    private int score;

    public Game() {
        questions = new String[5];
    }

    public Game setQuestions(ListSlang listSlangs, int type){
        Random random = new Random();
        int index = random.nextInt(4) + 1;
        SlangWord word = listSlangs.RandomSlang();
        if (type == 1){
            questions[0] = word.getSlang();
            for(int i = 1; i < questions.length; i++){
                if (i != index){
                    questions[i] = listSlangs.RandomSlang().getDefinition();
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
                    questions[i] = listSlangs.RandomSlang().getSlang();
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
