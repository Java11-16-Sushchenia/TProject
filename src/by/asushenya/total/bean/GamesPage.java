package by.asushenya.total.bean;

import java.util.List;

public class GamesPage {

	private List<Game> gamesList;
	private int numberOfPages;
	
	public GamesPage(){}
	
	public List<Game> getGamesList() {
		return gamesList;
	}
	public void setGamesList(List<Game> gamesList) {
		this.gamesList = gamesList;
	}
	public int getNumberOfGamePages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfGameRecords) {
		this.numberOfPages = numberOfGameRecords;
	}

}
