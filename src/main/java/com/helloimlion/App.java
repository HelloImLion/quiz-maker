package com.helloimlion;
import java.util.List;

public class App {
    private static void displayList(List<String[]> list) {
      for (String[] quiz : list) {
          System.out.println("Title: " + quiz[0]);
          if (quiz[1] != null) System.out.println("Description: " + quiz[1]);
          System.out.println("");
        }
    }
    public static void main( String[] args ) {
        for (Quiz q : quizMaker.getList()) {
          System.out.println(q);
        }
    }
}
