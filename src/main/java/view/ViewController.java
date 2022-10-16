package view;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Answer;
import model.MCQ;
import model.Model;
import model.Module;

public class ViewController {
		
	@FXML
	public Parent rootPane;
	
	@FXML
	public BorderPane indexPane;
	
	/* module */
	
	@FXML
	public AnchorPane modulePane;
	
	@FXML
	public Button engineButton;
	
	@FXML
	public Button backButton;
	
	/* course */
	
	@FXML
	public AnchorPane coursePane;
	
	// engine 
	
	@FXML
	public AnchorPane engineCoursePane0;
	
	@FXML
	public AnchorPane engineCoursePane1;
	
	@FXML
	public AnchorPane engineCoursePane2;
	
	@FXML
	public Button previousPageButton;
	
	@FXML
	public Button nextPageButton;
	
	@FXML
	public Label pageNumberLabel;
	
	/* mqc */
	
	@FXML
	public AnchorPane mcqPane;
	
	@FXML
	public Label questionLabel;
	
	@FXML
	public VBox answersVBox;
	
	@FXML
	public Button confirmButton;
	
	@FXML
	public Button mcqHelpButton;
	
	@FXML
	public Button mcqIndicationButton;
		
	private int currentCoursePage;
	private List<AnchorPane> coursePages;
	private int currentMcq;
	private String currentModule;
	
	private Model model;
	private Controller controller;
	
	private static final int NB_MCQ_PER_MODULE = 3;
	
	public ViewController() {
		this.coursePages = new ArrayList<>();
	}
	
	public void openModule( ActionEvent event ) {
		this.modulePane.toFront();
		this.coursePane.toFront();
		this.currentCoursePage = 0;
		this.currentMcq = 0;
		if( event.getSource() == this.engineButton ) {
			this.currentModule = "engine";
			this.coursePages.add(this.engineCoursePane0);
			this.coursePages.add(this.engineCoursePane1);
			this.coursePages.add(this.engineCoursePane2);
		}
		this.buildMcqPage();
		this.coursePages.get(0).toFront();
		this.updatePageControl();
	}
	
	public void changeCoursePage( ActionEvent event ) {
		if(event.getSource() == this.previousPageButton) {
			this.openPreviousPage();
		} else {
			this.openNextPage();
		}
		this.updatePageControl();
	}
	
	private void buildMcqPage() {
		Module module = this.model.getModuleByName(this.currentModule);
		module.selectMcq();
		MCQ mcq = module.selectedMcq();
		mcq.selectAnswers();
		this.answersVBox.getChildren().clear();
		this.questionLabel.setText("Question : " + mcq.getQuestion());
		List<Answer> answers = mcq.selectedAnswers();
		for(Answer answer : answers) {
			this.answersVBox.getChildren().add(new CheckBox(answer.getContent()));
		}
	}
	
	public void confirmMcq() {
		//TODO send answers to model
		if(this.currentMcq == NB_MCQ_PER_MODULE - 1) {
			this.backToIndex();
		} else {
			this.currentMcq++;
			this.buildMcqPage();
		}
	}
	
	public void backToIndex() {
		this.indexPane.toFront();
		this.coursePages.clear();
	}
	
	private void openPreviousPage() {
		if(this.currentCoursePage > 0) {
			this.currentCoursePage--;
			this.coursePages.get(this.currentCoursePage).toFront();
		}
	}
	
	private void openNextPage() {
		this.currentCoursePage++;
		if(this.currentCoursePage < this.coursePages.size()) {
			this.coursePages.get(currentCoursePage).toFront();
		} else {
			this.openMcq();
		}
	}
	
	private void openMcq() {
		this.mcqPane.toFront();
	}
	
	private void updatePageControl() {
		this.updatePageSwitchButtons();
		this.updatePageNumberLabel();
	}
	
	private void updatePageSwitchButtons() {
		if(this.currentCoursePage == 0) {
			this.previousPageButton.setDisable(true);
		} else {
			this.previousPageButton.setDisable(false);
			if(this.currentCoursePage == this.coursePages.size() - 1) {
				this.nextPageButton.setText("MCQ");
			} else {
				this.nextPageButton.setText(">");
			}
		}
	}
	
	private void updatePageNumberLabel() {
		this.pageNumberLabel.setText("Page : " + (this.currentCoursePage+1) + "/" + this.coursePages.size());
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void showMcqHelp() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("MCQ Help");
		alert.setContentText("You have to check one or more answers. "
				+ "If you need an indication, press the \"I need help!\" button. "
				+ "located on the right. Once you are sure of your answer, press \"Confirm\"");
		alert.showAndWait();
	}
	
	public void showMcqIndication() {
		MCQ mcq = this.model.getModuleByName(this.currentModule).selectedMcq();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("MCQ Indication");
		alert.setContentText(mcq.getIndication());
		alert.showAndWait();
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
