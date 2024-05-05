package model;

public class Song {
	private String name;
	private String artist;
	private Double playtime;
	private String fileName;

	public Song(String name, String artist, Double playtime, String fileName) {
		this.name = name;
		this.artist = artist;
		this.playtime = playtime;
		this.fileName = fileName;

	}
	/**
	 * Getters
	 * @return
	 */
	String getName() {
		return name;
	}

	String getArtist() {
		return artist;
	}

	String getFileName() {
		return fileName;
	}

	Double getPlaytime() {
		return playtime;
	}
	
}
