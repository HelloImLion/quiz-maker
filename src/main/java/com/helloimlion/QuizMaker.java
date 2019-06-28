package com.helloimlion;
import java.io.File;
import java.io.IOException;
import java.lang.Exception;
import java.util.List;
import java.util.ArrayList;

class QuizMaker {
  public static List<Quiz> getList() {
    QuizBuilder builder = new QuizBuilderXML();
    return builder.getQuizFromDocument();
  }
  public static Quiz getQuiz(String name) {
    QuizBuilder builder = new QuizBuilderXML();
    return builder.createQuizFromDocument(name);
  }
  public static void addQuiz(Quiz quiz) {
    QuizBuilder builder = new QuizBuilderXML();
    builder.addQuizToDocument(quiz);
  }
}
