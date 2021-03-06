package com.ashpex.Models;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SlangHashMap {
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

    public SlangHashMap() throws FileNotFoundException {
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
                    SlangWord newWord = new SlangWord(tempArray[0], tempArray[1]);
                    slangList.put(newWord.getSlang(), newWord);
                } else {
                    SlangWord newWord = new SlangWord(temp, "-");
                    slangList.put(newWord.getSlang(), newWord);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showListSlang() {
        for (Map.Entry<String, SlangWord> entry : slangList.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getDefinition());
        }
    }

    public String[][] getListSlang() {
        String[][] result = new String[slangList.size()][2];
        int i = 0;
        for (Map.Entry<String, SlangWord> entry : slangList.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue().getDefinition();
            i++;
        }
        return result;
    }

    public String searchDefinitionBasedOnSlang(String word) {
        if (slangList.containsKey(word)) {
            String definition = slangList.get(word).getDefinition();
            this.history.Add(word + ": " + definition);
            this.history.saveHistory();
            return definition;
        } else {
            this.history.Add(word + ": not found");
            this.history.saveHistory();
            return word + ": not found";
        }
    }

    public String[][] searchDefinitionBasedOnSlang3(String word){
        int i = 0;
        String[][] result = new String[slangList.size()][3];
        if(slangList.containsKey(word)){
            String definition = slangList.get(word).getDefinition();
            if(definition.length() > 0){
                String[] definitionList = definition.split("\\| ");
                result = new String[definitionList.length][3];
                for(String s : definitionList){
                    /*System.out.println(s);*/
                    result[i][0] = String.valueOf(i+1);
                    result[i][1] = word;
                    result[i][2] = s;
                    i++;
                }
                this.history.Add(word + ": " + definition);
                this.history.saveHistory();
                return result;
            }else {
                this.history.Add(word + ": not found");
                this.history.saveHistory();
                return result;
            }
        }
        this.history.saveHistory();
        return result;
    }


    public String searchSlangBasedOnDefinition(String definition) {
        String temp = standardize(definition);
        String src = "";
        for (Map.Entry<String, SlangWord> entry : slangList.entrySet()) {
            String str = entry.getValue().getDefinition();
            if (str.contains(temp)) {
                SlangWord word = entry.getValue();
                src += word.getSlang() + ": " + word.getDefinition() + "\n";
            }
        }
        if (src.equals("")) {
            src = "No result found";
        }
        history.Add(src);
        return src;

    }

    public String[][] searchSlangBasedOnDefinition2(String definition){
        int i = 0;
        String[][] result = new String[slangList.size()][3];
        String temp = standardize(definition);
        String src = "";
        for (Map.Entry<String, SlangWord> entry : slangList.entrySet()) {
            String str = entry.getValue().getDefinition();
            if (str.contains(temp)) {
                SlangWord word = entry.getValue();
                result[i][0] = String.valueOf(i+1);
                result[i][1] = word.getSlang();
                result[i][2] = word.getDefinition();
                src = word.getSlang() + ": " + word.getDefinition();
                history.Add(src);
                i++;
            }
        }
        if (src.equals("")) {
            src = "No result found";
        }
        history.saveHistory();
        return result;
    }

    public void addSlang(String slang, String definition, int duplicate) {
        if (!slangList.containsKey(slang)) {
            SlangWord newWord = new SlangWord(slang, definition);
            slangList.put(newWord.getSlang(), newWord);
        } else {
            if (duplicate == 1) {
                slangList.remove(slang);
                SlangWord newWord = new SlangWord(slang, definition);
                slangList.put(newWord.getSlang(), newWord);
            } else {
                slangList.get(slang).addDefinition(definition);
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
                if (slangList.get(slang).getDefinition().contains(definition)) {
                    String str = standardize(slangList.get(slang).removeDefinition(definition));
                    slangList.get(slang).setDefinition(str);
                    JOptionPane.showConfirmDialog(null, "Successfully deleted definition", "Edit", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Deleted failed", "Edit", JOptionPane.DEFAULT_OPTION);
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

    public void save(){
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

    public String[][] getData() {
        String[][] data = new String[slangList.size()][3];
        String data2[][] = this.getListSlang();
        for (int i = 0; i < data2.length; i++) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = data2[i][0];
            data[i][2] = data2[i][1];
        }
        return data;
    }

    public ArrayList<String> getHistory() {
        return history.getHistory();
    }

    public void clearHistory() {
        history.clearHistory();
    }

    public boolean containsSlang(String slang) {
        return slangList.containsKey(slang);
    }
}





