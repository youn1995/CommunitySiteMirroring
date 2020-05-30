package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import scraping.Board;

public class CommityDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;

	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void insertBoard(List<Board> list) {
		int insertNum = 0;
		conn = getConnect();
		for (Board board : list) {
			String sql = String.format(
					"insert into communitysite values(%d, '%s', '%s', '%s', '%s',%d ,%d , sysdate , '%s')",
					board.getId(), board.getDivision(), board.getTitle(), board.getBody(), board.getWriter(),
					board.getHits(), board.getRecommend(), board.getUrl());

			try {
				pstmt = conn.prepareStatement(sql);
				int r = pstmt.executeUpdate();
				insertNum += r;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(insertNum + " Row has been insert");

	}

}
