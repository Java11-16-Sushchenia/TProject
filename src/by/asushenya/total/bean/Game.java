package by.asushenya.total.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import by.asushenya.total.bean.util.GameKind;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private GameKind gameKind;
	private String firstTeam;
	private String secondTeam;
	private Timestamp date;
	private double k1;
	private double kx;
	private double k2;

	public Game() {
	}

	public GameKind getGameKind() {
		return gameKind;
	}

	public void setGameKind(GameKind gameKind) {
		this.gameKind = gameKind;
	}

	public Game(int id, String firstTeam, String secondTeam, Timestamp date) {

		this.id = id;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(String firstTeam) {
		this.firstTeam = firstTeam;
	}

	public String getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(String secondTeam) {
		this.secondTeam = secondTeam;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	};

	public double getK1() {
		return k1;
	}

	public void setK1(double k1) {
		this.k1 = k1;
	}

	public double getKx() {
		return kx;
	}

	public void setKx(double kx) {
		this.kx = kx;
	}

	public double getK2() {
		return k2;
	}

	public void setK2(double k2) {
		this.k2 = k2;
	}

}
