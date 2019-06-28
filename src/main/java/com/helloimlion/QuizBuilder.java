package com.helloimlion;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.Exception;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

abstract class QuizBuilder {
  public abstract Quiz createQuizFromDocument(String name);
  public abstract List<Quiz> getQuizFromDocument();
  public abstract void addQuizToDocument(Quiz quiz);
}

class QuizBuilderXML extends QuizBuilder {

  static final String PATH_NAME = "quiz_list.xml";

  private Document createDocument() {
    try {
    SAXBuilder builder = new SAXBuilder();
    File xml_file = new File(PATH_NAME);
    Document document = builder.build(xml_file);
    return document;
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private Element searchQuiz(String name,Document document) {
    List<Element> quiz_list = document.getRootElement().getChildren();
    String quiz_name = null;
    Element match = null;
    for(Element quiz : quiz_list) {
      quiz_name = quiz.getChild("title").getText();
      if (quiz_name.equals(name)) {
        match = quiz;
        break;
      }
    }
    return match;
  }

  private  Answer createAnswer( Element element_answer ) {
    String text = element_answer.getChild("text").getText();
    Answer answer = new Answer(text,false,1.0);
    return answer;
  }

  private  Question createQuestion( Element element_question ) {
    String title = element_question.getChild("title").getText();
    String text = element_question.getChild("text").getText();
    Question question = new Question(title,text);
    List<Element> answer_list = element_question.getChild("answers").getChildren();
    for ( Element answer : answer_list ) {
      question.addAnswer(createAnswer(answer));
    }
    return question;
  }

  private Quiz createQuiz(Element  element_quiz) {
    if (element_quiz == null) {
      return null;
    }
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
  private void addAnswerToDocument(Answer answer,Element answer_list) {
    String answer_text = answer.getText();
    Element element_answer = new Element("answer");
    element_answer.addContent(new Element("text").setText(answer_text));
    answer_list.addContent(element_answer);
  }
  private void addQuestionToDocument(Question question,Element question_list) {
    String question_title = question.getQuestionTitle();
    String question_text = question.getQuestionText();
    Element element_question = new Element("question");
    element_question.addContent(new Element("title").setText(question_title));
    element_question.addContent(new Element("text").setText(question_text));
    Element answer_list = new Element("answers");
    for (Answer a : question.getAnswerList() ) {
      addAnswerToDocument(a,answer_list);
    }
    element_question.addContent(answer_list);
    question_list.addContent(element_question);
  }

  @Override
  public Quiz createQuizFromDocument( String name ) {
    Document document = createDocument();
    Quiz quiz = createQuiz(searchQuiz(name,document));
    return quiz;
  }

  @Override
  public List<Quiz> getQuizFromDocument() {
    Document document = createDocument();
    List<Quiz> quiz_list = new ArrayList<Quiz>();
    List<Element> element_quiz_list = document.getRootElement().getChildren();
    for (Element quiz : element_quiz_list) quiz_list.add(createQuiz(quiz));
    return quiz_list;
  }
  @Override
  public void addQuizToDocument(Quiz quiz) {
    Document document = createDocument();
    Element root = document.getRootElement();
    Element element_quiz = new Element("quiz");
    String quiz_title = quiz.getTitle();
    String quiz_description = quiz.getDescription();
    element_quiz.addContent(new Element("title").setText(quiz_title));
    if (quiz_description != null) {
      element_quiz.addContent(new Element("description").setText(quiz_description));
    }
    Element question_list = new Element("questions");
    for (Question q : quiz.getQuestionList()) {
      addQuestionToDocument(q,question_list);
    }
    element_quiz.addContent(question_list);
    root.addContent(element_quiz);
    try {
      FileWriter writer = new FileWriter(PATH_NAME);
      XMLOutputter xml_output = new XMLOutputter();
      xml_output.setFormat(Format.getPrettyFormat());
      xml_output.output(document,writer);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
