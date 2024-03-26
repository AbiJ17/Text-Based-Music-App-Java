/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable <Song>  // implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	// Constructor - Initialize variables to be used
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 

		// Variables from Audiocontent superclass
		super(title, year, id, type, audioFile, length); 

		// New varaibles defined in Song class
		this.artist = artist; 
		this.composer = composer; 
		this.genre = genre; 
		this.lyrics = lyrics; 
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		
		// Get the basic information of the audiobook from the AudioContent superclass 
		// printInfo() method in Audiocontent superclass contains the basic information
		super.printInfo();        
		
		// Print artist, composer, genre of song
		System.out.print("Artist: " + artist + " Composer: " + composer + " Genre: " + genre); 
		System.out.println(); 

	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		// Sets the audioFile (variable in superclass) to lyrics in Song class
		super.setAudioFile(lyrics);     
		
		// Calling the play() method from Audiocontent superclass
		super.play();
	}
	
	// Getter(Accessor) and setter(mutator) methods of each new variable defined in Song class
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	// Other parameter refers to the other song
	// Returns true if the information, artists and composer are equal, false otherwise 
	public boolean equals(Object other)
	{
		// Cast song to the other object
		Song othSong = (Song) other; 

		// If the information, composer and artists are the equal to another song's information, artist and  composer, then both songs are the same (return true)
		// if not, then return false
		if (super.equals(othSong) && composer.equals(othSong.composer) && artist.equals(othSong.artist)) {
			return true; 
		} else { 
			return false;
		}
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	// Other parameter refers to the other song's title
	public int compareTo(Song other) 
	{ 
		// If both strings are equal to each other, return 0
		if (this.getTitle().equals(other.getTitle())) { 
			return 0; 
		} else { 

			// Use compareToIgnoreCase() method in String class
			// If the string comes before the string argument in alphabetical order, then return -1
			// If the string comes after the string argument in alphabetical order, then return 1
			return this.getTitle().compareToIgnoreCase(other.getTitle());
		}
	}
}
