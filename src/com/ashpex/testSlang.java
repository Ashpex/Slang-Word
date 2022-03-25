package com.ashpex;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class testSlang {
    public static void main(String[] args) throws FileNotFoundException {
        ListSlang slang = new ListSlang();
/*        String[][] result = slang.searchDefinitionBasedOnSlang3(" ");
        if(!checkNull(result)) {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i][0] + " " + result[i][1] + " " + result[i][2]);
            }
        }else
            System.out.println("No result found");

        String history = "2022-03-22 06:04:25 BBC: British Broadcasting Corporation\n";
        String regEx = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        List<String> splitedStringList = splitString(history, regEx, 1);
        System.out.println(splitedStringList.get(0));
        System.out.println(splitedStringList.get(1));

        String slangWord = splitedStringList.get(1).split(":")[0];
        String definition = splitedStringList.get(1).split(":")[1];
        System.out.println(slangWord);
        System.out.println(definition);*/

        slang.addSlang("test", "abcdef",0);
        System.out.println(slang.searchDefinitionBasedOnSlang("hello"));
        System.out.println(slang.searchDefinitionBasedOnSlang("test"));
        System.out.println(slang.searchDefinitionBasedOnSlang("test"));

    }
    public static boolean checkNull(String[][] result) {
        for(int i = 0; i < result.length; i++) {
            if(result[i][0] == null)
                return true;
        }
        return false;
    }

    public static List<String> splitString(String inputString, String regEx, int occurancePosition) {
        List<String> splitedStringList = new LinkedList<String>();
        // Check if inputString is not null
        if (inputString != null && inputString.length() > 0) {
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(inputString);
            int count = 0;
            int stringSplitIndex = 0;
            // Iterate while loop for finding regEx
            while (matcher.find()) {
                // increase count if found the matcher string(regEx)
                count++;
                // check if we found expected occurrence of regEx in given
                // inputString
                if (count == occurancePosition) {
                    // assign index position of occurrence regEx.
                    stringSplitIndex = matcher.end();
                    break;
                }
            }
            // add subString of input String into the list from String index
            // position 0 to according to our occurrence position
            splitedStringList.add(inputString.substring(0, stringSplitIndex));
            // add SubString from occurrence index position to end of the
            // inputString.
            splitedStringList.add(inputString.substring(stringSplitIndex));
        }

        return splitedStringList;
    }
}
