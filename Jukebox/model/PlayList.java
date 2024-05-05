//@author Ethan Quimpo
package model;

import java.io.File;
import java.net.URI;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayList {
	private LinkedBlockingQueue<String> queueList;
	private int i = 1;

	public PlayList() {
		queueList = new LinkedBlockingQueue<>();
	}

	public void queueUpNextSong(String songFile) throws InterruptedException {
	
		PlayMP3(songFile);

	}

	private void PlayMP3(String pathString) {
		String path = pathString;

		// Need a File and URI object so the path works on all OSs
		File file = new File(path);
		URI uri = file.toURI();
		// Play one mp3 and and have code run when the song ends
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Waiter());
	}

	private class Waiter implements Runnable {
		@Override
		public void run() {

			try {
				Thread.sleep(4000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}