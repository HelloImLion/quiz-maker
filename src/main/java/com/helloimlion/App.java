package com.helloimlion;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class App {

    static final String WELCOME_MSG =
    "Welcome to creation mode,you can quit anytime entering '.'";
    static final String NOT_VALID_INPUT = "Please enter a valid option";
    static final String CONFIRMATION_REQUEST = "\nCorrect ? Y/N";
    static final String MORE_QUESTION = "Do you want to add another question?";
    static final String[] TITLE_REQUEST = {
      "Please choose a title: " , "Title is: "
    };
    static final String[] QUIZ_DESCRIPTION_REQUEST = {
      "Please enter a description,leave blank if you don't want to:",
      "Description is: "
    };
    static final String[] QUIZ_TAG_REQUEST = {
      "Please enter the tags separated by '|',if you don't want to enter any tag,leave blank",
      "Tags are: "
    };
    static final String[] QUESTION_TEXT_REQUEST = {
      "Please,enter the question: " , "Question is:"
    };
    static final String[] ANSWER_TEXT_REQUEST = {
      "Insert the answers,when you are finished,leave blank." , "Answer is: "
    };

    private static void displayList() {
      for ( Quiz quiz : QuizMaker.getList() ) {
        System.out.println( quiz.getHeader() );
      }
    }

    private static List<String> parseTags(String tags) {
      if(tags.equals("")) return null;
      String[] tags_array = tags.split("[|]");
      List<String> tags_list = new ArrayList<String>(Arrays.asList(tags_array));
      return tags_list;
    }

    private static boolean confirmChoice(String preface,Scanner scanner) {
      System.out.println(preface + CONFIRMATION_REQUEST );
      String confirmation = scanner.nextLine();
      boolean confirm_value = false;
      while ( !confirmation.equals("Y") && !confirmation.equals("N") &&
          !confirmation.equals(".") ) {
        System.out.println(NOT_VALID_INPUT);
        confirmation = scanner.nextLine();
      }
      switch(confirmation) {
        case "Y" : confirm_value = true; break;
        case "N" : confirm_value = false;break;
        case "." : System.exit(0); break;
      }
      return confirm_value;
    }

    private static String insertStatement(String request,String confirmation,
        Scanner scanner,boolean is_required) {
      String string = null;
      boolean out_of_loop = false;
      while(!out_of_loop) {
        System.out.println(request);
        string = scanner.nextLine();
        while ( string.equals("") && is_required ) {
          System.out.println(NOT_VALID_INPUT);
        }
        switch(string) {
          case "" : out_of_loop = true;
                    break;
          case ".": System.exit(0);
                    break;
          default:
              out_of_loop =
                  confirmChoice(String.format(confirmation + " " + string),scanner);
        }
      }
      return string;
    }


    private static boolean createQuestion(Quiz quiz,Scanner scanner) {
      String question_name = insertStatement(TITLE_REQUEST[0],
          TITLE_REQUEST[1],scanner,true);
      String question_text = insertStatement(QUESTION_TEXT_REQUEST[0],
          QUESTION_TEXT_REQUEST[1],scanner,true);
      Question question = new Question(question_name,question_text);
      String answer_text = "";
      while ( !question.hasAnswer() || !answer_text.equals("") ) {
        answer_text = insertStatement(ANSWER_TEXT_REQUEST[0],
            ANSWER_TEXT_REQUEST[1],scanner,false);
        if ( !answer_text.equals("") ) {
          question.addAnswer(new Answer(answer_text));
        }
      }
      quiz.addQuestion(question);
      return confirmChoice(MORE_QUESTION,scanner);
    }

    private static Quiz createQuiz() {
      Scanner scanner = new Scanner(System.in);
      System.out.println(WELCOME_MSG);
      String quiz_name = insertStatement(TITLE_REQUEST[0],
          TITLE_REQUEST[1],scanner,true);
      String description = insertStatement(QUIZ_DESCRIPTION_REQUEST[0],
          QUIZ_DESCRIPTION_REQUEST[1],scanner,false);
      String tags = insertStatement(QUIZ_TAG_REQUEST[0],
          QUIZ_TAG_REQUEST[1],scanner,false);
      List<String> tags_list = parseTags(tags);
      Quiz quiz = null;
      if (tags_list == null) {
        quiz = new Quiz(quiz_name,description);
      }
      else {
        quiz = new Quiz(quiz_name,description,tags_list);
      }
      while( createQuestion(quiz,scanner) );
      return quiz;
    }
    public static void main( String[] args ) {
      //QuizMaker.addQuiz(createQuiz());
      //displayList();
    }
}
