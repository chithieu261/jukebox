/**
 * @author Ethan Quimpo, Chi Thieu
 */
package controller_view;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.JukeboxAccount;
import model.PlayList;



public class JukeboxGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	JukeboxAccount activeAccount;
	LoginCreateAccountPane loginPane;
	BorderPane everything;

	private Button songButton1;
	private Button songButton2;
	private GridPane buttonGridPane = new GridPane();
	private PlayList playList;
	
	private ListView<String> songQueueListView;
	private ObservableList<String> obsQueue;

	
    private TableView<Song> tableView;
    private ObservableList<Song> songs;
    private ArrayList<String> playlist;
    private GridPane playListPane = new GridPane();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		LayoutGUI();
		Scene scene = new Scene(everything, 600, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	private void LayoutGUI() {
		buttonGridPane = new GridPane();
		songButton1 = new Button("Select Song 1");
		songButton2 = new Button("Select Song 2");
		buttonGridPane.setHgap(30);
		buttonGridPane.add(songButton1, 1, 0);
		buttonGridPane.add(songButton2, 2, 0);
		everything = new BorderPane();
		loginPane = new LoginCreateAccountPane();
		activeAccount = loginPane.getActiveAccount();
		
		
		playList = new PlayList();
		songs = FXCollections.observableArrayList(
                new Song("Pokemon Capture", "Pikachu", 5,"songfiles/Capture.mp3"),
                new Song("Danse Macabre", "Kevin MacLeod", 34, "songfiles/DanseMacabreViolinHook.mp3"),
                new Song("Determined Tumbao", "FreePlay Music", 20, "songfiles/DeterminedTumbao.mp3"),
                new Song("Longing in Their Hearts", "Kevin MacLeod", 5,"songfiles/LongingInTheirHearts.mp3" ),
                new Song("LopingSting", "Kevin MacLeod", 5,"songfiles/LopingSting.mp3" ),
                new Song("Swing Cheese", "FreePlay Music", 15, "songfiles/SwingCheese.mp3"),
                new Song("The Curtain Rises", "Kevin MacLeod", 28, "songfiles/TheCurtainRises.mp3"),
                new Song("UntameableFire", "Pierre Langer", 284, "songfiles/UntameableFire.mp3")
        );
        playlist = new ArrayList<>();
        tableView = new TableView<>();
        tableView.setItems(songs);
        tableView.setMinSize(300, 300);
        TableColumn<Song, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));

        TableColumn<Song, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtist()));

        TableColumn<Song, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormattedTime()));

        tableView.getColumns().addAll(titleColumn, artistColumn, timeColumn);
       
        
        songQueueListView = new ListView<>();
        songQueueListView.setPrefWidth(200); // Adjust the width as needed
        
        obsQueue = FXCollections.observableArrayList(playlist);
        
        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
        	activeAccount = loginPane.getActiveAccount();
        	if(!(activeAccount.getSongCount() >=3)) {
        	activeAccount.addSong();
            Song selectedSong = tableView.getSelectionModel().getSelectedItem();
            playlist.add(selectedSong.getTitle() + "-" + selectedSong.getArtist());
            obsQueue = FXCollections.observableArrayList(playlist);
            songQueueListView.setItems(obsQueue);
            loginPane.loginLabel.setText(activeAccount.getSongCount() + " songs played today");
            try {
				playList.queueUpNextSong(selectedSong.getFileName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        	else {
        		loginPane.loginLabel.setText("3 songs already played today");
        	}
        });
        
        Label songListLabel = new Label("Song List");
        songListLabel.setFont(new Font(20));

        Label songQueueLabel = new Label("Song Queue");
        songQueueLabel.setFont(new Font(20));
        
        playListPane.add(songQueueLabel , 0, 0);
        playListPane.add(songListLabel , 2, 0);
        playListPane.add(songQueueListView, 2, 1);
        playListPane.add(tableView, 0, 1);
        playListPane.add(playButton, 1, 1);
        everything.setTop(playListPane);
		everything.setCenter(loginPane);
	}


<<<<<<< HEAD
=======
}
class Song implements Serializable {
    private String title;
    private String artist;
    private int time;
    private String fileName;

    public Song(String title, String artist, int time, String fileName) {
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getTime() {
        return time;
    }
    public String getFileName() {
    	return fileName;
    }
    public String getFormattedTime() {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
>>>>>>> d7dfe63a9d748469b0f8250fcb28a2918fd5a317
}
class Song implements Serializable {
    private String title;
    private String artist;
    private int time;
    private String fileName;

    public Song(String title, String artist, int time, String fileName) {
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getTime() {
        return time;
    }
    public String getFileName() {
    	return fileName;
    }
    public String getFormattedTime() {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}