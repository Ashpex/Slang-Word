package com.ashpex;

public interface Controller <T> {
    public boolean addSlangWord(T word);
    public boolean removeSlangWord(T word);
    public boolean updateSlangWord(T oldWord, T newWord);
    public T getSlangWord(T word);
}