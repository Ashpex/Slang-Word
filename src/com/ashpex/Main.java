package com.ashpex;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
	// write your code here
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getClassName());
        }
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new MenuFrame();
    }

/*    static void showMenu(){
        System.out.println("1. Find definition by slang word");
        System.out.println("2. Find slang word by definition");
        System.out.println("3. Show history of search");
        System.out.println("4. Add a slang word");
        System.out.println("5. Edit a slang word");
        System.out.println("6. Delete a slang word");
        System.out.println("7. Reset slang word to default");
        System.out.println("8. Random a slang word");
        System.out.println("9. Quiz: Find a definition based on a slang word");
        System.out.println("10. Quiz: Find a slang word based on a definition");
        System.out.println("11. Exit");
    }

    public void findDefinitionBySlangWord(){
        System.out.println("Enter a slang word: ");
        Scanner scanner = new Scanner(System.in);
        String slangWord = scanner.nextLine();
        System.out.println("The definition of " + slangWord + " is: " + dictionary.getDefinition(slangWord));
    }
    public void findSlangWordByDefinition(){
        System.out.println("Enter a definition: ");
        Scanner scanner = new Scanner(System.in);
        String definition = scanner.nextLine();
        System.out.println("The slang word of " + definition + " is: " + dictionary.getSlangWord(definition));
    }
    public void showHistoryOfSearch(){
        System.out.println("History of search: ");
        for (String s : historyOfSearch) {
            System.out.println(s);
        }
    }
    public void addASlangWord(){
        System.out.println("Enter a slang word: ");
        Scanner scanner = new Scanner(System.in);
        String slangWord = scanner.nextLine();
        System.out.println("Enter a definition: ");
        String definition = scanner.nextLine();
        dictionary.addASlangWord(slangWord, definition);
    }
    public void editASlangWord(){
        System.out.println("Enter a slang word: ");
        Scanner scanner = new Scanner(System.in);
        String slangWord = scanner.nextLine();
        System.out.println("Enter a new definition: ");
        String definition = scanner.nextLine();
        dictionary.editASlangWord(slangWord, definition);
    }
    public void deleteASlangWord(){
        System.out.println("Enter a slang word: ");
        Scanner scanner = new Scanner(System.in);
        String slangWord = scanner.nextLine();
        dictionary.deleteASlangWord(slangWord);
    }
    public void resetASlangWord(){
        System.out.println("Enter a slang word: ");
        Scanner scanner = new Scanner(System.in);
        String slangWord = scanner.nextLine();
        dictionary.resetASlangWord(slangWord);
    }
    public void randomASlangWord(){
        System.out.println("Random a slang word: " + dictionary.randomASlangWord());
    }
    public void quizFindADefinitionBasedOnASlangWord(){
        System.out.println("Enter a slang word: ");
        Scanner scanner = new Scanner(System.in);
        String slangWord = scanner.nextLine();
        System.out.println("The definition of " + slangWord + " is: " + dictionary.getDefinition(slangWord));
    }
    public void quizFindASlangWordBasedOnADefinition(){
        System.out.println("Enter a definition: ");
        Scanner scanner = new Scanner(System.in);
        String definition = scanner.nextLine();
        System.out.println("The slang word of " + definition + " is: " + dictionary.getSlangWord(definition));
    }*/

}
