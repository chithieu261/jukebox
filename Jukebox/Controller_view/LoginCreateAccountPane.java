/**
 * @author Chi Thieu, Ethan Quimpo
 */
package controller_view;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import model.JukeboxAccount;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginCreateAccountPane extends GridPane {

	private Label label = new Label("Login or Create Account");
	private TextField usernameField = new TextField();
	private PasswordField passwordField = new PasswordField();
	private Button loginButton = new Button("Login");
	private Button createAccountButton = new Button("Create new Account");
	private Button logoutButton = new Button("Log out");
	public Label loginLabel = new Label("Login first");
	public AccountCollection accountCollection = new AccountCollection();;
	private JukeboxAccount activeAccount;

	public LoginCreateAccountPane() {
		// Set padding and spacing
		this.setPadding(new Insets(10));
		this.setHgap(10);
		this.setVgap(10);

		// Add label, username field, password field, and login button to the grid
		this.add(loginLabel, 1, 0);
		this.add(new Label("Account Name:"), 0, 1);
		this.add(usernameField, 1, 1);
		this.add(loginButton, 2, 1); 
		this.add(new Label("Password:"), 0, 2);
		this.add(passwordField, 1, 2);
		this.add(logoutButton, 2, 2);
		this.add(createAccountButton,1, 3);
		activeAccount = accountCollection.getSelected();
		// Handle login button click event
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				// Handle the login logic here, e.g., check credentials
				// You can replace this with your actual login logic
				if (accountCollection.login(username, password)) {
					activeAccount = accountCollection.getSelected();
					loginLabel.setText(activeAccount.getSongCount() + " songs played today");
				} else {
					loginLabel.setText("Invalid Credentials");
					usernameField.clear();
					passwordField.clear();
				}
			}
		});
		createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String username = usernameField.getText();
				String password = passwordField.getText();

				// add an account
				JukeboxAccount newAccount = new JukeboxAccount(username, password);
				accountCollection.createAccount(username, password);

			}
		});
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				activeAccount = null;
				loginLabel.setText("Logged out");
				usernameField.clear();
				passwordField.clear();
			}
		});

	}

	public JukeboxAccount getActiveAccount() {
		return activeAccount;
	}

	public void setLoginLabel(String edit) {
		loginLabel.setText(edit);
	}
}
