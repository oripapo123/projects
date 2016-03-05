package ScoreBoard;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	private List<String> scores;
	private String [] scoresArray;
	
	
	public ScoreBoard(){
		scores = readTextFile("scoreboard/ScoreBoard.txt");
		scoresArray = new String[11];
		scores.toArray(scoresArray);
		for(int i=0; i<10; i++){
			if((scoresArray[i] == null) || (scoresArray[i] == "") || ((int)scoresArray[i].charAt(0) == 65279))
				scoresArray[i] = "Unknown$" + 10*(10-i);
		}
		writeScoresToText();
	}
	
	public boolean isHighScore(int newScore){
		boolean isHighScore = false;
		int $keyOldScore = (scoresArray[9].lastIndexOf('$'));
		if(newScore > Integer.parseInt(scoresArray[9].substring($keyOldScore+1)))
			isHighScore = true;
		return isHighScore;
	}
	
	public void addScore(String newScore){
		int $keyNewScore = (newScore.lastIndexOf('$'));
		int $keyOldScore = (scoresArray[9].lastIndexOf('$'));
		boolean foundplace = false;
		int score = Integer.parseInt(newScore.substring($keyNewScore+1)); 
		if(score > Integer.parseInt(scoresArray[9].substring($keyOldScore+1))){
			scoresArray[9] = scoresArray[8];
			for(int i=8; i>=0 && !foundplace; i--){
				$keyOldScore = (scoresArray[i].indexOf('$'));
			if(score > Integer.parseInt(scoresArray[i].substring($keyOldScore+1)))
				scoresArray[i+1] = scoresArray[i];
			else{
				scoresArray[i+1] = newScore;
				foundplace = true;
			}
			if((i == 0) && (score > Integer.parseInt(scoresArray[i].substring($keyOldScore+1))))
				scoresArray[i] = newScore;
			}	
		}
				writeScoresToText();
	}
	
	private void writeScoresToText(){
		scores = new ArrayList<String>();
		int i=0;
		while(scoresArray[i] != null){
			scores.add(scoresArray[i]);
			i++;
		}
		
		try {
			writeTextFile(scores, "scoreboard/ScoreBoard.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<String> readTextFile(String aFileName){
	    Path path = Paths.get(aFileName);
	    try {
			return Files.readAllLines(path, ENCODING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	  }
	
	private void writeTextFile(List<String> aLines, String aFileName) throws IOException {
	    Path path = Paths.get(aFileName);
	    Files.write(path, aLines, ENCODING);
	}
	
	public String toString(){
		String str = "";
		for(int i=0; i<10; i++){
			int $key = scoresArray[i].lastIndexOf('$');
			str = str + (i+1) + ". " + scoresArray[i].substring(0,$key) + " - " + scoresArray[i].substring($key+1);
			
			str = str + "\n";
		}
		return str;
	}
}
