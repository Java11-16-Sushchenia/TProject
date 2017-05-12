package by.asushenya.total.bean.page;

import java.util.List;

import by.asushenya.total.bean.Game;

/** 
 * 
 * Transfer list with {@link Game} and number of {@link Game} at page from services to command
 *
 * @author Artyom Suschenya
 */

public class GamesPage {

	private List<Game> gamesList;
	private int numberOfPages;

	public GamesPage() {
	}

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
