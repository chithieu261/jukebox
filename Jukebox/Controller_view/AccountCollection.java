package controller_view;

//@author Ethan Quimpo, Chi Thieu
import model.JukeboxAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

public class AccountCollection {
	private List<JukeboxAccount> list;
	private JukeboxAccount selected;

	public AccountCollection() {
		list = new ArrayList<>();
	}

	public void createAccount(String username, String password) {
		JukeboxAccount newAccount = new JukeboxAccount(username, password);
		list.add(newAccount);
		System.out.println("Account created successfully.");
	}

	public boolean login(String username, String password) {
		for (JukeboxAccount account : list) {
			if (account.getPassword().equals(password) && account.getUsername().equals(username)) {
				selected = account;
				return true;

			}
		}

		return false;
	}

	public JukeboxAccount getSelected() {
		return selected;
	}

	public boolean logout() {
		return false;
	}
}
