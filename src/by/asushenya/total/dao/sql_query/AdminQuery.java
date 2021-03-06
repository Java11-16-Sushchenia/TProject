package by.asushenya.total.dao.sql_query;

/**
 * Contains all SQL queries that administrator use.
 * 
 * @author Artyom Sushenya
 *
 */
public class AdminQuery {
	public static final String ADD_NEW_GAME = "insert into game (game_kind, team_1, team_2, date, k1,kx,k2) values(?,?,?,?,?,?,?)";
	public static final String GET_TEAM_ID_BY_NAME_RU = "select team.id from team where team.name = ?";
	public static final String GET_TEAM_ID_BY_NAME_EN = "select team.id from team where team.name_en = ?";
	public final static String GET_ALL_USERS = "select id, login, password, email, role, cash,is_visible from user limit ? , ?";
	public static final String GET_ALL_USERS_COUNT = "select count(*) `users_count` from user";
	public static final String GET_TEAMS_BY_GAME_KIND_RU = "select id, name as `name` from team where game_kind = ?";
	public static final String GET_TEAMS_BY_GAME_KIND_EN = "select id, name_en `name` from team where game_kind = ?";
	public static final String BLOCK_USER = "update user set is_visible = 0 where id = ?";
	public static final String UNBLOCK_USER = "update user set is_visible = 1 where id = ?";

}
