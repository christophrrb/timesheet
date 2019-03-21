import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.sql.ResultSet;

public class TimeSheet extends Application {

	String loggedInUser;
	int loggedInUserID;
	String loggedInUserFirstName;
	String loggedInUserLastName;

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Time Sheet");		

		VBox container = new VBox();

		Label label = new Label("Time Sheet");

		Label usernameLabel = new Label("Username");
		TextField usernameTextField = new TextField();

		Label passwordLabel = new Label("Password");
		PasswordField passwordField = new PasswordField();

		Button submitButton = new Button("Submit");

		ObservableList list = container.getChildren();

		submitButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				try {
					ResultSet rs = UserDatabase.authenticateUser(usernameTextField.getText(), passwordField.getText());
					if (rs.getString("username").equals("") || rs.getString("username") == null) {
						list.add(new Label("The username and password combination was not found."));
					} else {
						loggedInUser = rs.getString("username");
						loggedInUserID = rs.getInt("id");
						loggedInUserFirstName = rs.getString("first_name");
						loggedInUserLastName = rs.getString("last_name");
						logTimeScene(primaryStage);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}));

		
		list.addAll(label, usernameLabel, usernameTextField, passwordLabel, passwordField, submitButton);
		
		primaryStage.setScene(new Scene(container, 400, 400));
		primaryStage.show();
	}

	public static void main(String[] args) {
		UserDatabase.connect();
		SpreadsheetDatabase.connect();
		launch(args);
	}

	private void logTimeScene(Stage primaryStage) {
		GridPane timeSheetGridPane = new GridPane();

		Font defaultFont = new Font("Arial", 30);

		/*HBox tableLabels = new HBox();
		tableLabels.setSpacing(10);
		Label[] tableLabelsArray = {new Label("Time In"), new Label("Time Out"), new Label("Time In"), new Label("Time Out")};
		for (Label l : tableLabelsArray)
			l.setFont(new Font("Arial", 40));
		tableLabels.getChildren().addAll(tableLabelsArray);*/

		//First Row
		HBox firstRow = new HBox();
		Label daysLabel = new Label("Days"); daysLabel.setFont(defaultFont); timeSheetGridPane.add(daysLabel, 0, 0, 1, 1);
		Label timeIn1 = new Label("Time In"); timeIn1.setFont(defaultFont); timeSheetGridPane.add(timeIn1, 1, 0, 1, 1);
		Label timeOut1 = new Label("Time Out"); timeOut1.setFont(defaultFont); timeSheetGridPane.add(timeOut1, 2, 0, 1, 1); 
		Label timeIn2 = new Label("Time In"); timeIn2.setFont(defaultFont); timeSheetGridPane.add(timeIn2, 3, 0, 1, 1);
		Label timeOut2 = new Label("Time Out"); timeOut2.setFont(defaultFont); timeSheetGridPane.add(timeOut2, 4, 0, 1, 1);

		//Second Row
		HBox secondRow = new HBox();
		Label mondayLabel = new Label("Monday"); mondayLabel.setFont(defaultFont); timeSheetGridPane.add(mondayLabel, 0, 1, 1, 1);
		TextField monTimeIn1 = new TextField(" "); monTimeIn1.setFont(defaultFont); timeSheetGridPane.add(monTimeIn1, 1, 1, 1, 1);
		TextField monTimeOut1 = new TextField(" "); monTimeOut1.setFont(defaultFont); timeSheetGridPane.add(monTimeOut1, 2, 1, 1, 1);
		TextField monTimeIn2 = new TextField(" "); monTimeIn2.setFont(defaultFont); timeSheetGridPane.add(monTimeIn2, 3, 1, 1, 1);
		TextField monTimeOut2 = new TextField(" "); monTimeOut2.setFont(defaultFont); timeSheetGridPane.add(monTimeOut2, 4, 1, 1, 1);
		TextField[] monTime = {monTimeIn1, monTimeOut1, monTimeIn2, monTimeOut2};

		//Third Row
		HBox thirdRow = new HBox();
		Label tuesdayLabel = new Label("Tuesday"); tuesdayLabel.setFont(defaultFont); timeSheetGridPane.add(tuesdayLabel, 0, 2, 1, 1);
		TextField tueTimeIn1 = new TextField(" "); tueTimeIn1.setFont(defaultFont); timeSheetGridPane.add(tueTimeIn1, 1, 2, 1, 1);
		TextField tueTimeOut1 = new TextField(" "); tueTimeOut1.setFont(defaultFont); timeSheetGridPane.add(tueTimeOut1, 2, 2, 1, 1);
		TextField tueTimeIn2 = new TextField(" "); tueTimeIn2.setFont(defaultFont); timeSheetGridPane.add(tueTimeIn2, 3, 2, 1, 1);
		TextField tueTimeOut2 = new TextField(" "); tueTimeOut2.setFont(defaultFont); timeSheetGridPane.add(tueTimeOut2, 4, 2, 1, 1);
		TextField[] tueTime = {tueTimeIn1, tueTimeOut1, tueTimeIn2, tueTimeOut2};		

		//Fourth Row
		HBox fourthRow = new HBox();
		Label wednesdayLabel = new Label("Wednesday"); wednesdayLabel.setFont(defaultFont); timeSheetGridPane.add(wednesdayLabel, 0, 3, 1, 1);
		TextField wedTimeIn1 = new TextField(" "); wedTimeIn1.setFont(defaultFont); timeSheetGridPane.add(wedTimeIn1, 1, 3, 1, 1);
		TextField wedTimeOut1 = new TextField(" "); wedTimeOut1.setFont(defaultFont); timeSheetGridPane.add(wedTimeOut1, 2, 3, 1, 1);
		TextField wedTimeIn2 = new TextField(" "); wedTimeIn2.setFont(defaultFont); timeSheetGridPane.add(wedTimeIn2, 3, 3, 1, 1);
		TextField wedTimeOut2 = new TextField(" "); wedTimeOut2.setFont(defaultFont); timeSheetGridPane.add(wedTimeOut2, 4, 3, 1, 1);
		TextField[] wedTime = {wedTimeIn1, wedTimeOut1, wedTimeIn2, wedTimeOut2};		

		//Fifth Row
		HBox fifthRow = new HBox();
		Label thursdayLabel = new Label("Thursday"); thursdayLabel.setFont(defaultFont); timeSheetGridPane.add(thursdayLabel, 0, 4, 1, 1);
		TextField thurTimeIn1 = new TextField(" "); thurTimeIn1.setFont(defaultFont); timeSheetGridPane.add(thurTimeIn1, 1, 4, 1, 1);
		TextField thurTimeOut1 = new TextField(" "); thurTimeOut1.setFont(defaultFont); timeSheetGridPane.add(thurTimeOut1, 2, 4, 1, 1);
		TextField thurTimeIn2 = new TextField(" "); thurTimeIn2.setFont(defaultFont); timeSheetGridPane.add(thurTimeIn2, 3, 4, 1, 1);
		TextField thurTimeOut2 = new TextField(" "); thurTimeOut2.setFont(defaultFont); timeSheetGridPane.add(thurTimeOut2, 4, 4, 1, 1);
		TextField[] thurTime = {thurTimeIn1, thurTimeOut1, thurTimeIn2, thurTimeOut2};

		//Sixth Row
		HBox sixthRow = new HBox();
		Label fridayLabel = new Label("Friday"); fridayLabel.setFont(defaultFont); timeSheetGridPane.add(fridayLabel, 0, 5, 1, 1);
		TextField friTimeIn1 = new TextField(" "); friTimeIn1.setFont(defaultFont); timeSheetGridPane.add(friTimeIn1, 1, 5, 1, 1);
		TextField friTimeOut1 = new TextField(" "); friTimeOut1.setFont(defaultFont); timeSheetGridPane.add(friTimeOut1, 2, 5, 1, 1);
		TextField friTimeIn2 = new TextField(" "); friTimeIn2.setFont(defaultFont); timeSheetGridPane.add(friTimeIn2, 3, 5, 1, 1);
		TextField friTimeOut2 = new TextField(" "); friTimeOut2.setFont(defaultFont); timeSheetGridPane.add(friTimeOut2, 4, 5, 1, 1);
		TextField[] friTime = {friTimeIn1, friTimeOut1, friTimeIn2, friTimeOut2};

		TextField[][] daysTime = {monTime, tueTime, wedTime, thurTime, friTime};

		//Save Spreadsheet in Database Button
		Button saveInDatabase = new Button ("Save for Later"); saveInDatabase.setFont(defaultFont); timeSheetGridPane.add(saveInDatabase, 0, 6, 2, 1);
		saveInDatabase.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				try {
					SpreadsheetDatabase.storeSpreadsheet(loggedInUserID,
														loggedInUser,
														stringifyArray(monTime),
														stringifyArray(tueTime),
														new String[] {wedTimeIn1.getText(), wedTimeOut1.getText(), wedTimeIn2.getText(), wedTimeOut2.getText()},
														new String[] {thurTimeIn1.getText(), thurTimeOut1.getText(), thurTimeIn2.getText(), thurTimeOut2.getText()},
														new String[] {friTimeIn1.getText(), friTimeOut1.getText(), friTimeIn2.getText(), friTimeOut2.getText()});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}	
		});

		//Check to Restore
		try { 
			String[][] rs = SpreadsheetDatabase.restoreSpreadsheet(loggedInUserID);

			if (rs != null) {
				for (int i = 0; i < rs.length; i++) {
					for (int j = 0; j < rs[i].length; j++)
						daysTime[i][j].setText(rs[i][j]);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		

		//Export As Spreadsheet Button
		Button exportAsSpreadsheet = new Button("Export as Spreadsheet"); exportAsSpreadsheet.setFont(defaultFont); timeSheetGridPane.add(exportAsSpreadsheet, 2, 6, 2, 1);
		exportAsSpreadsheet.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				try {
					new Spreadsheet(stringifyArray(monTime),
									stringifyArray(tueTime),
									stringifyArray(wedTime),
									stringifyArray(thurTime),
									stringifyArray(friTime));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		primaryStage.setScene(new Scene(timeSheetGridPane, 2000, 800));
	}

	//Get Strings of TextFields
	private String[] stringifyArray(TextField[] arr) {
		String[] returnArr = new String[4];

		for (int i = 0; i < arr.length; i++) {
			returnArr[i] = arr[i].getText();
		}

		return returnArr;
	}
}