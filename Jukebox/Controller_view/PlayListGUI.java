/**
 * @author Chi Thieu
 */
package controller_view;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayListGUI extends GridPane {

    private TableView<Song> tableView;
    private ObservableList<Song> songs;
    private ArrayList<Song> playlist;

    public PlayListGUI() {
        songs = FXCollections.observableArrayList(
                new Song("Pokemon Capture", "Pikachu", 5),
                new Song("Danse Macabre", "Kevin MacLeod", 34),
                new Song("Determined Tumbao", "FreePlay Music", 20),
                new Song("LopingSting", "Kevin MacLeod", 5),
                new Song("Swing Cheese", "FreePlay Music", 15),
                new Song("The Curtain Rises", "Kevin MacLeod", 28),
                new Song("UntameableFire", "Pierre Langer", 284)
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
       
        
        ListView<String> songQueueListView = new ListView<>();
        songQueueListView.setPrefWidth(200); // Adjust the width as needed
        
        
        Button addToPlaylistButton = new Button("Add to Playlist");
        addToPlaylistButton.setOnAction(event -> {
            Song selectedSong = tableView.getSelectionModel().getSelectedItem();
            if (selectedSong != null) {
                playlist.add(selectedSong);
                songQueueListView.getItems().add(selectedSong.getTitle());
            }
        });
        

        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            if (playlist.size() > 3) {
                showAlert("Playlist Limit Exceeded", "You cannot play more than 3 songs at a time.");
            } 
            else if (playlist.size() > 0) {
                for (Song song : playlist) {
                    songQueueListView.getItems().add(song.getTitle());
                }
                playlist.clear(); // Clear the playlist after adding songs to the queue
            }
        });
        
        Label songListLabel = new Label("Song List");
        songListLabel.setFont(new Font(20));

        Label songQueueLabel = new Label("Song Queue");
        songQueueLabel.setFont(new Font(20));
        
        this.add(songQueueLabel , 0, 0);
        this.add(songListLabel , 2, 0);
        this.add(songQueueListView, 2, 1);
        this.add(tableView, 0, 1);
        this.add(playButton, 1, 1);;
    }
private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Butt
        onType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(close);
        alert.showAndWait();
    }
    
    private class Song implements Serializable {
        private String title;
        private String artist;
        private int time;

        public Song(String title, String artist, int time) {
            this.title = title;
            this.artist = artist;
            this.time = time;
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

        public String getFormattedTime() {
            int minutes = time / 60;
            int seconds = time % 60;
            return String.format("%d:%02d", minutes, seconds);
        }
    }
}
