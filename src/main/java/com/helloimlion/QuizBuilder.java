package com.helloimlion;
import org.jdom2.Element;
import java.util.List;

class QuizBuilder {
  private static Answer createAnswer( Element element_answer ) {
    String text = element_answer.getChild("text").getText();
    Answer answer = new Answer(text,false,1.0);
    return answer;
  }
  private static Question createQuestion( Element element_question ) {
    String title = element_question.getChild("title").getText();
    String text = element_question.getChild("text").getText();
    Question question = new Question(title,text);
    List<Element> answer_list = element_question.getChild("answers").getChildren();
    for ( Element answer : answer_list ) {
      question.addAnswer(createAnswer(answer));
    }
    return question;
  }
  public static Quiz createQuiz( Element element_quiz ) {
    String title = element_quiz.getChild("title").getText();
    String description = "";
    if( element_quiz.getChild("description") != null ) {
      description = element_quiz.getChild("description").getText();
    }
    Quiz quiz = new Quiz(title,description);
    List<Element> question_list = element_quiz.getChild("questions").getChildren();
    for ( Element question : question_list ) {
      quiz.addQuestion(createQuestion(question));
    }
    return quiz;
  }
}
