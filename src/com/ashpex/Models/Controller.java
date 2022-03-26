package com.ashpex.Models;
import java.util.HashMap;
import java.util.Map.Entry;

public class Controller{
    final String PATH = "slang.txt";
    final String DEFAULT_PATH = "original.txt";
    final String HISTORY_PATH = "history.txt";
    private static final Entry<String, SlangWord> entry = null;
    private HashMap<String, SlangWord> slang = new HashMap<>();
    public History history = new History();
}