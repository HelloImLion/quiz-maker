package com.helloimlion;
import java.io.File;
import java.io.IOException;
import java.lang.Exception;
import java.util.List;
import java.util.ArrayList;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.Document;
import org.jdom2.Element;

class quizMaker {
  private static Quiz describeQuiz(Element quiz) {
    return QuizBuilder.createQuiz(quiz);
  }
  private static Document createDocument() throws JDOMException,IOException {
    SAXBuilder builder = new SAXBuilder();
    File xml_file = new File("quiz_list.xml");
    Document document = builder.build(xml_file);
    return document;
  }
  public static List<Quiz> getList() {
      try {
        List<Quiz> quiz_list = new ArrayList<Quiz>();
        Document document = createDocument();
        List<Element> element_quiz_list = document.getRootElement().getChildren();
        for (Element quiz : element_quiz_list) quiz_list.add(describeQuiz(quiz));
        return quiz_list;
      }
      catch (Exception e) {
        e.printStackTrace();
        return null;
      }
  }
}
