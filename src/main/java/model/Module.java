package model;

import java.util.List;
import java.util.Random;

public class Module {
	
	private String name;
	private List<MCQ> mcqs;
	private MCQ selectedMcq;
	private int score;
	private Random randomizer;
	
	public Module() {
	}
	
	public void init() {
		this.score = 0;
		this.randomizer = new Random();
		this.selectMcq();
		this.selectedMcq.init();
	}
	
	public void selectMcq() {
		int random = this.randomizer.nextInt(this.mcqs.size());
		this.selectedMcq = this.mcqs.get(random);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MCQ> getMcqs() {
		return mcqs;
	}

	public void setMcqs(List<MCQ> mcqs) {
		this.mcqs = mcqs;
	}
	
	public MCQ selectedMcq() {
		return this.selectedMcq;
	}
}
