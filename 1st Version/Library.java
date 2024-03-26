import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	    podcasts = new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content)
	{
		// If content is a song type
		if (content.getType().equals(Song.TYPENAME)) { 

			// If a song already exists in songs arraylist, then set the error message and return false
			// Create a new song object by casting the song class to AudioContent content
			Song s = (Song) content; 
			// Iterate through songs arraylist
			for (int i = 0; i < songs.size(); i++) { 
				// If the song is already in the songs arraylist (song at the index from songs arraylist is equal to the song object) 
				// then set error message and return false
				if (songs.get(i) == s) { 
					errorMsg = "Song is already downloaded";
					return false;
				} else { 
					continue; 
				}
			}
			// If song is not in the songs arraylist, then add the song to the list
			songs.add(s); 
		
		// If audiobook already exists in audiobooks arraylist, then set error message and return false
		// If content is an audiobook type
		} else if (content.getType().equals(AudioBook.TYPENAME)) { 
			// Create a new audiobook object by casting the audiobook class to AudioContent content
			AudioBook a = (AudioBook) content; 
			// Iterate through Audiobooks arraylist
			for (int i = 0; i < audiobooks.size(); i++) { 
				// If the audiobook is already in the audiobooks arraylist (audiobook at the index from audiobooks arraylist is equal to the audiobook object) 
				// then set error message and return false
				if (audiobooks.get(i) == a) { 
					errorMsg = "Audiobook is already downloaded"; 
					return false; 
				} else { 
					continue; 
				}
			}
			// If audiobook is not in the audiobooks arraylist, then add the audiobook to the list
			audiobooks.add(a); 

		// If content is a podcast type
		} else if (content.getType().equals(Podcast.TYPENAME)) { 
			// Create a new podcast object by casting the podcast class to AudioContent content
			Podcast p = (Podcast) content; 
			// Iterate through the podcasts arraylist
			for (int i = 0; i < podcasts.size(); i++) { 
				// If the podcast is already in the podcasts arraylist (podcast at the index from podcasts arraylist is equal to the podcast object) 
				// then set error message and return false
				if (podcasts.get(i) == p) { 
					errorMsg = "Podcast is already downloaded"; 
					return false; 
				} else { 
					continue; 
				}
			}
			// If podcast is not in the podcasts arraylist, then add the podcast to the list, then return true
			podcasts.add(p); 
		}
		return true;
			
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		// Iterate through the songs arraylist
		for (int i = 0; i < songs.size(); i++)
		{
			// First print out the numbered indexes to store each song from the songs arraylist (index starts at 0, so add 1)
			int index = i + 1;
			System.out.print("" + index + ". ");
			
			// Print out details about each song at its index in the arraylist using the printInfo() method from song class
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		// Iterate through the audiobooks arraylist
		for (int i = 0; i < audiobooks.size(); i++)
		{
			// Print out the numbered indexes to store each audiobook from the audiobooks arraylist (index starts at 0, so add 1)
			System.out.print("" + (i+1) + ". ");	

			// Print out details about each audiobook at its index in the arraylist using the printInfo() method from audiobook class
			audiobooks.get(i).printInfo(); 
			System.out.println(); 
		}
		
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		// Iterate through the podcasts arraylist
		for (int i = 0; i < podcasts.size(); i++) { 

			// Print out the numbered indexes to store each podcast from the podcasts arraylist (index starts at 0, so add 1)
			System.out.print("" + (i+1) + ". "); 

			// Print out details about each podcast at its index in the arraylist using the printInfo() method from podcast class
			podcasts.get(i).printInfo();
			System.out.println();
		}
	}
	
  	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		// Iterate through the playlists arraylist
		for (int i = 0; i < playlists.size(); i++) { 

			// Print out the numbered indexes to store each playlist title from the playlists arraylist (index starts at 0, so add 1)
			System.out.print("" + (i+1) + ". ");

			// Get the title from each playlist in the arraylist using getTitle() (method from playlist class) and print it
			System.out.print(playlists.get(i).getTitle()); 
			System.out.println();
		}
		
		
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names

		// Create and initialize new String arraylist to store the artists
		ArrayList<String> artistList = new ArrayList<String>(); 

		// Iterate through songs arraylist
		for (int i = 0; i < songs.size(); i++) {

			// If the artist of a song in the songs arraylist is not in the artist arraylist, then add the artist of the song to the artist arraylist
			if (!artistList.contains(songs.get(i).getArtist())) { 
				artistList.add(songs.get(i).getArtist()); 
			} else { 
				continue; 
			}
			
		}
		// Iterate through artistlist arraylist
		for (int i = 0; i < artistList.size(); i++) { 

			// Print out the numbered indexes to store each artist from the artistlist arraylist (index starts at 0, so add 1)
			// Then, get each artist from artistlist arraylist and print it
			System.out.print("" + (i+1) + ". " + artistList.get(i));
			System.out.println();
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index)
	{
		// Iterate through songs arraylist
		for (int i = 0; i < songs.size(); i++) { 
					
			// If the index is out of range (less than 1 and greater than length of songs arraylist), then set error message and return false
			// Otherwise, continue looping and execution
			if (index < 1 || index > songs.size()) { 
				errorMsg = "Song Not Found"; 
				return false;
			} 
		}

		// Check if songs arraylist is less than or equal to 0
		if (songs.size() <= 0) { 
			// If it is, then set error message and return false
			errorMsg = "Song is not in library"; 
			return false; 
		}
		
		// Create a new audiocontent object to compare with content in the playlist, set it to the song in songs arraylist using the index given
		AudioContent song = songs.get(index-1); 

		// Remove the song from the library using the index
		songs.remove(index-1);   
		
		// Iterate through playlists arraylist
		for (int i = 0; i < playlists.size(); i++){
			// Iterate through a playlist's content arraylist
			for (int j = 0; j < playlists.get(i).getContent().size(); j++) { 
				// Create a new audiocontent object and set it to the current content in the playlist
				AudioContent content = playlists.get(i).getContent().get(j); 

				// If the content in the playlist is a song type
				if (content.getType().equalsIgnoreCase(Song.TYPENAME)) { 
					// If the song content in playlist matches the song in the user's library (song user wants to remove), then remove the song from the playlist as well
					if (song.equals(content)) { 
						playlists.get(i).getContent().remove(j); 
					} 
				} 
			}
			
		}        
		// Return true if it passes all the cases
		return true;

 	}
	
  //Sort songs in songs arraylist by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 

		// Create and initialize new comparator to compare the year of the song
		SongYearComparator syc = new SongYearComparator(); 

		// Sorts the songs arraylist based off year (using songyearcomparator)
		Collections.sort(songs, syc); 
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> 
	{
		// Compare two song objects in the songs arraylist
		public int compare(Song s1, Song s2) { 

			// Variable to store result of comparison (initialized to 0)
			int result = 0; 

			// Store the year of each song object as integer variables (get year of song using getYear() method from song class)
			int s1year = s1.getYear(); 
			int s2year = s2.getYear(); 

			// If the year of one song object is greater than the other, set result to +1
			if (s1year > s2year) { 
				result = 1;
			
			// If the year of one song object is less than the other, set result to -1
			} else if (s1year < s2year) { 
				result = -1; 

			// If both years are equal to each other, then return result (which is initialized to 0, so returns 0)
			} else { 
				return result; 
			}
			// Return the result variable
			return result; 
		}
		
	}

	// Sort songs in songs arraylist by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 

	 // Create and initialize new comparator to compare the length of the song
	 SongLengthComparator slc = new SongLengthComparator(); 

	 // Sorts the songs arraylist based off length (using songlengthcomparator)
	 Collections.sort(songs, slc);
	 
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		// Compare two song objects in the songs arraylist
		public int compare(Song s1, Song s2) { 

			// Variable to store result of comparison (initialized to 0)
			int result = 0; 

			// Store the length of each song object as integer variables (get length of song using getLength() method from song class)
			int s1length = s1.getLength(); 
			int s2length = s2.getLength(); 

			// If the length of one song object is greater than the other, set result to +1
			if (s1length > s2length) { 
				result = 1;
			
			// If the length of one song object is less than the other, set result to -1
			} else if (s1length < s2length) { 
				result = -1; 
			
			// If both lengths are equal to each other, then return result (which is initialized to 0, so returns 0)
			} else { 
				return result; 
			}
			// Return the result variable
			return result; 
		}
		
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
		
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code

		// Sorts the songs arraylist by name
		Collections.sort(songs);	

	}
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public boolean playSong(int index)
	{
		// Check if the index is within the range (greater than 0 and less than or equal to size of songs arraylist)
		if (index > 0 && index <= songs.size())
		{
			// If index is in the range, then get the song at the specified index and use play() from song class to play song
			songs.get(index-1).play();
			return true;
		}
		
		// If index out of range, set error message and return false
		errorMsg = "Song Not Found";
		return false;
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		// Create new podcast object, set to null (will be updated during execution)
		Podcast pd = null;

		// Create new season object, set to null (will be updated during execution)
		Season s = null; 

		// Check if index is within the range (greater than 0 and less than or equal to length of podcasts arraylist)
		if (index > 0  && index <= podcasts.size()) { 

			// If index is within the range, get the podcast at its specified index in the podcasts arraylist and set the podcast object to this podcast
			pd = podcasts.get(index-1); 

			// Select the season of the podcast by using selectSeason() method in podcast class and the season parameter from method as the season of podcast to play
			pd.selectSeason(season);
		
		// If index is out of range, set error message and return false
		} else { 
			errorMsg = "Podcast not found"; 
			return false; 
		}

		// Check if season number is within the range (greater than 0 and less than or equal to length of getSeasons() arraylist, which is from Podcast class)
		if (season > 0 && season <= pd.getSeasons().size()) { 

			// If season is within range, set the season object to the current season of the podcast
			// Get the current season from the getSeasons() arraylist from Podcast class 
			s = pd.getSeasons().get(season-1); 
		
		// If season is out of range, set error message and return false
		} else { 
			errorMsg = "Season not found"; 
			return false;
		} 

		// Check if episode number is within the range (greater than 0 and less than or equal to length of getEpisodeTitles() arraylist, which is from Season class)
		if (episode > 0 && episode <= s.getEpisodeTitles().size()) { 
			
			// If episode is within range, get the current episode files from the episode files arraylist in Season class
			s.getEpisodeFiles().get(episode-1); 

			// Select the episode of the podcast by using selectEpisode() method in podcast class and the episode parameter from method as the episode of podcast to play
			pd.selectEpisode(episode);

			// Play podcast by calling play() method from podcast class, then return true
			pd.play();
			return true; 
		}

		// If episode out of range, set error message and return false
		errorMsg = "Episode not found"; 
		return false; 
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		// Create new podcast object, set to null (will be updated during execution)
		Podcast pd = null; 

		// Check if index is within the range (greater than 0 and less than or equal to length of podcasts arraylist)
		if (index > 0  && index <= podcasts.size()) { 

			// If index is within range, then get the podcast from the podcasts arraylist at the specified index
			pd = podcasts.get(index-1); 
		
		// If index not within range, set error message and return false
		} else { 
			errorMsg = "Podcast not found"; 
			return false; 
		}

		// Check if season is within the range (greater than 0 and less than or equal to length of getSeasons() arraylist from podcast class)
		if (season > 0 && season <= pd.getSeasons().size()) { 

			// If season is within the range, then print the table of contents of the podcast season using printTOC() method of podcast class, then return true
			// Pass in the season parameter to indicate the current season of the podcast
			pd.printTOC(season-1);
			return true;
		}

		// If season is not within range, then set error message and return false
		errorMsg = "Season not found"; 
		return false; 
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter)
	{
		// Check if index is within range (greater than 0 and less than or equal to length of audiobooks arraylist)
		if (index < 1  || index > audiobooks.size()) { 

			// If index not within range, set error message and return false
			errorMsg = "Audiobook not found"; 
			return false; 
		} 
		
		// If index is within range, create new audiobook object and get the audiobook from the audiobooks arraylist at the specified index
		// Then, select the chapter of that audiobook using selectChapter() method from audiobook class and setting the chapter as the chapter parameter
		AudioBook ab = audiobooks.get(index-1);
		ab.selectChapter(chapter);

		// Check if chapter is within range (greater than 0 and less than or equal to length of getChapters() arraylist from audiobook class)
		if (chapter > 0 && chapter <= ab.getChapters().size()) { 

			// If chapter is within range, then play audiobook by calling play() method from audiobook class and return true
			ab.play();
			return true; 
		}

		// If chapter not within range, then set error message and return false
		errorMsg = "Chapter not found"; 
		return false; 
		
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index)
	{
		// Check if index is within range (greater than 0 and less than or equal to length of audiobooks arraylist)
		if (index > 0  && index <= audiobooks.size()) { 

			// If index is within range, create new audiobook object and get the audiobook from the audiobook arraylists at the specified index
			// Set the audiobook object to that audiobook and print the table of contents of the audiobook by calling printTOC() method from audiobook class
			// Then, return true
			AudioBook ab = audiobooks.get(index-1); 
			ab.printTOC();
			return true;
		} 
		// If index is not within range, set error message and return false
		errorMsg = "Audiobook not found"; 
		return false; 
		
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title)
	{ 
		// Create new playlist object, set the title of the playlist as the title parameter of method
		Playlist pl = new Playlist(title); 

		// Iterate through playlists arraylist
		for (int i = 0; i < playlists.size(); i++) {

			// If a playlist's title at the specified index is equal to another title, then set error message and return false
			if (playlists.get(i).getTitle().equals(title)) { 
				errorMsg = "Playlist " + title + " already exists"; 
				return false; 
			} 
			
		}
		// If the playlist title doesn't exist in playlists arraylist, then add it to the playlists arraylist and return true
		playlists.add(pl); 
		return true; 
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public boolean printPlaylist(String title)
	{
		// Iterate through playlists arraylist
		for (int i = 0; i < playlists.size(); i++) {  

			// If a playlist's title at the specified index is equal to another title, then get the playlist at that index
			// Then print the information of that playlist using printContents() method from playlist class, then return true
			if (playlists.get(i).getTitle().equals(title)) { 
				playlists.get(i).printContents();
				return true; 
			}
		}
		// If the title not equal to the titles of playlists in playlist arraylist, set error message and return false
		errorMsg = "Playlist not found"; 
		return false; 
		
	}
	
	
	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle)
	{
		// Iterate through playlists arraylist
		for (int i = 0; i < playlists.size(); i++) {  

			// If the playlist at the specified index in the playlists arraylist is equal to the title of another playlist, 
			// Then play all of the content in that playlist by calling playAll() method from playlist class, then return true
			if (playlists.get(i).getTitle().equals(playlistTitle)) { 
				playlists.get(i).playAll();
				return true; 
			} 
		}
		// If the title of the playlist is not in the playlists arraylist, then set error message and return false
		errorMsg = "Contents not found"; 
		return false; 
	}
	
	// Play a specific song/audiobook in a playlist
	public boolean playPlaylist(String playlistTitle, int indexInPL)       
	{
		
		// Set new playlist object, set to null (will be updated during execution)
		Playlist currpl = null; 

		// Create a boolean variable to check if playlist exists in list of playlists
		Boolean plTitle = false; 

		// Iterate through playlists arraylist
		for (int i = 0; i < playlists.size();i++) { 

			// If the playlist at the specified index in the playlists arraylist is equal to the title of another playlist, 
			// Then set playlist object to that playlist, set boolean variable to true and break out of loop 
			if (playlists.get(i).getTitle().equals(playlistTitle)) { 
				currpl = playlists.get(i); 
				plTitle = true; 
				break;
			
			// Otherwise, set boolean variable to false and continue the loop until it reaches the end of the playlist arraylist
			} else { 
				plTitle = false; 
				continue; 
			}
		}

		// If boolean variable is false, then set error message and return boolean variable (which is false)
		if (plTitle == false) { 
			errorMsg = "Playlist not found"; 
			return plTitle; 
		}

		// Check if index is within range (greater than 0 and less than or equal to length of audiobooks arraylist)
		if (indexInPL > 0 && indexInPL <= currpl.getContent().size()) { 

			// If index is within range, print the title of playlist before playing the content of playlist at the specified index
			System.out.println(playlistTitle); 

			// Play the content of the playlist at the specified index by calling play() method from playlist class, then return true
			currpl.play(indexInPL-1);
			return true;
		}
		// If index not within range, set error message and return false
		errorMsg = "Content not found"; 
		return false; 
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle)
	{
		// Set new playlist object, set to null (will be updated during execution)
		Playlist currpl = null; 

		// Create a boolean variable to check if playlist exists in list of playlists
		Boolean plTitle = false; 

		// Create a boolean variable to check if the index is within the range or not
		Boolean contentInd = false; 

		// Iterate through the playlists arraylist
		for (int i = 0; i < playlists.size();i++) { 

			// If the playlist at the specified index in the playlists arraylist is equal to the title of another playlist, 
			// Then set playlist object to that playlist, set boolean variable plTitle to true and break out of loop 
			if (playlists.get(i).getTitle().equals(playlistTitle)) { 
				currpl = playlists.get(i); 
				plTitle = true; 
				break; 

			// Otherwise, set boolean variable to false and continue the loop until it reaches the end of the playlist arraylist
			} else { 
				plTitle = false; 
				continue; 
			}
		}

		// If boolean variable plTitle is false, then set error message and return boolean variable (which is false)
		if (plTitle == false) { 
			errorMsg = "Invalid playlist"; 
			return plTitle; 
		}

		// Create new Audiocontent object, set it to null (will be updated during execution)
		AudioContent content = null;

		// If the type parameter is a song type
		if (type.equalsIgnoreCase(Song.TYPENAME)) { 

			// Check if index is within range (greater than 0 and less than or equal to length of songs arraylist)
			if (index < 1 || index > songs.size()) { 

				// If index is not within range, set error message and return false (return boolean variable contentInd, which is initialized to false)
				errorMsg = "Song not found"; 
				return contentInd;  
			} 
			// If index is within range, set the audiocontent object to the song from the songs arraylist at the specified index
			content = songs.get(index-1); 

			// Iterate through the contents of a playlist
			for (int i = 0; i < currpl.getContent().size(); i++) {

				// Create a new Audiocontent object, set that to the current content from the current playlist 
				AudioContent currContent = currpl.getContent().get(i); 

				// Check if the current content type is a song type
				if (currContent.getType().equals(Song.TYPENAME)) {

					// If the song content matches the current content in the playlist, set error message and return false
					if (content.equals(currContent)) { 
						errorMsg = "Content is already in playlist"; 
						return false; 
					}
				} 
			}   
			// If song content doesn't match any of the content in the playlist, 
			// then add the song content to the playlist by using the addContent() method from the audiocontent class
			currpl.addContent(songs.get(index-1));     
			
		// If the type parameter is a audiobook type
		} else if (type.equalsIgnoreCase(AudioBook.TYPENAME)) { 

			// Check if index is within range (greater than 0 and less than or equal to length of audiobooks arraylist)
			if (index < 1 || index > audiobooks.size()) { 

				// If index is not within range, set error message and return false (return boolean variable contentInd, which is initialized to false)
				errorMsg = "Audiobook not found"; 
				return contentInd; 
			} 
			// If index is within range, set the audiocontent object to the audiobook from the audiobooks arraylist at the specified index
			content = audiobooks.get(index-1); 

			// Iterate through the contents of a playlist
			for (int i = 0; i < currpl.getContent().size(); i++) {

				// Create a new Audiocontent object, set that to the current content from the current playlist 
				AudioContent currContent = currpl.getContent().get(i); 

				// Check if the current content type is an audiobook type
				if (currContent.getType().equals(AudioBook.TYPENAME)) {

					// If the audiobook content matches the current content in the playlist, set error message and return false
					if (content.equals(currContent)) { 
						errorMsg = "Content is already in playlist"; 
						return false; 
					}
				}  
			}
			// If audiobook content doesn't match any of the content in other playlists, 
			// then add the audiobook content to the playlist by using the addContent() method from the audiocontent class
			currpl.addContent(audiobooks.get(index-1));        
			
		// If the type parameter is a podcast type
		} else if (type.equalsIgnoreCase(Podcast.TYPENAME)) {

			// Check if index is within range (greater than 0 and less than or equal to length of podcasts arraylist)
			if (index < 1 || index > podcasts.size()) { 

				// If index is not within range, set error message and return false (return boolean variable contentInd, which is initialized to false)
				errorMsg = "Podcast not found"; 
				return contentInd; 
			} 

			// If index is within range, set the audiocontent object to the podcast from the podcasts arraylist at the specified index
			content = podcasts.get(index-1); 

			// Iterate through the contents of the current playlist
			for (int i = 0; i < currpl.getContent().size(); i++) {

				// Create a new Audiocontent object, set that to the current content from the current playlist 
				AudioContent currContent = currpl.getContent().get(i); 

				// Check if the current content type is a podcast type
				if (currContent.getType().equals(Podcast.TYPENAME)) {

					// If the podcast content matches the current content in the playlist, set error message and return false
					if (content.equals(currContent)) { 
						errorMsg = "Content is already in playlist"; 
						return false; 
					}
				}
			}
			// If podcast content doesn't match any of the content in the playlist, 
			// then add the podcast content to the playlist by using the addContent() method from the audiocontent class
			currpl.addContent(podcasts.get(index-1));        
			
		// If the type parameter none of the 3 types, then set error message and return false
		} else { 
			errorMsg = "Invalid content type"; 
			return false; 
		}

		// After the content has been added into the playlist, set contentInd to true and return it
		contentInd = true; 
		return contentInd; 
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		// Set new playlist object, set to null (will be updated during execution)
		Playlist currpl = null; 

		// Create a boolean variable to check if playlist exists in list of playlists
		Boolean plTitle = false; 

		// Create a boolean variable to check if the index is within the range or not
		Boolean contentInd = false; 

		// Iterate through the playlists arraylist
		for (int i = 0; i < playlists.size();i++) { 

			// If the playlist at the specified index in the playlists arraylist is equal to the title of another playlist, 
			// Then set playlist object to that playlist, set boolean variable plTitle to true and break out of loop 
			if (playlists.get(i).getTitle().equals(title)) { 
				currpl = playlists.get(i); 
				plTitle = true; 
				break; 
			
			// Otherwise, set boolean variable to false and continue the loop until it reaches the end of the playlist arraylist
			} else { 
				plTitle = false; 
				continue; 
			}
		}

		// If boolean variable plTitle is false, then set error message and return boolean variable (which is false)
		if (plTitle == false) { 
			errorMsg = "Invalid playlist"; 
			return plTitle; 
		}

		// Check if index is within range (greater than 0 and less than or equal to length of getContent() arraylist from playlist class)
		if (index > 0 && index <= currpl.getContent().size()) { 

			// If index is within range, remove the content at the specified index from the playlist, then set boolean variable contentInd to true and return contentInd
			currpl.getContent().remove(index-1); 
			contentInd = true; 
			return contentInd;
		}
		
		// If index is not within range, set error message and return false
		errorMsg = "Content not found"; 
		return contentInd; 

	}
	
}

