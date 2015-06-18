//package DAOs;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import BaseClasses.BaseDAO;
//import Beans.UserBean;
//import Constants.AppConstants;
//import Utils.Encrypt;
//import Utils.Util;
//
//public class UserDAO extends BaseDAO {
//	public UserDAO() {
//		startConnection();
//	}
//
//	// ユーザの追加
//	// ユーザの削除
//	// ユーザの編集
//	// 編集に関してはかなり細かい単位で変種可能にする
//	// ユーザの全権表示
//	// パスヲード用にセレクトメソッド
//	// ログインのためのメソッド
//	
//	//ユーザグインするためのメソッド
//	@SuppressWarnings("finally")
//	public UserBean selectUserForLogin(HttpServletRequest request) {
//		// 該当するユーザー
//		UserBean user = new UserBean();
//		ResultSet rs = null;
//		try {
//			// sql文の作成
//			String sql = "SELECT * FROM table_user"
//					+ " Where account_name = ? AND" + " password = ?;";
//			PreparedStatement prstmt = conn.prepareStatement(sql);
//			int incrementalSymbol = 1;
//			prstmt.setString(incrementalSymbol++,
//					request.getParameter("ACCOUNT_NAME"));
//			prstmt.setString(incrementalSymbol++,
//					request.getParameter("PASSWORD"));
//			rs = prstmt.executeQuery();
//			// 結果の取得
//			if (rs.next()) {
//				user.setUserId(rs.getInt("user_id"));
//				user.setMail(rs.getString("mail"));
//				user.setAuthFlag(rs.getInt("auth_flag"));
//				user.setAccountName(rs.getString("account_name"));
//				user.setDeleteFlag(rs.getInt("delete_flag"));
//				user.setUserTypes(rs.getInt("user_type"));
//				user.setCreatedAt(rs.getDate("created_at"));
//				user.setUpdatedAt(rs.getDate("updated_at"));
//				Util.l("データベース処理っている");
//			}
//		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
//		} finally {
//			finishConnection();
//			Util.l(user.getAccountName());
//			return user;
//		}
//	}
//	
//	//ユーザ全権を取得してくるためのメソッド
//	public List<UserBean> selectALLUserList() {
//		// ユーザーリスト
//		List<UserBean> userList = new ArrayList<>();
//		try {
//			// クエリ文作成
//			String sql = "SELECT * FROM table_user";
//			PreparedStatement prstmt = conn.prepareStatement(sql);
//			// 検索実行
//			ResultSet rs = null;
//			prstmt = conn.prepareStatement(sql);
//			rs = prstmt.executeQuery();
//			// テーブルからの結果を取得
//			while (rs.next()) {
//				UserBean user = new UserBean();
//				user.setUserId(rs.getInt("user_id"));
//				user.setMail(rs.getString("mail"));
//				user.setAuthFlag(rs.getInt("auth_flag"));
//				user.setAccountName(rs.getString("account_name"));
//				user.setDeleteFlag(rs.getInt("delete_flag"));
//				user.setUserTypes(rs.getInt("user_type"));
//				user.setCreatedAt(rs.getDate("created_at"));
//				user.setUpdatedAt(rs.getDate("updated_at"));
//				userList.add(user);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			// データベース切断処理
//			finishConnection();
//		}
//		return userList;
//	}
//	
//	//ユーザの追加用のメソッド
//	public int addUser(HttpServletRequest request) {
//		// 成功件数
//		int successNum = 0;
//		try {
//			// フォームから送られてきた値の取得
//			String userName = request.getParameter("ACCOUNT_NAME");
//			String mail = request.getParameter("MAIL");
//			String password = request.getParameter("PASSWORD");
//			String checkPassword = request.getParameter("CHECK_PASSWORD");
//			Util.l("ok1>>>>>" + userName + mail + password + checkPassword);
//			Util.l("チェック結果>>>>>" + checkUserName(userName) + checkEmail(mail)
//					+ checkUserPassword(password));
//			// 値の確認
//			if (checkUserName(userName) && checkEmail(mail)
//					&& checkUserPassword(password)) {
//				// パスワードの確認
//				if (password.equals(checkPassword)) {
//					// // 暗号化
//
//					String encryptedPassword = Encrypt.SHA512(password);
//					Util.l(encryptedPassword);
//					// sql文の作成
//					String sql = "INSERT INTO table_user (mail, account_name, password)"
//							+ " values(?,?,?);";
//					PreparedStatement prstmt = conn.prepareStatement(sql);
//					// 値のセット
//					prstmt.setString(1, mail);
//					prstmt.setString(2, userName);
//					prstmt.setString(3, encryptedPassword);
//					// SQL実行
//					successNum = prstmt.executeUpdate();
//					Util.l("成功件数" + successNum);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return successNum;
//	}
//
//	//ユーザのアカウント名の更新用のメソッド
//	public int updateAccountName(HttpServletRequest request) {
//		int successNum = 0;
//		try {
//			// フォームから送られてきた値の取得
//			String accountName = request.getParameter("ACCOUNT_NAME");
//			int userId = ((UserBean) request.getSession().getAttribute(
//					"USER_INF")).getUserId();
//			Util.l("ok1>>>>>" + accountName);
//			Util.l("チェック結果>>>>>" + checkUserName(accountName));
//			// sql文の作成
//			String sql = "UPDATE table_user SET account_name=? WHERE user_id=?;";
//			PreparedStatement prstmt = conn.prepareStatement(sql);
//			int incrementalSymbol = 1;
//			// 値のセット
//			prstmt.setString(incrementalSymbol++, accountName);
//			prstmt.setInt(incrementalSymbol++, userId);
//			// SQL実行
//			successNum = prstmt.executeUpdate();
//			Util.l("成功件数" + successNum);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return successNum;
//	}
//	
//	//ユーザのパスワード更新用のメソッド
//	public UserBean selectUserForUpdateByUserId(HttpServletRequest request) {
//		UserBean user = new UserBean();
//		ResultSet rs = null;
//		try {
//			// sql文の作成
//			String sql = "SELECT password account_name FROM table_user"
//					+ " Where user_id=?;";
//			PreparedStatement prstmt = conn.prepareStatement(sql);
//			int incrementalSymbol = 1;
//			rs = prstmt.executeQuery();
//			// 結果の取得
//			if (rs.next()) {
//				user.setPassword(rs.getString("password"));
//				user.setAccountName(rs.getString("account_name"));
//				Util.l("データベース処理っている");
//			}
//		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
//		} finally {
//			finishConnection();
//			Util.l(user.getAccountName());
//			return user;
//		}
//	}
//	
//	//ユーザのパスワードを更新するメソッド
//	public int updateUserPassword(HttpServletRequest request) {
//		int successNum = 0;
//		try {
//			UserBean bufferUserInf = selectUserForUpdateByUserId(request);
//			String password = request.getParameter("PASSWORD");
//			String newPassword = request.getParameter("NEW_PASSWORD");
//			String checkPassword = request.getParameter("CHECK_PASSWORD");
//			int userId = ((UserBean) request.getSession().getAttribute(
//					"UESR_INF")).getUserId();
//			UserBean user = selectUserForLogin(request);
//			Util.l("ok1>>>>>" + password + checkPassword + newPassword);
//			Util.l("チェック結果" + checkUserPassword(newPassword));
//			String encryptedOldPassword = Encrypt.SHA512(newPassword);
//			String encryptedNewPassword = Encrypt.SHA512(newPassword);
//			if (!encryptedOldPassword.equals(bufferUserInf.getPassword())) {
//				return successNum;
//			}
//			if (newPassword.equals(checkPassword)) {
//				// パスワードの暗号化
//				String sql = "UPDATE table_user SET password = ? WHERE user_id=? AND password=?";
//				PreparedStatement prstmt = conn.prepareStatement(sql);
//				int incrementalSymbol = 1;
//				prstmt.setString(incrementalSymbol++, password);
//				prstmt.setString(incrementalSymbol++,
//						bufferUserInf.getPassword());
//				prstmt.setString(incrementalSymbol++,
//						bufferUserInf.getAccountName());
//				successNum = prstmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return successNum;
//	}
//
//	//ユーザ削除用のメソッド
//	public int deleteUser(HttpServletRequest request){
//		int successNum = 0;
//		try{
//			int userId = ((UserBean)request.getSession().getAttribute("USER_INF")).getUserId();
//			String accountName = request.getParameter("ACCOUNT_NAME");
//			String sql = "UPDATE table_user SET delete_flag=? WHERE user_id=?";
//			PreparedStatement prstmt = conn.prepareStatement(sql);
//			int incrementalSymbol = 1;
//			prstmt.setBoolean(incrementalSymbol++, true);
//			prstmt.setInt(incrementalSymbol++, userId);
//			successNum = prstmt.executeUpdate();
//		}catch(SQLException e){
//			e.printStackTrace();
//			return successNum;
//		}
//		return successNum;
//	}
//	
//	//ユーザのメールアドレス更新用のメソッド
//	public int updateUserMail(HttpServletRequest request) {
//		int successNum = 0;
//		try {
//			// フォームから送られてきた値の取得
//			String mail = request.getParameter("MAIL");
//			int userId = ((UserBean) request.getSession().getAttribute(
//					"USER_INF")).getUserId();
//			Util.l("ok1>>>>>" + mail);
//			Util.l("チェック結果>>>>>" + checkUserName(mail));
//			// sql文の作成
//			String sql = "UPDATE table_user SET mail=? WHERE user_id=?;";
//			PreparedStatement prstmt = conn.prepareStatement(sql);
//			int incrementalSymbol = 1;
//			// 値のセット
//			prstmt.setString(incrementalSymbol++, mail);
//			prstmt.setInt(incrementalSymbol++, userId);
//			// SQL実行
//			successNum = prstmt.executeUpdate();
//			Util.l("成功件数" + successNum);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return successNum;
//	}
//	
//	//ユーザ名の確認用のメソッド
//	private boolean checkUserName(String userName) {
//		if (userName == null) {
//			return false;
//		}
//		Util.l("一つ目name");
//		if (userName.length() <= 4 || userName.length() >= 16) {
//			return false;
//		}
//		Util.l("2つ目name");
//		if (!userName.matches("^[0-9a-zA-Z]*$")) {
//			return false;
//		}
//		Util.l("3つ目name");
//		return true;
//	}
//	
//	//パスワード確認用のメソッド
//	private boolean checkUserPassword(String password) {
//		if (password == null) {
//			return false;
//		}
//		Util.l("一つ目pass");
//		if (password.length() <= 7 || password.length() >= 16) {
//			return false;
//		}
//		Util.l("2つ目pass");
//		if (!password.matches("^[0-9a-zA-Z]*$")) {
//			return false;
//		}
//		Util.l("3つ目pass");
//		return true;
//	}
//	
//	//メール確認用のメソッド
//	private boolean checkEmail(String mail) {
//		if (mail == null) {
//			return false;
//		}
//		Util.l("一つ目em");
//		if (!mail.matches("[^@].*@{1}.*")) {
//			return false;
//		}
//		Util.l("2つ目em");
//		return true;
//	}
//}
