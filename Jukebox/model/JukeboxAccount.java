//@author Ethan Quimpo
package model;

import java.time.LocalDate;

// This class name is just a suggestion. 
// The account will need to use the type LocalDate
public class JukeboxAccount {

	private LocalDate todayDate;
	public String username;
	private int songCount;
	private LocalDate currentDate;
	public String password;

	/**
	 * constructor
	 * 
	 * @param name     chosen name for account;
	 * @param password chosen password;
	 */
	public JukeboxAccount(String name, String password) {

		this.username = name;
		this.password = password;
		songCount = 0;
		todayDate = LocalDate.now();
		currentDate = todayDate;

	}

	/**
	 * if current date is today's date, add a song like normal else, set song count
	 * to 1 again after, update the current date
	 */
	public void addSong() {

		if (currentDate.compareTo(todayDate) == 0) {
			songCount++;
		} else {
			songCount = 1;
		}
		currentDate = todayDate;
	}

	/**
	 * getter for current date
	 * 
	 * @return current date
	 */
	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public int getSongCount() {
		return songCount;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @return true if account can play a game false if it can't
	 */
	public boolean canPlay() {
		if (currentDate.compareTo(todayDate) == 0) {
			if (songCount > 3) {
				return false;
			}
		}
		return true;
	}

	public void pretendItsTomorrow() {
		todayDate = LocalDate.now().plusDays(1);
	}

}
