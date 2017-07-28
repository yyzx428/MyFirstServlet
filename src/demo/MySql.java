package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MySql {
	final static String driverClass = "com.mysql.jdbc.Driver";
	final static String url = "jdbc:mysql://127.0.0.1:3306/student?useUnicode=true&characterEncoding=GBK&useSSL=false";
	final static String user = "root";
	final static String passWord = "qwer123456";
	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName(driverClass);
				conn = DriverManager.getConnection(url, user, passWord);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static JSONArray getClasses() {
		JSONArray jArray = null;
		ResultSet result = null;
		Statement statement = null;
		try {
			statement = conn.createStatement();
			String sql = "select classNum as Num,Name from class";
			result = statement.executeQuery(sql);
			ResultSetMetaData data = result.getMetaData();
			jArray = new JSONArray();
			while (result.next()) {
				JSONObject jObject = new JSONObject();
				for (int i = 1; i <= data.getColumnCount(); i++) {
					jObject.put(data.getColumnName(i), result.getString(i));
				}
				jArray.put(jObject);
			}
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jArray;
	}
}
