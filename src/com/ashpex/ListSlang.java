package com.ashpex;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ListSlang {
    private static final Map.Entry<String, SlangWord> entry = null;
    private HashMap<String, SlangWord> slangList = new HashMap<String, SlangWord>();
    public History history = new History();

    private static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    private String standardize(String word) {
        String result = word.toLowerCase();
        result = result.replaceAll("[^a-zA-Z0-9]", "");
        if (result.charAt(0) == '|') {
            result = removeCharAt(result, 0);
        }
        if (result.charAt(result.length() - 1) == '|') {
            result = removeCharAt(result, result.length() - 1);
        }
        return result;
    }

    public ListSlang() throws FileNotFoundException {
        // TODO Auto-generated constructor stub
        History history = new History();
        String temp = "";
        int i = 0;
        String[] tempArray = new String[2];
        try {
            File file = new File("slang.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));
            while ((temp = br.readLine()) != null) {
                if (temp.contains("`")) {
                    tempArray = temp.split("`");
                    if (slangList.containsKey(tempArray[0])) {
                        addSlang(tempArray[0], tempArray[1],0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addSlang(String slang, String definition, int duplicate) {
        if (!slangList.containsKey(slang)) {
            SlangWord newWord = new SlangWord(slang, definition);
            slangList.put(newWord.getSlang(), newWord);
        } else {
            if (duplicate == 1) {
                slangList.remove(slang);
                SlangWord newWord = new SlangWord(slang, definition);
                slangList.put(newWord.getSlang(), newWord);
            } else {
                String temp = slangList.get(slang).addDefinition(definition);
            }
        }
    }

    public void deleteSlang(String slang) {
        if (slangList.containsKey(slang)) {
            slangList.remove(slang);
        }
    }

    public void editSlang(String slang, String definition, int option) {
        switch (option) {
            case 1:
                if (slangList.get(slang).getDefinition().equals(definition)) {
                    String str = standardize(slangList.get(slang).removeDefinition(definition));
                    slangList.get(slang).setSlang(str);
                    JOptionPane.showConfirmDialog(null, "Slang word has been deleted", "Edit", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Successfully deleted definition", "Edit", JOptionPane.DEFAULT_OPTION);
                }
                break;
            case 2:
                slangList.get(slang).addDefinition(definition);
                break;
            case 3:
                addSlang(slang, definition, 1);
                break;
            default:
                break;
        }
    }


    public void Reset() {
        slangList.clear();
        String temp = "";
        int i = 0;
        String[] tempArray = new String[2];
        try {
            File file = new File("original.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));
            while ((temp = br.readLine()) != null) {
                if (temp.contains("`")) {
                    tempArray = temp.split("`");
                    if (slangList.containsKey(tempArray[0])) {
                        addSlang(tempArray[0], tempArray[1],0);
                    }
                    SlangWord newWord = new SlangWord(tempArray[0], tempArray[1]);
                    slangList.put(newWord.getSlang(), newWord);
                } else {
                    SlangWord newWord = new SlangWord(temp, "----");
                    slangList.put(newWord.getSlang(), newWord);
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Save(){
        try{
            File file = new File("slang.txt");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.ISO_8859_1));
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            String str = "";
            for(Map.Entry<String, SlangWord> entry : slangList.entrySet()){
                str = entry.getValue().getDefinition();
                str.replace("|","\\|");
                fw.write(entry.getValue().getSlang() + "`" + str + "\n");
            }
            fw.close();
            slangList.clear();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SlangWord RandomSlang(){
        Random rand = new Random();
        int random = rand.nextInt(slangList.size());
        int i = 0;
        for(Map.Entry<String, SlangWord> entry : slangList.entrySet()){
            if(i == random){
                return entry.getValue();
            }
            i++;
        }
        return null;
    }

    public void saveHistory(){
        history.saveHistory();
    }
}





