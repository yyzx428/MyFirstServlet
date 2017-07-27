package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyFirstServlet extends HttpServlet {
	private String driverClass = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/student?useUnicode=true&characterEncoding=GBK";
	private String user = "root";
	private String passWord = "qwer123456";
	private Connection conn;

	public void init() {
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

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException{
		resp.setContentType("text/json");
		resp.setCharacterEncoding("utf-8"); 
		PrintWriter out=resp.getWriter();
		try {
			Statement statement=conn.createStatement();
			String sql="select * from class";
			ResultSet result=statement.executeQuery(sql);
			JSONArray jArray = new JSONArray();
			while(result.next()) {
				JSONObject jObject = new JSONObject();
				jObject.put("classNum",result.getString("classNum"));
				jObject.put("Name",result.getString("Name"));
				jArray.put(jObject);
			}
			result.close();
			statement.close();
			out.print(jArray);
			out.flush();
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(JSONException e) {
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
