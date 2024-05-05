package demoMediaPlayer;

/**
 * This code will play one song assuming that file is in folder songfiles. 
 * @author Chi Thieu                   
 */
import java.io.File;
import java.net.URI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PlayAnMP3 extends Application {

  public static void main(String[] args) {
    launch(args); 
  } 
  
  private String songFilePath;

  public PlayAnMP3(String songFilePath) {
      this.songFilePath = songFilePath;
  }

  @Override
  public void start(Stage stage) throws Exception {
      Media media = new Media(new File(songFilePath).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setOnEndOfMedia(() -> {
          mediaPlayer.stop();
          Platform.exit();
      });
      mediaPlayer.play();
  }
  private class Waiter implements Runnable {
    @Override
    public void run() {
      songsPlayed++;
      System.out.println("Song ended, play song #" + songsPlayed);
      Platform.exit();
    }
  }
}
