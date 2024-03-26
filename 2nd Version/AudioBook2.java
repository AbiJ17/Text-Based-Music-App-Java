import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook2 extends AudioContent2
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	// Constructor - Initialize variables to be used
	public AudioBook2(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables. 
		
		// Variables from Audiocontent superclass
		super(title, year, id, type, audioFile, length); 

		// New variables that were defined in this class (Audiobook)
		this.author = author; 
		this.narrator = narrator; 
		this.chapterTitles = chapterTitles; 
		this.chapters = chapters;  
	}
	
	public String getType()
	{
		return TYPENAME;
	}

  // Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		// Get the basic information of the audiobook from the AudioContent superclass 
		// printInfo() method in Audiocontent superclass contains the basic information
		super.printInfo();

		// Print author and narrator of audiobook
		System.out.print("Author: " + author + " Narrator: "  + narrator); 
		System.out.println(); 
		
	}
	
 	 // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		// Create a new String variable to store the current chapter title from chapterTitles arraylist
		String currAF = ""; 
		currAF = chapterTitles.get(currentChapter) + "\n" + chapters.get(currentChapter); 

		// Set the audioFile variable from superclass to the String variable holding the current chapter title
		super.setAudioFile(currAF);     

		// Call play() method from Audiocontent superclasss
		super.play();
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		// Iterate through the chapterTitles arrayList
		for (int i = 0; i < chapterTitles.size(); i++) { 

			// Print the chapter titles of the audiobook one by one
			System.out.println("Chapter " + (i+1) + ". " + chapterTitles.get(i));
			System.out.println(); 
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	// Other parameter refers to other audiobook
	// Return true if information, author and narrators are equal to each other, false otherwise
	public boolean equals(Object other)
	{
		// Cast audiobook to other object
		AudioBook2 otherAb = (AudioBook2) other; 

		// If the information, author and narrators are the same as the other, return true. Else, return false
		if (super.equals(otherAb) && author.equals(otherAb.author) && narrator.equals(otherAb.narrator)) { 
			return true; 
		} else { 
			return false;
		}
	}
	
	// Getter(Accessor) and Setter(Mutator) methods of each new defined field in the class
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}

}
