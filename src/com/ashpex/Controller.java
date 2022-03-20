package com.ashpex;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Controller{
    final String PATH = "slang.txt";
    final String DEFAULT_PATH = "default.txt";
    final String HISTORY_PATH = "history.dat";
    private static final Entry<String, SlangWord> entry = null;
    private HashMap<String, SlangWord> slang = new HashMap<>();
    public History history = new History();
}