package application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class SampleController implements Initializable {
@FXML	
public Button Load1 = new Button();
public Button Save2 = new Button();
public Button Close = new Button();
public Button Convert = new Button();
public Label Status = new Label();
public FileChooser fileChooser = new FileChooser();
public FileChooser fileChooser1 = new FileChooser();
public String FileLocation1;
public TextArea FileContents1 = new TextArea();
public String FileLocation2;
public TextArea FileContents2 = new TextArea();
public String fileName = "";
public String fileLocation = "";
static boolean pack = false;
String address = "";
static boolean javaToCConvert = true;
static boolean cToJavaConvert = false;
static boolean hasScan = false;
public void initialize(URL url, ResourceBundle rb) {
	
}


@FXML
final public void clickLoad1(ActionEvent event) throws IOException {
	FileContents1.setText("");
	FileContents2.setText("");
try {
address = FileLocation1;
} catch(Exception e) {
Status.setText("Incorrect Input!");
return;
}

Desktop desktop = Desktop.getDesktop();
//File file = new File(address);
FileChooser.ExtensionFilter extFilter = 
new FileChooser.ExtensionFilter("Java files (*.java)", "*.java");
fileChooser.getExtensionFilters().add(extFilter);
File file = fileChooser.showOpenDialog(null);
if(file.exists()) {
BufferedReader br = new BufferedReader(new FileReader(file));
String ln; 
while(((ln = br.readLine() )!= null)){
	FileContents1.appendText(ln + "\n");
}
FileLocation1= (file.getAbsolutePath());
Status.setText("File Loaded");
Stage stage = (Stage) (Close.getScene().getWindow());
stage.setTitle(file.getAbsolutePath());
br.close();
}else {
	Status.setText("File Not Found!");
	return;
}
}
/*
@FXML
final public void clickLoad2(ActionEvent event) throws IOException {
FileContents2.setText("");
try {
address = FileLocation2.getText();
} catch(Exception e) {
Status.setText("Incorrect Input!");
return;
}

Desktop desktop = Desktop.getDesktop();
//File file = new File(address);
File file = fileChooser.showOpenDialog(null);

if(file.exists()) {
	BufferedReader br = new BufferedReader(new FileReader(file));
	String ln; 
	while(((ln = br.readLine() )!= null)){
		FileContents1.appendText(ln + "\n");
	}
Status.setText("File Loaded");
FileLocation2.setText(file.getAbsolutePath());
br.close();

}else {
	Status.setText("File Not Found!");
	return;
}
}
*/

@FXML
final public void clickSave1()  {
	
	try (PrintWriter out = new PrintWriter(FileLocation1)) {
	    out.println(FileContents1.getText());
	    out.close();
	} catch(Exception e) {
		Status.setText("Incorrect Input");
	}
	Status.setText("text saved to " + address);
}

@FXML
final public void clickSave2(ActionEvent event)  {
	
	FileChooser.ExtensionFilter extFilter = 
            new FileChooser.ExtensionFilter("CS files (*.cs)", "*.cs");
        fileChooser1.getExtensionFilters().add(extFilter);
        fileChooser1.setInitialDirectory(new File(new File(FileLocation1).getParent()));
        fileChooser1.setInitialFileName(FileLocation2);
	File file = fileChooser1.showSaveDialog(null);
    
    if(file != null){
        SaveFile(FileContents2.getText(), file);
  
}
}
private void  SaveFile(String content, File file) {
	try  {
		FileWriter out = new FileWriter (file);
	    out.write(content);
	    out.close();
	} catch(Exception e) {
		Status.setText("Incorrect Input");
	}
	Status.setText("text saved to " + address);
	
	
    }
@FXML
final public void clickConvert(ActionEvent event) throws IOException  {
	String name = "";
	FileContents2.setText("");
	pack = false;
	address = FileLocation1;
	//clickSave1();
    //File file = new File(address);
   // BufferedReader br = new BufferedReader(new FileReader(file));
//System.out.print(br);
	String javaCode = FileContents1.getText().replaceAll("\n", System.getProperty("line.separator"));
	
	BufferedReader br = new BufferedReader(new StringReader(javaCode));
    String ln;
    if(address == null || address == " ") {
    	 name = "";
    }
    else {
    	if(address.contains(".")) {
    	 name = address.substring(0,address.indexOf('.'));
    	}
    }
    if(javaToCConvert) {
    FileContents2.setText("using System; \n");
    }
    if(cToJavaConvert) {
    	if(hasScan) {
    		FileContents2.appendText("import java.util.Scanner; \n");
    	}
    }
    while ((ln = br.readLine()) != null){
        ln = convert(ln, name);
        if(pack == true){
        	FileContents2.appendText("    "+ln);
        }
        else{
        	FileContents2.appendText(ln);
        }
        FileContents2.appendText("\n");
    }
    if(ln == null && pack == true){
    	FileContents2.appendText("}");
    }
//br.close();
FileLocation2= ""; 
String[] s = FileLocation1.toString().split("\\.", '2');
FileLocation2= FileLocation2.concat(s[0]);
if(javaToCConvert) {
	FileLocation2= FileLocation2.concat(".cs");
}
if(cToJavaConvert) {
	FileLocation2= FileLocation2.concat(".java");
}
}
static String convert(String ln, String name){
	if(javaToCConvert) {
		if(ln.contains("import java.util.LinkedList;")) {
			ln = ln.replace("import java.util.LinkedList;", "using System.Collections.Generic;");
		}
		if(ln.contains("LinkedList<Integer>")) {
			ln = ln.replace("LinkedList<Integer>", "LinkedList<int>");
		}
		if(ln.contains(".getFirst()")) {
			ln = ln.replace("getFirst()", "First.Value");
		}
		if(ln.contains(".getLast()")) {
			ln = ln.replace("getLast()", "Last.Value");
		}
		if(ln.contains(".add(")) {
			ln = ln.replace(".add(", ".AddLast(");
		}
		if(ln.contains("java.util.Scanner;")) {
			hasScan = true;
			ln = "";
		}
		if(ln.contains("Scanner sc")) {
			ln = "";
		}
    if(ln.contains("package")){
        pack = !pack;
        ln = ln.replace("package", "namespace");
        ln = ln + "{";
    }
    if(ln.contains("public static void main(String[] args)")) {
        ln = ln.replace("public static void main(String[] args)", "public static void Main(string[] args)");
    }
    if(ln.contains("System.out.println")){
        ln = ln.replace("System.out.println", "Console.WriteLine");
    }
    if(ln.contains("System.out.print")){
        ln = ln.replace("System.out.print", "Console.Write");
    }
   /* if(ln.contains("public class")){
        ln = ln.replace("public class", "class");
    }*/
    if(ln.contains(".length")){
    	if (ln.contains("[0].length")) {
        	ln = ln.replace("[0].length",".GetLength(1)");
        }
        ln = ln.replace(".length", ".GetLength(0)");
        
    }
    if(ln.contains("Math.random")) {
    	String s = ln;
    	String[] split = s.split("=",2);
    	Matcher matcher = Pattern.compile("\\d+").matcher(ln);
    	matcher.find();
    	int i = Integer.valueOf(matcher.group());
    	matcher.find();
    	int j = Integer.valueOf(matcher.group());
    	split[0] = split[0] + " = new Random().Next(" + i + ", "+ j + " );";
    	ln = split[0];
    }
    if (ln.contains("sc.nextInt()")) {
    	String[] s = ln.split("=",2);
    	ln = "Console.WriteLine(); \n " + s[0] + "= Convert.ToInt32(Console.ReadLine());";
    }
    if(ln.contains("sc.close()")) {
    	ln = "";
    }
    if(ln.contains("boolean")) {
    	ln = ln.replace("boolean", "bool");
    }
    if(ln.contains("clone")) {
    	ln = ln.replace("clone", "Clone");
    	String[] s = ln.split("=",2);
    	String[] r = s[1].split("\\)",2);
    	ln = s[0] + "= " + "(" + "int [])"+  "(" + r[0] + ")"+")"+";";
    }
    if (ln.contains("[][]")) {
    	ln = ln.replace("[][]", "[,]");
    	Matcher matcher = Pattern.compile("\\d+").matcher(ln);
    	matcher.find();
    	int i = Integer.valueOf(matcher.group());
    	matcher.find();
    	int j = Integer.valueOf(matcher.group());
    	String[] s = ln.split("=",2);
    	ln = s[0] + "= new char [" + j + "," + i + "];";
    }
    if(ln.contains("][")) {
    	ln = ln.replace("][", ",");
    }
    if(ln.contains("private")) {
    	ln = ln.replace("private", "public");
    }	
    
	}
	if(cToJavaConvert){
		
		if(ln.contains("using System;")) {
			ln = ln.replace("using System;", "");
		}
		if(ln.contains("namespace")){
	        ln = ln.replace("namespace", "package");
	        ln = ln + "{";
	    }
	    if(ln.contains("public static void Main(string[] args)")) {
	        ln = ln.replace("public static void Main(string[] args)", "public static void main(String[] args)");
	    }
	    if(ln.contains("Console.WriteLine")){
	        ln = ln.replace("Console.WriteLine", "System.out.println");
	    }
	    if(ln.contains("Console.Write")){
	        ln = ln.replace("Console.Write", "System.out.print");
	    }
	    if(ln.contains("class")){
	        ln = ln.replace("class", "public class");
	    }
	    if(ln.contains(".Length")){
	        ln = ln.replace(".Length", ".length");
	    }
	    if(ln.contains("new Random()")) {
	    	String s = ln;
	    	String[] split = s.split("=",2);
	    	Matcher matcher = Pattern.compile("\\d+").matcher(ln);
	    	matcher.find();
	    	int i = Integer.valueOf(matcher.group());
	    	matcher.find();
	    	int j = Integer.valueOf(matcher.group());
	    	split[0] = split[0] + " = (int) ((Math.random() * " + j + ") +  "+ i + " );";
	    	ln = split[0];
	}
	    if(ln.contains("Console.WriteLine")) {
	    	ln = ln.replace("Console.WriteLine", "");
	    }
	    
	    if(ln.contains("Convert.ToInt32")) {
	    	ln = ln.replace("Convert.ToInt32(Console.ReadLine(", "sc.nextInt");
	    }
	    if(ln.contains("boolean")) {
	    	ln = ln.replace("boolean", "bool");
	    }
	
	}
    return ln;
}
@FXML
final public void clickSwitch(ActionEvent event) throws IOException {
	/*FileContents1.setText("");
	FileContents2.setText("");
	String s = FileLocation1.getText();
	FileLocation1.setText(FileLocation2.getText());
	FileLocation2.setText(s);
	try {
	address = FileLocation2.getText();
	} catch(Exception e) {
	Status.setText("Incorrect Input!");
	return;
	}

	Desktop desktop = Desktop.getDesktop();
	File file = new File(address);
	if(file.exists()) {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String ln; 
		while(((ln = br.readLine() )!= null)){
			FileContents2.appendText(ln + "\n");
		}
	Status.setText("File Loaded");
	br.close();
	}
	else {
		Status.setText("File Not Found!");
		return;
	}
	try {
		address = FileLocation1.getText();
		} catch(Exception e) {
		Status.setText("Incorrect Input!");
		return;
		}

		Desktop desktop1 = Desktop.getDesktop();
		File file1 = new File(address);
		if(file.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(file1));
			String ln; 
			while(((ln = br.readLine() )!= null)){
				FileContents1.appendText(ln + "\n");
			}
		Status.setText("File Loaded");
		br.close();
		}
		else {
			Status.setText("File Not Found!");
			return;
		}
		javaToCConvert = !javaToCConvert;
		cToJavaConvert = !cToJavaConvert;
		*/
}

@FXML
final public void clickClose(ActionEvent event) { 
	FileContents1.setText("");
	FileContents2.setText("");
	FileLocation1="";
	FileLocation2= "";
	
	/*Stage stage = (Stage) (Close.getScene().getWindow());
 	stage.close();  */
}
}
