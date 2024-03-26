import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				// Initalize variable to keep track of the index
				int index = 0;
				
				// Prompt user to enter the content number in the store
				System.out.print("Store Content #: ");

				// Check if user inputted an integer (number)
				if (scanner.hasNextInt())
				{
					// If user's input is an integer, then set the index variable to the user's input
					index = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}

				// Create new audiocontent object, set it to the content in the store at the specified index
				AudioContent content = store.getContent(index);

				// If the content is empty, meaning it's not in the store, return an error message
				if (content == null)
					System.out.println("Content Not Found in Store");

				// Print error message if content is already downloaded from store and is in the library
				else if (!mylibrary.download(content))
						System.out.println(mylibrary.getErrorMessage());
									
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				// Initalize variable to keep track of the index
				int index = 0; 

				// Prompt user to enter the song number in library to play
				System.out.print("Song number: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the index variable to the user's input
					index = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 
				
				// Print error message if the song doesn't exist in the library (index not in range)
				if (!mylibrary.playSong(index)) { 
					System.out.println(mylibrary.getErrorMessage()); 
				} 
				 
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				
				// Initalize variable to keep track of the index
				int index = 0; 

				// Prompt user to enter the audiobook number in library to print the table of contents of
				System.out.print("Audio Book Number: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the index variable to the user's input
					index = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine(); 
				} 
				
				// Print error message if the audiobook doesn't exist in the library (index not in range)
				if (!mylibrary.printAudioBookTOC(index)) {
					System.out.println(mylibrary.getErrorMessage());
				}
			
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				// Initalize variables to keep track of the index and the chapter of the book that the user wants to play
				int index = 0; 
				int chapter = 0; 

				// Prompt user to enter the audiobook number in library to play
				System.out.print("Audiobook number: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the index variable to the user's input
					index = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 

				// Prompt user to enter the chapter of audiobook to play
				System.out.print("Chapter: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the chapter variable to the user's input
					chapter = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine(); 
				} 
				

				// Print error message if the audiobook or chapter doesn't exist in the library (index or chapter not in range)
				if (!mylibrary.playAudioBook(index, chapter)) { 
					System.out.println(mylibrary.getErrorMessage()); 
				} 
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				// Initalize variable to keep track of the podcast number and season number that the user wants to play
				int podcastNum = 0; 
				int seasonNum = 0; 

				// Prompt user to enter the podcast number in library to print the table of contents of
				System.out.print("Podcast number: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the podcastNum variable to the user's input
					podcastNum = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 

				// Prompt user to enter the season of the podcast to print the table of contents of
				System.out.print("Season: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the seasonNum variable to the user's input
					seasonNum = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine(); 
				} 

				// Print error message if the podcast or season doesn't exist in the library (podcastNum or seasonNum not in range)
				if (!mylibrary.printPodcastEpisodes(podcastNum, seasonNum)) { 
					System.out.println(mylibrary.getErrorMessage()); 
				} 
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				// Initalize variable to keep track of the podcast, season and episode numbers that the user wants to play
				int podcastNum = 0;
				int seasonNum = 0; 
				int episodeNum = 0; 

				// Prompt user to enter the podcast number in library to play
				System.out.print("Podcast number: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the podcastNum variable to the user's input
					podcastNum = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 

				// Prompt user to enter the season of the podcast to play
				System.out.print("Season: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the seasonNum variable to the user's input
					seasonNum = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine(); 
				} 

				// Prompt user to enter the episode in the season of the podcast to play
				System.out.print("Episode: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the episodeNum variable to the user's input
					episodeNum = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine(); 
				} 

				// Print error message if the podcast, season or episode doesn't exist in the library (podcastNum, seasonNum or episodeNum not in range)
				if (!mylibrary.playPodcast(podcastNum, seasonNum, episodeNum)) { 
					System.out.println(mylibrary.getErrorMessage()); 
				} 
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				// Initalize variable to keep track of the title of the playlist that the user wants to play
				String plTitle = ""; 

				// Prompt user to enter the playlist title of the playlist in library to print the table of contents of
				System.out.print("Playlist Title: "); 

				// Check if user has enterred a string (words)
				if(scanner.hasNext()) { 

					// If user's input is a string, then set the plTitle variable to the user's input
					plTitle = scanner.next(); 

					// Use up the newline character (important when mixing nextLine() and next() methods)
					scanner.nextLine();
				} 

				// Print error message if the playlist Title doesn't exist in the list of playlists in the library
				if(!mylibrary.playPlaylist(plTitle)) { 
					System.out.println(mylibrary.getErrorMessage());
				}
				
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				// Initalize variable to keep track of the title of the playlist that the user wants to play
				String plTitle = ""; 

				// Prompt user to enter the playlist title of the playlist to play all its contents of 
				System.out.print("Playlist Title: "); 

				// Check if user has enterred a string (words)
				if(scanner.hasNext()) { 

					// If user's input is a string, then set the plTitle variable to the user's input
					plTitle = scanner.next(); 

					// Use up the newline character (important when mixing nextLine() and next() methods)
					scanner.nextLine();
				} 

				// Initalize variable to keep track of the index
				int index = 0; 

				// Read index of specific content to play
				System.out.print("Content Number: ");

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the index variable to the user's input
					index = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 

				// Print error message if the playlist Title doesn't exist in the list of playlists and if the content doesn't exist in the playlist (index out of range in playlist) 
				if(!mylibrary.playPlaylist(plTitle, index)) { 
					System.out.println(mylibrary.getErrorMessage());
				}

				
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				// Initalize variable to keep track of the index
				int index = 0; 

				// Prompt user to enter the number of the song in the library to delete
				System.out.print("Library Song: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the episodeNum variable to the user's input
					index = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 

				if (!mylibrary.deleteSong(index)) { 
					System.out.println(mylibrary.getErrorMessage()); 
				} 	
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				// Initalize variable to keep track of the title of the playlist that the user wants to play
				String plTitle = ""; 

				// Prompt user to enter a playlist title of the playlist they want to create
				System.out.print("Playlist Title: "); 

				// Check if user has enterred a string (words)
				if(scanner.hasNext()) { 

					// If user's input is a string, then set the plTitle variable to the user's input
					plTitle = scanner.next(); 

					// Use up the newline character (important when mixing nextLine() and next() methods)
					scanner.nextLine();
				} 
				
				// Print error message if the playlist Title does exist in the list of playlists in the library
				if (!mylibrary.makePlaylist(plTitle)) { 
					System.out.println(mylibrary.getErrorMessage()); 
				} 
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				// Initalize variable to keep track of the title of the playlist that the user wants to play
				String plTitle = ""; 

				// Prompt user to enter a playlist title of the playlist they want to print the content information of 
				System.out.print("Playlist Title: "); 

				// Check if user has enterred a string (words)
				if(scanner.hasNext()) { 

					// If user's input is a string, then set the plTitle variable to the user's input
					plTitle = scanner.next(); 

					// Use up the newline character (important when mixing nextLine() and next() methods)
					scanner.nextLine();
				} 

				// Print error message if the playlist Title doesn't exist in the list of playlists in the library
				if(!mylibrary.printPlaylist(plTitle)) { 
					System.out.println(mylibrary.getErrorMessage());
				}

				
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				// Initalize variables to keep track of the title of the playlist that the user wants to add content to, 
				// The type of content the user wants to add, and the index of the content based on the content list to add
				String plTitle = ""; 
				String contentType = ""; 
				int libNum = 0; 

				// Prompt user to enter the title of the playlist to play
				System.out.print("Playlist Title: "); 

				// Check if user has enterred a string (words)
				if(scanner.hasNext()) { 

					// If user's input is a string, then set the plTitle variable to the user's input
					plTitle = scanner.next(); 

					// Use up the newline character (important when mixing nextLine() and next() methods)
					scanner.nextLine();
				} 

				// Prompt user to enter the content type to play
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: "); 

				// Check if user has enterred a string (words)
				if(scanner.hasNext()) { 

					// If user's input is a string, then set the contentType variable to the user's input
					contentType = scanner.next(); 

					// Use up the newline character (important when mixing nextLine() and next() methods)
					scanner.nextLine();
				} 

				// Prompt user to enter the number of the content in the content list to play
				System.out.print("Library Content #: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the libNum variable to the user's input
					libNum = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 

				// Print error message if the playlist Title doesn't exist in the list of playlists, if the content type doesn't match the content in the playlist, 
				// Or if the content is not in the playlist in the library
				if (!mylibrary.addContentToPlaylist(contentType, libNum, plTitle)) { 
					System.out.println(mylibrary.getErrorMessage());
				}
				
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				// Initalize variables to keep track of the title of the playlist that the user wants to delete content from, 
				// The index of the content based on the content list to delete
				String plTitle = ""; 
				int libNum = 0; 

				// Prompt user to enter the title of the playlist that they want to delete content from 
				System.out.print("Playlist Title: "); 

				// Check if user has enterred a string (words)
				if(scanner.hasNext()) { 

					// If user's input is a string, then set the plTitle variable to the user's input
					plTitle = scanner.next(); 

					// Use up the newline character (important when mixing nextLine() and next() methods)
					scanner.nextLine();
				} 

				// Prompt user to enter the content in the playlist that they want to delete from the playlist
				System.out.print("Playlist Content #: "); 

				// Check if user inputted an integer (number)
				if(scanner.hasNextInt()) { 

					// If user's input is an integer, then set the libNum variable to the user's input
					libNum = scanner.nextInt(); 

					// Use up the newline character (important when mixing nextLine() and nextInt() methods)
					scanner.nextLine();
				} 

				// Print error message if the playlist Title doesn't exist in the list of playlists and if the content doesn't exist in the playlist in the library
				if (!mylibrary.delContentFromPlaylist(libNum, plTitle)) { 
					System.out.println(mylibrary.getErrorMessage());
				}
				
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			System.out.print("\n>");
		}
	}
}
