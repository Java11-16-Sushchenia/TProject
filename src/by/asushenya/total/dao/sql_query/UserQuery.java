package by.asushenya.total.dao.sql_query;

/**
 * Contains all SQL queries that user use.
 * 
 * @author Artyom Sushenya
 *
 */
public class UserQuery {
	public static final String FIND_USER_BY_EMAIL = "select id, login,email, password , role, cash from user where email = ?";
	public static final String FIND_USER_BY_LOGIN = "select id, login,email, password , role, cash,is_visible from user where login = ?";
	public static final String INSERT_INTO_USER = "insert into user ( login, password,email,role,cash) values(?,?,?,?,?);";
	public static final String INSERT_INTO_RATE = "insert into rate (user_id, game_id, money, choice, game_coefficient) values(?,?,?,?,?)";
	public static final String GET_GAMES_BY_GAME_KIND_RU = "select id, game_kind, date, (select team.name from team  where team.id = game.team_1) as `team_1`, (select team.name from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true and game_kind = ? and date>now() limit ?,?";
	public static final String GET_GAMES_BY_GAME_KIND_EN = "select id, game_kind, date, (select team.name_en from team  where team.id = game.team_1) as `team_1`, (select team.name_en from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true and game_kind = ? and date>now() limit ?,?";
	public static final String GET_ALL_GAMES_FOR_PAGE_RU = "select id, game_kind, date, (select team.name from team  where team.id = game.team_1) as `team_1`, (select team.name from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true and date>now() limit ?,?";
	public static final String GET_ALL_GAMES_FOR_PAGE_EN = "select id, game_kind, date, (select team.name_en from team  where team.id = game.team_1) as `team_1`, (select team.name_en from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true and date>now() limit ?,?";
	public static final String GET_ALL_GAMES_BY_GAME_KIND_COUNT = "select count(*) `games_count` from game where is_visible = true and date>now() and game_kind = ?";
	public static final String GET_ALL_GAMES_COUNT = "select count(*) `games_count` from game where is_visible = true and date>now()";
	public static final String GET_USER_RATES_COUNT = "select count(*) `rates_count` from rate where user_id = ?";
	public static final String GET_ALL_USER_RATES_RU = "select rate.id, (select name from team where team.id = game.team_1) `team_1`, (select name from team where team.id = game.team_2) `team_2`, rate.date, money, choice, game_coefficient, profit, is_success from rate inner join game on rate.game_id = game.id where user_id = ? limit ?,?";
	public static final String GET_ALL_USER_RATES_EN = "select rate.id, (select name_en from team where team.id = game.team_1) `team_1`, (select name_en from team where team.id = game.team_2) `team_2`, rate.date, money, choice, game_coefficient, profit, is_success from rate inner join game on rate.game_id = game.id where user_id = ? limit ?,?";

}
