import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI2
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore2 store = new AudioContentStore2();
		
		// Create my music mylibrary
		Library2 mylibrary = new Library2();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try { 
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all audio content in store
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all audiobooks
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all podcasts
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all artists
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				// A2: Modified so that it can take in 2 store indices instead of 1 store index
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{

					// Initalize variables to keep track of the index (from content number index, and to content number index)
					int fromIndex = 0;
					int toIndex = 0; 
					
					// Prompt user to enter the from content number in the store
					System.out.print("From Store Content #: ");

					// Check if user inputted an integer (number)
					if (scanner.hasNextInt())
					{
						// If user's input is an integer, then set the fromIndex variable to the user's input
						fromIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					} 
					
					// Prompt user to enter the from content number in the store
					System.out.print("To Store Content #: ");

					// Check if user inputted an integer (number)
					if (scanner.hasNextInt())
					{
						// If user's input is an integer, then set the toIndex variable to the user's input
						toIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					
					} 
				
					// Create new AudioContent object
					AudioContent2 content = null;
				
					// Start iterating from the user inputted's fromIndex (from the content number in store)
					// Iterate until i is less than or equal to the user inputted's toIndex (to the content number in store)
					for (int i = fromIndex; i <= toIndex; i++) { 

						// set the new audiocontent object equal to the content in the store at the specified index
						// Do this by calling the getContent() method in AudioContentStore
						content = store.getContent(i); 

						// If the audiocontent object contains a content from the store (not null)
						if (content != null) { 
							try { 

								// Then try to download the content by calling the download() method in Library
								mylibrary.download(content); 

								// Print message letting user know that the audio content they requested has been added to the library
								System.out.println(content.getType() + " " + content.getTitle() + " Added to library"); 

							// Catch any AudioContentExistsException and print the exception message
							// Occurs when the audio content the user requested to download is already in the library
							} catch (AudioContentExistsException e) { 
								System.out.println(e.getMessage()); 
							}
						}
					}
										
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

					// Call the playSong method in library class, pass the user's inputted index as the argument
					mylibrary.playSong(index);
					
					
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
					
					// Call the printAudioBookTOC method from the library, with the user inputted index variable as argument
					mylibrary.printAudioBookTOC(index);
					
				
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
					
					// Call the playAudioBook method from the library, with the user inputted index and chapter variables as arguments
					mylibrary.playAudioBook(index, chapter);
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

					// Call the printPodcastEpisodes method from the library, with the user inputted podcastNum and seasonNum variables as arguments
					mylibrary.printPodcastEpisodes(podcastNum, seasonNum);
					
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

					// Call the playPodcast method from the library, with the user inputted podcastNum, seasonNum and episodeNum variables as arguments
					mylibrary.playPodcast(podcastNum, seasonNum, episodeNum); 
						
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

					// Call the playPlaylist method from the library, with the user inputted plTitle variable as argument
					mylibrary.playPlaylist(plTitle);
					
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

					// Call the playPlaylist method from the library, with the user inputted plTitle and index variables as arguments
					mylibrary.playPlaylist(plTitle, index);
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

					// Call the deleteSong method from the library, with the user inputted index variable as argument
					mylibrary.deleteSong(index);
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
					
					// Call the makePlaylist method from the library, with the user inputted plTitle variable as argument
					mylibrary.makePlaylist(plTitle);
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

					// // Call the printPlaylist method from the library, with the user inputted plTitle variable as argument
					mylibrary.printPlaylist(plTitle);

					
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

					// Call the addContentToPlaylist method from the library, with the user inputted contentType, libNum and plTitle variables as arguments
					mylibrary.addContentToPlaylist(contentType, libNum, plTitle);
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

					// Call the delContentFromPlaylist method from the library, with the user inputted libNum and plTitle variables as arguments
					mylibrary.delContentFromPlaylist(libNum, plTitle);
					
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

				} else if (action.equalsIgnoreCase("SEARCH")) { // searches for audio content that user inputs in the store

					// Initalize variable to keep track of the title of the audio content that the user wants to search
					String title = ""; 

					// Prompt user to enter the title of the audio content they want to search
					System.out.print("Title: "); 

					// Check if user inputted a string (word)
					if (scanner.hasNextLine()) { 

						// If user entered a string (word), set the title variable to the user's input
						title = scanner.nextLine(); 
					}

					// Makes sure that the user inputted title is the exact same as the title in the store (same uppercase/lowercase letter)
					// Set the boolean variable space to true
					boolean space = true; 
					
					// String variable to store the converted user title input
					String newTitle = "";  
					
					// Iterate through the user inputted title string
					for (int i = 0; i < title.length(); i++) { 

						// If the current character in the string artist is a whitespace (space)
						if (Character.isWhitespace(title.charAt(i))) { 

							// Set boolean variable space to true 
							space = true; 

							// Add a space to the newTitle string variable
							newTitle += " "; 

						// If the boolean variable space is true
						} else if (space == true) { 

							// Set the first letter of the artist string after a space to uppercase
							// Then add it to the newTitle string variable 
							newTitle += Character.toUpperCase(title.charAt(i)); 

							// Set the space boolean variable to false
							space = false; 
						
						// If the boolean variable space is false and the current character is not a space 
						} else { 

							// This means that the current character is lowercase, so add that lowercase letter to the newTitle string
							newTitle += Character.toLowerCase(title.charAt(i)); 
						}
					}
					
					// Create a new audiocontent arraylist, which stores the results of the titles after searching it up
					ArrayList <AudioContent2> results = store.titleSearch(newTitle); 

					// Create a new audiocontent object
					AudioContent2 content = null; 
					
					// Loop through the results arraylist
					for (int i = 0; i < results.size(); i++) { 

						// Get the content at the specified index from the results arraylist, which keeps all the title search results
						content = results.get(i); 

						// Print out the information about the audio content
						System.out.print("" + store.storeIndex(content) + ". "); 
						content.printInfo();
						System.out.println(); 
					}
					

				} else if (action.equalsIgnoreCase("SEARCHA")) {  // Searches for artist in store

					// Prompt user to enter the artist they want to search
					System.out.print("Artist: "); 

					// Initalize variable to keep track of the artist that the user wants to search
					String artist = ""; 

					// Check if user inputted a string (word)
					if (scanner.hasNextLine()) { 

						// If user entered a string (word), set the artist variable to the user's input
						artist = scanner.nextLine(); 
					}

					// Makes sure that the user inputted artist is the exact same as the artist in the store (same uppercase/lowercase letter)
					// Set the boolean variable space to true
					boolean space = true; 
					
					// String variable to store the converted user artist input
					String newArtist = "";  
					
					// Iterate through the user inputted artist string
					for (int i = 0; i < artist.length(); i++) { 

						// If the current character in the string artist is a whitespace (space)
						if (Character.isWhitespace(artist.charAt(i))) { 

							// Set boolean variable space to true 
							space = true; 

							// Add a space to the newArtist string variable
							newArtist += " "; 

						// If the boolean variable space is true
						} else if (space == true) { 

							// Set the first letter of the artist string after a space to uppercase
							// Then add it to the newArtist string variable 
							newArtist += Character.toUpperCase(artist.charAt(i)); 

							// Set the space boolean variable to false
							space = false; 
						
						// If the boolean variable space is false and the current character is not a space 
						} else { 

							// This means that the current character is lowercase, so add that lowercase letter to the newArtist string
							newArtist += Character.toLowerCase(artist.charAt(i)); 
						}
					}
					
					// Create a new audiocontent arraylist, which stores the results of the artists after searching it up
					ArrayList <AudioContent2> results = store.artistSearch(newArtist); 

					// Create a new audiocontent object
					AudioContent2 content = null;

					// Loop through the results arraylist
					for (int i = 0; i < results.size(); i++) { 

						// Get the content at the specified index from the results arraylist, which keeps all the artist search results
						content = results.get(i); 

						// Print out the information about the audio content
						System.out.print("" + store.storeIndex(content) + ". "); 
						content.printInfo();
						System.out.println(); 
					}
				

				} else if (action.equalsIgnoreCase("SEARCHG")) {  // Searches for genre in store

					// Prompt user to enter the genre they want to search
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: "); 

					// Initalize variable to keep track of the genre that the user wants to search
					String genre = ""; 

					// Check if user inputted a string (word)
					if (scanner.hasNext()) { 

						// If user entered a string (word), set the genre variable to the user's input
						genre = scanner.nextLine(); 
					}

					// Converts the user inputted genre to uppercase to match the same exact genre in the store text file
					String newGenre = genre.toUpperCase(); 

					// Create a new audiocontent arraylist, which stores the results of the genres after searching it up
					ArrayList <AudioContent2> results = store.genreSearch(newGenre); 

					// Create a new audiocontent object
					AudioContent2 content = null;
					
					// Loop through the results of the genre search arraylist
					for (int i = 0; i < results.size(); i++) { 

						// If the current object type in the results arraylist is an audiobook,then remove it from the arraylist
						// Did this since for some reason, the audiobooks were being added to the results arraylist when searching up POP genre 
						if (results.get(i).getType().equals(AudioBook2.TYPENAME)) { 
							results.remove(i); 
						} else { 

							// If current object type is a song, then set the audiocontent object to the current object in the results arraylist
							content = results.get(i);

							// Print out the information of the contents from the genre search
							System.out.print("" + store.storeIndex(content) + ". "); 
							content.printInfo();
							System.out.println();
						}
					}
				

				} else if (action.equalsIgnoreCase("DOWNLOADA")) { // Downloads store content based off artist that user inputs 

					// Initalize variable to keep track of artist name
					String artist = ""; 
					
					// Prompt user to enter the artist name whose content to download
					System.out.print("Artist Name: ");

					// Check if user inputted a String (word)
					if (scanner.hasNextLine())
					{
						// If user's input is a string, then set the artist variable to the user's input
						artist = scanner.nextLine();
					}

					// Makes sure that the user inputted artist is the exact same as the artist in the store (same uppercase/lowercase letter)
					// Set the boolean variable space to true
					boolean space = true; 

					// String variable to store the converted user artist input
					String newArtist = "";  

					// Iterate through the user inputted artist string
					for (int i = 0; i < artist.length(); i++) { 

						// If the current character in the string artist is a whitespace (space)
						if (Character.isWhitespace(artist.charAt(i))) { 

							// Set boolean variable space to true 
							space = true; 

							// Add a space to the newArtist string variable
							newArtist += " "; 
						
						// If the boolean variable space is true
						} else if (space == true) { 

							// Set the first letter of the artist string after a space to uppercase
							// Then add it to the newArtist string variable 
							newArtist += Character.toUpperCase(artist.charAt(i)); 

							// Set the space boolean variable to false
							space = false; 

						// If the boolean variable space is false and the current character is not a space 
						} else { 
							
							// This means that the current character is lowercase, so add that lowercase letter to the newArtist string
							newArtist += Character.toLowerCase(artist.charAt(i)); 
						}
					}

					// Create a new audiocontent arraylist, which stores the results of the artists after searching it up
					ArrayList <AudioContent2> downloadArtists = store.artistSearch(newArtist); 

					// Create a new audiocontent object
					AudioContent2 content = null;

					// Loop through the downloadArtists arraylist
					for (int i = 0; i < downloadArtists.size(); i++) { 
						
						// Try to download the content related to the artist that the user inputted
						try { 

							// Get the content at the specified index from the downloadArtists arraylist, which keeps all the artist search results
							content = downloadArtists.get(i); 

							// Download the content by calling the download() method in Library
							mylibrary.download(content); 

							// Print message telling user that content is added to library
							System.out.println(content.getType() + " " + content.getTitle() + " Added to library"); 

						// Catch any AudioContentExistsException
						// Occurs when the content is already in the library
						} catch (AudioContentExistsException e) { 
							System.out.println(e.getMessage());
						} 

					}
				
					

				} else if (action.equalsIgnoreCase("DOWNLOADG")) { // Downloads store content based off genre that user inputs 

					// Initalize variable to keep track of genre 
					String genre = ""; 
					
					
					// Prompt user to enter the genre whose content to download
					System.out.print("Genre: ");

					// Check if user inputted a String (word)
					if (scanner.hasNextLine())
					{
						// If user's input is a string, then set the genre variable to the user's input
						genre = scanner.nextLine();
					}

					// Converts the user inputted genre to uppercase to match the same exact genre in the store text file
					String newGenre = genre.toUpperCase(); 

					// Create a new audiocontent arraylist, which stores the results of the genre after searching it up
					ArrayList <AudioContent2> downloadGenres = store.genreSearch(newGenre); 

					// Create a new audiocontent object
					AudioContent2 content = null;

					// Loop through the downloadGenres arraylist
					for (int i = 0; i < downloadGenres.size(); i++) { 

						// If the current object type in the downloadGenres arraylist is an audiobook,then remove it from the arraylist
						// Did this since for some reason, the audiobooks were being added to the downloadGenres arraylist when searching up POP genre 
						if (downloadGenres.get(i).getType().equals(AudioBook2.TYPENAME)) { 
							downloadGenres.remove(i); 
						} else { 

							// Try to download the genre that the user inputted
							try { 

								// Get the content at the specified index from the downloadGenres arraylist, which keeps all the genre search results
								content = downloadGenres.get(i);

								// Download the content by calling the download() method in Library
								mylibrary.download(content); 

								// Print message telling user that content is added to library
								System.out.println(content.getType() + " " + content.getTitle() + " Added to library"); 

							// Catch any AudioContentExistsException
							// Occurs when the content is already in the library
							} catch (AudioContentExistsException e) { 
								System.out.println(e.getMessage()); 
							}	
						}
					}
					
				// BONUS --> NOT COMPLETED
				} else if (action.equalsIgnoreCase("SEARCHP")) {  // Searches all audio content that partially matches a target string
					
					String target = ""; 

					System.out.print("Enter a target string that you want to search: "); 

					if (scanner.hasNextLine()) { 

						target = scanner.nextLine(); 
					}
				}
				
			// Catch the custom exceptions made in Library class
			// Prints out the exception messages that were set in Library
			} catch (AudioContentNotFoundException e) { 
				System.out.println(e.getMessage()); 
			} catch (AudioContentExistsException e) { 
				System.out.println(e.getMessage()); 
			} catch (InvalidAudioContentException e) { 
				System.out.println(e.getMessage()); 
			} catch (PlaylistExistsException e) { 
				System.out.println(e.getMessage()); 
			} catch (PlaylistNotFoundException e) { 
				System.out.println(e.getMessage()); 

			// Catch the custom exceptions made in AudioContentStore class
			// Prints out the exception messages that were set in AudioContentStore
			} catch (SearchNotFoundException e) { 
				System.out.println(e.getMessage()); 
			} catch (ContentNotInStoreException e) { 
				System.out.println(e.getMessage());
			}
			
			System.out.print("\n>");
		}
	}
}
