package model;

import java.util.Collections;
import java.util.List;

public class MCQ {
	
	private String question;
	private List<Answer> answers;
	private String indication;
	private List<Answer> selectedAnswers;
	private int score;
	
	public MCQ() {
	}
	
	public void init() {
		this.score = 0;
		this.selectAnswers();
	}
	
	public void selectAnswers() {
		Collections.shuffle(this.answers);
		int size = 5 <= this.answers.size() ? 5 : this.answers.size();
		this.selectedAnswers = this.answers.subList(0, size);
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public void setIndication(String indication) {
		this.indication = indication;
	}
	
	public String getIndication() {
		return this.indication;
	}
	
	public List<Answer> selectedAnswers() {
		return this.selectedAnswers;
	}
}
