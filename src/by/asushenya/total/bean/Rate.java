package by.asushenya.total.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import by.asushenya.total.bean.util.RateChoice;

/**
 * 
 * Contain information about rate that user make.
 *
 */

public class Rate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private User user;
	private Game game;
	private Timestamp date;
	private double money;
	private RateChoice choice;
	private double gameCoefficient;
	private double profit;
	private byte state;

	public Rate() {
	}

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

	public RateChoice getChoice() {
		return choice;
	}

	public void setChoice(RateChoice choice) {
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

	public byte getState() {
		return state;
	}

	public void setState(byte isSuccess) {
		this.state = isSuccess;
	}
}
