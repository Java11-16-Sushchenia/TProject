package by.asushenya.total.dao.sql_query;

public class AdminQuery {
	public static final String addNewGameQuerry = "insert into game (game_kind, team_1, team_2, date, k1,kx,k2) values(?,?,?,?,?,?,?)";
	public static final String GET_TEAM_ID_BY_NAME_RU = "select team.id from team where team.name = ?";
	public static final String GET_TEAM_ID_BY_NAME_EN = "select team.id from team where team.name_en = ?";
}
