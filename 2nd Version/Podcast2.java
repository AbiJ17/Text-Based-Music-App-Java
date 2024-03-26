import java.util.ArrayList;

/*
 * A podcast is a subclass of the AudioContent superclass 
 * It has extra instance fields, which are host (author of the podcast), and seasons (contains the list of episodes for each season)
 * The seasons arraylist contains Season Objects, but the Season class is in its own separate file (Season.java)
 */


public class Podcast2 extends AudioContent2{

    public static final String TYPENAME = "PODCAST"; 

    // Stores host of podcast
    private String host; 

    // Stores the seasons in a podcast 
    private ArrayList<Season2> seasons;

    // Stores the current episode of a season in the podcast, initialized to 0 
    private int currentEpisode = 0; 

    // Stores the current season in the podcast, initialized to 0
    private int currentSeason = 0; 


    // Constructor - Initialize variables to be used 
    public Podcast2(String title, int year, String id, String type, String audioFile, int length, String host, ArrayList<Season2> seasons) { 
       
        // Variables from AudioContent superclass
        super(title, year, id, type, audioFile, length); 

        // New instance fields defined in Podcast class 
        this.host = host; 
        this.seasons = seasons; 
 
    }

    // Getter(accessor) and setter(mutator) methods 
    public void setHost(String host) { 
        this.host = host; 
    }

    public String getHost(){
        return host; 
    }

    public void setSeasons(ArrayList<Season2> seasons) { 
        this.seasons = seasons; 
    }

    public ArrayList<Season2>getSeasons(){
        return seasons; 
    }

    @Override
    public String getType() {
        
        return TYPENAME;
    }

    // Prints information about the Podcast. Using the printInfo() method in the AudioContent superclass, print the 
    // basic information about the audiocontent, and then print the host and the number of seasons in the podcast. 
    public void printInfo(){ 

        // Calls printInfo() method in AudioContent superclass
        super.printInfo();

        // Prints host of the podcast
        System.out.print("Host: " + host + "\nSeasons: ");

        // Variable stores count of number of seasons in the podcast
        int count = 0; 

        // Iterates through seasons arraylist, increments count variable when there's a season in the podcast
        for (int i = 0; i < seasons.size(); i++) { 
            count ++; 
        } 

        // Prints the number of seasons in the podcast
        System.out.print(count); 
        System.out.println(); 
    }

    // Plays the podcast by first setting a Season Object to the currentSeason variable, 
    // Then create and initialize a String variable and store the episode title of the current episode, 
    // Followed by the episode files of the current episode
    // Then call the audioFile variable from the Audiocontent superclass and set it to the string Variable holding the current episode information,
    // Then call the play() method from the Audiocontent superclass
    public void play(){

        // Create new season object, initialize it to the currentSeason from the seasons arraylist
        Season2 s = seasons.get(currentSeason); 

        // Create and initialize a string variable 
		String currAF = ""; 

        // Set the string variable to the current episode files of the podcast
		currAF = s.getEpisodeTitles().get(currentEpisode) + "\n" + s.getEpisodeFiles().get(currentEpisode);

		// Set the audioFile variable from Audiocontent superclass to the String variable holding the current episode information
		super.setAudioFile(currAF);     

		// Call play() method from Audiocontent superclasss
		super.play();
    }

    // Prints the table of contents of the podcast
    // First create a season object and set it to the season parameter of the method
    public void printTOC(int season){
        Season2 s = seasons.get(season); 
        
        // Iterate through the episodeTitles arrayList in the Season class
        for (int i = 0; i < s.getEpisodeTitles().size(); i++) { 
            
            // Print the episode titles of the season 
            System.out.println("Episode " + (i+1) + ". " + s.getEpisodeTitles().get(i)); 
            System.out.println();
        }

    }

    // Selects a specific season in the podcast to play 
	public void selectSeason(int season) { 

        // If the season parameter is within the range (greater than 0, less than or equal to the size of the seasons arraylist),
        // Then set the currentSeason variable to the season parameter minus 1
        if (season >= 1 && season <= seasons.size()) { 
            currentSeason = season-1; 
        }
    
    }

    // Selects a specific episode in the season of the podcast to play
	public void selectEpisode(int episode) { 
        // Set a Season Object to the current season in the podcast 
        // So we know which episode of which season of the podcast the user wants to watch 
        Season2 s = seasons.get(currentSeason); 
	
        // If the episode parameter is within the range (greater than 0, less than or equal to the size of the episodeTitles arraylist in Season class),
        // Then set the currentEpisode variable to the episode parameter minus 1
		if (episode >= 1 && episode <= s.getEpisodeTitles().size()) {
            currentEpisode = episode-1; 	
		}
	}
    
}

