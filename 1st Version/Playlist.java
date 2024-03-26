import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	// Constructor - Initialize variables to be used
	public Playlist(String title)
	{
		// Initialize variables that are defined in Playlist class
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	// Getter(Accessor) and Setter(Mutator) methods of each field
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		// Iterate through the contents arraylist (arraylist contains audio contents of playlist)
		for (int i = 0; i < contents.size(); i++) { 
			// Print out each audio content in the playlist in numeric order
			System.out.print("" + (i+1) + ". ");

			// Get the index of the contents arraylist and then call the printInfo() method in the audiocontent superclass
			contents.get(i).printInfo();
			System.out.println(); 
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
 
		// Iterate through the contents arraylist
		for (int i = 0; i < contents.size(); i++) { 

			// Get the index of the contents arraylist and then call the play() method in the audiocontent class
			contents.get(i).play();
			System.out.println(); 
		}
		
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		// If the audiocontent is within the correct range (greater than 0, less than the size of arraylist), 
		// then get the audiocontent from the contents arraylist and play it
		if(index >= 0 && index <= contents.size()) { 
			contents.get(index).play();
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	// Other parameter refers to other playlist
	public boolean equals(Object other)
	{
		Playlist otherPlaylist = (Playlist) other;
		// Return true if both playlist titles are equal to each other, false otherwise
		return this.title.equals(otherPlaylist.title);
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
