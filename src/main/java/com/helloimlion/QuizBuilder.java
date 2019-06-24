package com.helloimlion;
import org.jdom2.Element;
import java.util.List;

class QuizBuilder {
  private static Answer createAnswer(Element answer) {
    String text = question.getChild("text").getText();
    Answer answer = new Answer(text,false,1.0);
  }
  private static Question createQuestion(Element question) {
    String title = question.getChild("title").getText();
    String text = question.getChild("text").getText();
    Question q = new Question(title,text);
    List<Element> answer_list = quiz.getChild("answers").getChildren();
    for (Element answer : answer_list) {
      q.addAnswer(createAnswer(answer));
    }
    return q;
  }
  public static Quiz createQuiz(Element quiz) {
    String title = quiz.getChild("title").getText();
    String description = "";
    if(quiz.getChild("description") != null) description = quiz.getChild("description").getText();
    Quiz qu = new Quiz(title,description);
    List<Element> question_list = quiz.getChild("questions").getChildren();
    for (Element question : question_list) {
      qu.addQuestion(createQuestion(question));
    }
    return qu;
  }
}
