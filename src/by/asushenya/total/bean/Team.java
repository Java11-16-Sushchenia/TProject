package by.asushenya.total.bean;

import java.io.Serializable;

/**
 * 
 * Represents team, that play the {@link Game}
 *
 */

public class Team implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;

	public Team() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
