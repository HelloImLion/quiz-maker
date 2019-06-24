package com.helloimlion;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

class Quiz {

  private String title;
  private String description;
  private List<String> tags;
  private List<Question> questions;
  private double score;

  public Quiz(String title,String description) {
    this.title = title;
    this.description = description;
    this.score = 0;
    tags = new ArrayList<String>();
    questions = new ArrayList<Question>();
  }

  public String getTitle() {
    return title;
  }
  public String getDescription() {
    return description;
  }
  public List<String>getTagList() {
    return tags;
  }
  public List<Question> getQuestionList() {
    return questions;
  }
  public double getScore() {
    return score;
  }

  public String printTags() {
    StringBuilder string = new StringBuilder();
    for (String tag : tags) string.append(tag);
    return string.toString();
  }
  public Quiz addQuestion(Question question) {
    questions.add(question);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    string.append(String.format("Title: " + title + "\n"));
    if (description != null) string.append(String.format("Description: " + description + "\n"));
    for (Question question : questions) string.append(question.toString());
    return string.toString();
  }


}

class Question {

  private String question_title;
  private String question_text;
  private List<Answer> answers_list;

  public Question(String question_title,String question_text) {
    this.question_title  = question_title;
    this.question_text = question_text;
    answers_list = new ArrayList<Answer>();
  }

  public List<Answer> getAnswerList() {
    return answers_list;
  }
  public String getQuestionTitle() {
    return question_title;
  }
  public String getQuestionText() {
    return question_text;
  }
  public Question addAnswer(Answer answer) {
    answers_list.add(answer);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    string.append(String.format(question_title+"\n"));
    string.append(String.format(question_text+"\n"));
    for (Answer answer : answers_list) {
      string.append(answer.toString());
    }
    string.append("\n");
    return string.toString();
  }
}

class Answer {

  private String text;
  private String explanation;
  private boolean correct;
  private double weight;

  public Answer(String text) {
    this.text = text;
    this.explanation = null;
    this.correct = false
    this.weight = 0.0
  }

  public Answer(String text,boolean correct,double weight) {
    this.text = text;
    this.explanation = null;
    this.correct = correct;
    this.weight = weight;
  }

  public String getText() {
    return text;
  }
  public String getExplanation() {
    return explanation;
  }
  public boolean isCorrect() {
    return correct;
  }
  public double getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return String.format("Text: " + text + "\nExplanation: " + explanation +
    "\nCorrect: " + correct + "\nWeight:" + weight+"\n");
  }
}
