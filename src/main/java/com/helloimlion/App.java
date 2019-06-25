package com.helloimlion;
import java.util.List;
import java.util.Scanner;
import org.jdom2.Document;
import org.jdom2.Element;

public class App {
    private static void displayList( List<Quiz> list ) {
      for ( Quiz quiz : quizMaker.getList() ) {
        System.out.println( quiz.getHeader() );
      }
    }

    private static boolean confirmChoice(String preface,Scanner scanner) {
      System.out.println(preface + "\nCorrect ? Y/N" );
      String confirmation = scanner.nextLine();
      while ( !confirmation.equals("Y") && !confirmation.equals("N") ) {
        System.out.println("Please enter a valid option");
        confirmation = scanner.nextLine();
      }
      if( confirmation.equals("Y") ) {
        return true;
      }
      else if ( confirmation.equals("N") ) {
        return false;
      }
      return false;
    }

    private static String insertStatement(String request,String confirmation,
        Scanner scanner) {
      String string = "";
      do {
        System.out.println(request);
        while ( (string = scanner.nextLine() ).equals("") ) {
          System.out.println("Please enter a valid option");
        }
        if (string.equals(".") ) return string;
      } while (!confirmChoice(String.format(confirmation + " " + string),scanner));
      return string;
    }


    private static Quiz createQuiz() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Welcome to creation mode you can quit anytime entering '.'");
      String quiz_name = insertStatement("Please enter quiz name",
      "Quiz name is:",scanner);
      System.out.println("Prova");
      String description = insertStatement("Please enter a description",
      "Description is: " ,scanner);
      Quiz quiz = new Quiz(quiz_name,description);
      System.out.println(quiz.getHeader());
      return null;
    }
    public static void main( String[] args ) {
      createQuiz();
    }
}
