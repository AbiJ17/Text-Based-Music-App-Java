import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.Content;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library
public class AudioContentStore2
{

	// Create a new audiocontent arraylist (will store the audiocontent from the store text file)
	private ArrayList<AudioContent2> contents; 

	// Create 3 maps for title, artist and genres
	private Map<String, Integer> titles; 
	private Map<String, ArrayList<Integer>> artists; 
	private Map<String, ArrayList<Integer>> genres; 

	// Constructor
	public AudioContentStore2()
	{
		// Try-Catch block used to call the method to read the store.txt file (Tries to call readFile method for store.txt)
		try { 

			// initialize the audiocontent arraylist to the method for reading the store text file
			// pass the store text file name (store.txt) as the parameter to the method
			contents = readFile("store.txt"); 

			// Initialize the 3 maps created above
			titles = new HashMap<String, Integer>(); 
			artists = new HashMap<String, ArrayList<Integer>>(); 
			genres = new HashMap<String, ArrayList<Integer>>();
				
			// Initalize 3 new string variables
			// Will be used to get the title, artist and genre for each audio content in store
			String title = "";
			String artist = ""; 
			String genre = ""; 
				 
			// Loop through the contents arraylist (which now contains all of the audiocontent in the store text file)
			for (int i = 0; i < contents.size(); i++) { 

				// Create a new audiocontent object, which will store the audiocontent at the specified index in the contents arraylist
				AudioContent2 searchContent = contents.get(i); 

				// Create 2 integer arraylists to store the artist and genre indices
				ArrayList <Integer> artistIndices = new ArrayList<Integer>(); 
				ArrayList <Integer> genreIndices = new ArrayList<Integer>();
				
				// If the audiocontent at the specified index in the contents arraylist is a song
				if (searchContent.getType().equals(Song2.TYPENAME)) { 

					// Cast the content to the Song Class
					Song2 s = (Song2) searchContent; 

					// Set the title, artist and genre variables to the title, artist and genre of the song at the specified index
					title = s.getTitle(); 
					artist = s.getArtist(); 
					genre = s.getGenre().toString(); 
					
				// If the audiocontent at the specified index in the contents arraylist is an arraylist
				} else if (searchContent.getType().equals(AudioBook2.TYPENAME)) { 
					AudioBook2 a = (AudioBook2) searchContent; 

					// Set the title and author variables to the title and author of the audiobook at the specified index
					title = a.getTitle(); 
					artist = a.getAuthor();

				}

				// Add a new entry (key-value pair) into the titles map (Title of the audio content is the key of the map, index is the value of the map)
				titles.put(title, i); 

				// Checks if artists map doesn't contain the artist key (from the audio content, songs & audiobooks)
				if (!artists.containsKey(artist)) { 
					
					// If the artists map doesn't contain the current artist key, then
					// Add current index to the artistIndices arraylist (will be the value to current artist key)
					artistIndices.add(i); 

					// Add a new entry (key-value pair) into the artists map (Artist of the audio content is the key of the map, artistIndices is the value of the map)
					artists.put(artist, artistIndices); 
				} else { 

					// If the artists map contains the current artist key, then get the current artist key and add the current index as the value of the key
					artists.get(artist).add(i); 
				}

				// Checks if genres map doesn't contain the genre key (from the audio content, only songs)
				if (!genres.containsKey(genre)) { 

					// If the genres map doesn't contain the current genre key, then
					// Add current index to the genreIndices arraylist (will be the value to current genre key)
					genreIndices.add(i); 

					// Add a new entry (key-value pair) into the genres map (Genre of the audio content is the key of the map, genreIndices is the value of the map)
					genres.put(genre, genreIndices); 

				} else { 

					// If the artists map contains the current artist key, then get the current artist key and add the current index as the value of the key
					genres.get(genre).add(i); 
				}
				
			}
		// Catch any IO Exceptions that occur while calling the readFile() method using the store.txt file as parameter
		} catch (IOException e) { 

			// Print the IOException message of the exception
			System.out.println(e.getMessage()); 

			// Exit the program
			System.exit(1);
		}
	}

	// Method that reads the store text file, which contains the audio contents, and creates audiocontent objects using the information in the text file 
	// And adds them into the contents arraylist
	// Throws an IOException if an input/output error occurs when reading the store.txt file
	private ArrayList<AudioContent2> readFile(String storeFile) throws IOException{

		// Create new AudioContent arraylist (Will be used to store audiocontent objects which are created from store text file)
		ArrayList <AudioContent2> storecontent = new ArrayList<AudioContent2>(); 

		// Try-Catch block: Try to open and read the store file
		try { 

			// Create a new file object to store the store.txt file
			File file = new File(storeFile); 

			// Create new scanner object (will be used to read the store.txt file)
			Scanner in = new Scanner(file); 

			// Initialize an empty string to read in the type of the audiocontent (first line in store.txt file)
			String type = ""; 

			// Continues reading a line in the store.txt file until there are no more lines in the file
			while (in.hasNextLine()) { 

				// Set the type variable to the next line of input read in from the file
				type = in.nextLine(); 

				// If the content type is a song
				if (type.equals("SONG")) { 

					// Set the id variable to the next line of input read in from the store.txt file
					String id = in.nextLine(); 

					// Set the title variable to the next line of input read in from the store.txt file
					String title = in.nextLine(); 

					// Set the year variable to the next line of input read in from the store.txt file
					String yearStr = in.nextLine(); 

					// Convert the string year variable to an integer varible using Integer.parseInt()
					int year = Integer.parseInt(yearStr); 

					// Set the length variable to the next line of input read in from the store.txt file
					String lenStr = in.nextLine();

					// Convert the string length variable to an integer varible using Integer.parseInt()
					int length = Integer.parseInt(lenStr); 

					// Set the artist variable to the next line of input read in from the store.txt file
					String artist = in.nextLine(); 

					// Set the composer variable to the next line of input read in from the store.txt file
					String composer = in.nextLine(); 

					// Genre is an enum in Song class
					// Reads in the next line of input from the store.txt file, converts it to an enum type using valueOf() method
					Song2.Genre genre = Song2.Genre.valueOf(in.nextLine()); 

					// Set the number of lyrics variable (which is a string in the store.txt file) to the next line of input read from the store.txt file
					String numLyricsStr = in.nextLine(); 

					// Convert the numLyrics string variable to an integer variable using Integer.parseInt() 
					int numLyrics = Integer.parseInt(numLyricsStr); 

					// Declare and Initailize a string variable, which will store the lyrics of the song
					String lyrics = ""; 

					// For loop: Loops until it is less than the value in numLyrics (Loops through the number of lyrics)
					for (int i = 0; i < numLyrics; i++) { 

						// Keep adding the the next line of input read from the store.txt file and a new line to the lyrics string
						lyrics += in.nextLine() + "\n";
					}

					// Create a new song object, set its parameters to the variables containing the information from the store.txt file
					Song2 s = new Song2(title, year, id, Song2.TYPENAME, lyrics, length, artist, composer, genre, lyrics); 

					// Add the song object into the audiocontent arraylist
					storecontent.add(s); 
					
				// If the content type is an audiobook
				} else if (type.equals("AUDIOBOOK")) { 

					// Set the id variable to the next line of input read in from the store.txt file
					String id = in.nextLine(); 

					// Set the title variable to the next line of input read in from the store.txt file
					String title = in.nextLine(); 
					
					// Set the year variable to the next line of input read in from the store.txt file
					String yearStr = in.nextLine(); 

					// Convert the string year variable to an integer varible using Integer.parseInt()
					int year = Integer.parseInt(yearStr); 

					// Set the length variable to the next line of input read in from the store.txt file
					String lenStr = in.nextLine();

					// Convert the string length variable to an integer varible using Integer.parseInt()
					int length = Integer.parseInt(lenStr); 

					// Set the author variable to the next line of input read in from the store.txt file
					String author = in.nextLine(); 

					// Set the narrator variable to the next line of input read in from the store.txt file
					String narrator = in.nextLine(); 
					
					// Set the number of chapter titles variable (which is a string in the store.txt file) to the next line of input read from the store.txt file
					String numChapTitlesStr = in.nextLine(); 

					// Convert the numChapTitles string variable to an integer variable using Integer.parseInt() 
					int numChapTitles = Integer.parseInt(numChapTitlesStr); 
					
					// Create 2 new string arraylist (will be used to store the chapter titles and the contents of each chapter of an audiobook)
					ArrayList <String> chapTitles = new ArrayList<String>(); 
					ArrayList <String> chapLines = new ArrayList<String>(); 

					// For loop: Loops until it is less than the value in numChapTitles (Loops through the number of chapters)
					for (int i = 0; i < numChapTitles; i++) { 

						// Add the next line of input read from the store.txt file into the chapTitles arraylist
						chapTitles.add(in.nextLine()); 
					}

					// For loop: Loops until it is less than the value in numChapTitles (Loops through the number of chapters)
					for (int i = 0; i < numChapTitles; i++) { 

						// Read in the next line of input from the store.txt file (which contains the number of lines of the chapter)
						// Store this in an integer variable by converting the string to an integer by using Integer.parseInt() method
						int numChapLines = Integer.parseInt(in.nextLine()); 

						// Declare and Initailize a string variable, which will store the lyrics (chapter content) of the audiobook
						String lyrics = ""; 
				
						// For loop: Loops until it is less than the value in numChapLines (Loops through the number of lines in the chapter)
						for (int j = 0; j < numChapLines; j++) { 

							// Keep adding the the next line of input read from the store.txt file and a new line to the lyrics string
							lyrics += in.nextLine() + "\n"; 
						}

						// Add the lyrics variable to the chapLines arraylist
						chapLines.add(lyrics);
					}
					
					// Create a new audiobook object, set its parameters to the variables containing the information from the store.txt file
					AudioBook2 a = new AudioBook2(title, year, id, AudioBook2.TYPENAME, title, length, author, narrator, chapTitles, chapLines); 

					// Add the audiobook object into the audiocontent arraylist
					storecontent.add(a); 
				}
			}

			// Close the scanner
			in.close();

		// Catch any FileNotFoundExceptions that occur when trying to read through the store.txt file
		} catch (FileNotFoundException e) { 

			// Print message telling user that the store.txt file cannot be found
			System.out.println("File " + storeFile + " is not found"); 
		}

		// return the audiocontent arraylist
		return storecontent; 
	}

	// This Method is from A1 (NO CHANGES HERE)
	public void listAll()
	{
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	// This Method is from A1 
	// Only change: Instead of returning null when index is not in range, a ContentNotFoundInStore exception is thrown
	public AudioContent2 getContent(int index)
	{
		if (index < 1 || index > contents.size())
		{
			throw new ContentNotInStoreException("Content does not exist in store");
		}
		return contents.get(index-1);
	}

	/*
	 * Map Search Methods
	 */

	// Method that searchs for the titles of the content, which is returned in an AudioContent arraylist
	public ArrayList <AudioContent2> titleSearch(String title) { 

		// Declare and initialize an audiocontent arraylist, which will be used to store the results of the title search
		ArrayList <AudioContent2> titleResult = new ArrayList<AudioContent2>(); 

		// Declare and initialize an integer variable, which will be used to store the index
		int index = 0; 
		
		// If the titles map contains the searched up title key
		if (titles.containsKey(title)) { 

			// Then get the value of the specified key and set the index variable to that value
			index = titles.get(title); 

		// If it doesn't contain the key (meaning the searched title is not in the store), then throw new SearchNotFoundException
		// Set exception message
		} else { 
			throw new SearchNotFoundException("No matches for " + title);  
		}

		// If the index is greater than or equal to 0 and less than the size of the contents arraylist
		if (index >= 0 && index < contents.size()) { 

			// Then get the content from the contents arraylist at that index and add it to the titleResult arraylist
			titleResult.add(contents.get(index));
		} 
		
		// Return the audiocontent arraylist with the results from the title search
		return titleResult; 

	}

	// Method that searchs for the artists of the content, which is returned in an AudioContent arraylist
	public ArrayList <AudioContent2> artistSearch(String artist) { 

		// Declare and initialize an audiocontent arraylist, which will be used to store the results of the artist search
		ArrayList <AudioContent2> artistResult = new ArrayList<AudioContent2>(); 

		// Declare and initalize an integer arraylist, which stores the values of the artist keys from the artist map
		ArrayList <Integer> artistIndices = artists.get(artist); 

		// Declare and initialize an integer variable, which will be used to store the index
		int index = 0; 
	
		// If the artistIndices arraylist is not null 
		if (artistIndices != null) { 

			// Then loop through the artistIndices arraylist
			for (int i = 0; i < artistIndices.size(); i++) { 
				index = artistIndices.get(i); 
				artistResult.add(contents.get(index)); 
			}
		// If artistIndices is null (meaning the searched artist is not in the store), then throw new SearchNotFoundException
		// Set exception message
		} else { 
			throw new SearchNotFoundException("No matches for " + artist); 
		}

		// Return the audiocontent arraylist with the results from the artist search
		return artistResult; 
		
	}

	// Method that searchs for the genres of the content, which is returned in an AudioContent arraylist
	public ArrayList <AudioContent2> genreSearch(String genre) { 

		// Declare and initialize an audiocontent arraylist, which will be used to store the results of the artist search
		ArrayList <AudioContent2> genreResult = new ArrayList<AudioContent2>(); 

		// Declare and initalize an integer arraylist, which stores the values of the genre keys from the genre map
		ArrayList <Integer> genreIndices = genres.get(genre); 

		// Declare and initialize an integer variable, which will be used to store the index
		int index = 0;

		// If the genreIndices arraylist is not null 
		if (genreIndices != null) { 

			// Then loop through the genreIndices arraylist
			for (int i = 0; i < genreIndices.size(); i++) { 
				index = genreIndices.get(i); 
				genreResult.add(contents.get(index)); 
			}
		// If genreIndices is null (meaning the searched genre is not in the store), then throw new SearchNotFoundException
		// Set exception message
		} else { 
			throw new SearchNotFoundException("No matches for " + genre); 
		}

		// Return the audiocontent arraylist with the results from the genre search
		return genreResult; 
	}

	// Method that returns the index of the content in the store
	public int storeIndex(AudioContent2 content) { 

		// Declare and initialize an index variable (Integer)
		int index = 0; 

		// Iterate through the contents arraylist
		for (int i = 0; i < contents.size(); i++) { 

			// If the title of the user's content matches the title of the content in the contents arraylist
			if (content.getTitle().equalsIgnoreCase(contents.get(i).getTitle())) { 

				// Set the index variable to the index of the content in the contents arraylist and add it by 1
				index = i + 1; 
			} 
		}

		// Return the index of the content in the store
		return index; 
	}
}

/* 
 * Custom Exception Classes
 */

// Custom exception class for SearchNotFoundException 
// Thrown when the user inputted title, artist or genre is not found in the maps
// errorMsg string variable returns the message from the exception
class SearchNotFoundException extends RuntimeException { 
	public SearchNotFoundException (String errorMsg) { 
		super(errorMsg); 
	}
}

// Custom exception class for ContentNotInStoreException 
// Thrown when the user inputted audio content is not in the store.txt file
// errorMsg string variable returns the message from the exception
class ContentNotInStoreException extends RuntimeException { 
	public ContentNotInStoreException(String errorMsg) { 
		super(errorMsg); 
	}
}
