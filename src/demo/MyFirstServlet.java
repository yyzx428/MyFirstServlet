package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyFirstServlet extends HttpServlet {

	private Connection conn;
	public static ActionConfig ActionConfig;
	public void init() {
		conn = MySql.getConnection();
		ActionConfig=new ActionConfig();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json");
		resp.setCharacterEncoding("utf-8");
		String url=req.getRequestURI();
		Object result = null;
		Action currentAction=ActionConfig.findAction(url);
		try {
			Class<?> action=Class.forName(currentAction.getClassName());
			Constructor<?> constructor=action.getDeclaredConstructor();
			Object entity=constructor.newInstance();
			Method[] menthods=action.getMethods();
			Method targetMenthod=null;
			for(Method current:menthods) {
				if(!(current.getName().equals(currentAction.getName()))) continue;
				targetMenthod=current;
				break;
			}
			if(targetMenthod!=null) {
				result=targetMenthod.invoke(entity, null);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		PrintWriter out = resp.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void destroy() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
