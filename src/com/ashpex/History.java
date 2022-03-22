package com.ashpex;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class History {
    private ArrayList<String> historyList = new ArrayList<String>();
    public History () {
        try{
            File file = new File("history.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_16));
            String line;
            while ((line = br.readLine()) != null) {
                historyList.add(line);
                if (historyList.size() > 100) {
                    historyList.remove(0);
                }
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Add(String historyEntry) {
        Date date = java.util.Calendar.getInstance().getTime();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(strDateFormat);
        String formattedDate = sdf.format(date);
        historyList.add(formattedDate + " " + historyEntry);

    }

    public String showHistory() {
        StringBuilder sb = new StringBuilder();
        for (String line : historyList) {
            sb.append(line);
        }
        return sb.toString();
    }

    public void saveHistory(){
        try{
            File file = new File("history.txt");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_16));
            for (String line : historyList) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getHistory() {
        return historyList;
    }

    public void clearHistory() {
        historyList.clear();
    }
}
