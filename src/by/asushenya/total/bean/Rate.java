package by.asushenya.total.bean;

import java.sql.Timestamp;
public class Rate {

	private int id;
	private User user;
	private Game game;
	private Timestamp date;
	private double money;
	private String choice;
	private double gameCoefficient;
	private double profit;
	private boolean isSuccess;
	
	public Rate(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public double getGameCoefficient() {
		return gameCoefficient;
	}

	public void setGameCoefficient(double gameCoefficient) {
		this.gameCoefficient = gameCoefficient;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public boolean getisSuccess() {
		return isSuccess;
	}

	public void setisSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}	
}
