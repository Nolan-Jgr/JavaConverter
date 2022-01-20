package application;

import java.util.*;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.TextInputDialog;
import javafx.stage.*;

public class Controller {
	
	public void loadFile(ActionEvent event) {
		TextInputDialog loadBox = new TextInputDialog();
		loadBox.setTitle("Load File Window");
		loadBox.setHeaderText("Enter the File Name or File Path you wish to load");
		loadBox.setContentText("File Name/File Path");
		
		Optional<String> input = loadBox.showAndWait();
		if(input.isPresent()) {
			String x = input.get();
			System.out.println(x);
		}
	}
	public void saveFile(ActionEvent event) {
		TextInputDialog saveBox = new TextInputDialog();
		saveBox.setTitle("Save File Window");
		saveBox.setHeaderText("Enter the File Name to be Saved");
		saveBox.setContentText("File Name/File Path");
		
		Optional<String> input = saveBox.showAndWait();
		if(input.isPresent()) {
			String x = input.get();
			System.out.println(x);
		}
	}
}
