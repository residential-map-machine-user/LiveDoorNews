package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BaseClasses.BaseDAO;
import Beans.ItemBean;

public class ArticleDAO extends BaseDAO {
	public ArticleDAO() {
	}

	public int addArticle(String title, String url, String article,
			int idCategory) {
		startConnection();
		int successNum = 0;
		String sql = "INSERT INTO table_article (title,url,article,id_category) values(?,?,?,?);";
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			int cnt = 1;
			pr.setString(cnt++, title);
			pr.setString(cnt++, url);
			pr.setString(cnt++, article);
			pr.setInt(cnt++, idCategory);
			successNum = pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			finishConnection();
		}
		return successNum;
	}

	public ArrayList<ItemBean> selectArticleByCategoryId(int idCategory) {
		startConnection();
		ArrayList<ItemBean> articleList = new ArrayList<>();
		String sql = "SELECT * FROM table_article WHERE id_category=?;";
		ResultSet rs = null;
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			int cnt = 1;
			pr.setInt(cnt++, idCategory);
			rs = pr.executeQuery();
			while (rs.next()) {
				ItemBean item = new ItemBean();
				item.setArticle(rs.getString("article"));
				item.setTitle(rs.getString("title"));
				item.setUrl(rs.getString("url"));
				articleList.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			finishConnection();
		}
		return articleList;
	}

	public int deleteArticle() {
		int successNum = 0;
		startConnection();
		String sql = "DELETE FROM table_article;";
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			int cnt = 1;
			successNum = pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			finishConnection();
		}
		return successNum;
	}
}
