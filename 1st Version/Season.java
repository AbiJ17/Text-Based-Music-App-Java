import java.util.ArrayList;

/*
 * A season contains the information about episodes in a season of a podcast. 
 * Consists the episode files and episode titles, which are stored in String arraylists
 * Consists the length of episodes, which is stored in an integer arraylist 
 */

public class Season {

    // Stores the files of an episode
    public ArrayList<String> episodeFiles; 

    // Stores the titles of all episodes in a season of the podcast
    public ArrayList<String> episodeTitles; 

    // Stores the lengths of all episodes in a season of the podcast
    public ArrayList<Integer> episodeLengths; 

    // Constructor - Initializes the variables(arrayLists) to be used 
    public Season()
	{
		this.episodeFiles = new ArrayList<String>();
		this.episodeTitles = new ArrayList<String>();
		this.episodeLengths = new ArrayList<Integer>();
	}

    // Constructor - Initializes the variables to be used
    public Season(ArrayList<String> episodeFiles, ArrayList<String> episodeTitles, ArrayList<Integer> episodeLengths) {
        this.episodeFiles = episodeFiles; 
        this.episodeTitles = episodeTitles; 
        this.episodeLengths = episodeLengths;  

    }

    // Getter and Setter methods for each ArrayList field
    public void setEpisodes(ArrayList<String> episodeFiles) { 
        this.episodeFiles = episodeFiles; 
    }

    public ArrayList<String>getEpisodeFiles(){
        return episodeFiles; 
    }

    public void setEpisodeTitles(ArrayList<String> episodeTitles) { 
        this.episodeTitles = episodeTitles; 
    }

    public ArrayList<String>getEpisodeTitles(){
        return episodeTitles; 
    }

    public void setEpisodeLengths(ArrayList<Integer> episodeLengths) { 
        this.episodeLengths = episodeLengths; 
    }

    public ArrayList<Integer>getEpisodeLengths(){
        return episodeLengths; 
    }

}
