package by.asushenya.total.dao.sql_query;

public class BookMakerQuery {
	public static final String UPDATE_GAME_COEFFICIENTS = "update game set k1 = ?, kx = ?, k2 = ? where game.id = ?";
	public static final String MAKE_GAME_INVISIBLE = "update game set is_visible = false where game.id = ?";
}
