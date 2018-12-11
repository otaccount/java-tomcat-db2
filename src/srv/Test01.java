package srv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Test01
 */
@WebServlet("/test01/Controller01")
public class Test01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Test01() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		System.out.println("start");
		
		DataSource ds = null;
		try {
			Context context = new InitialContext();
//			Context ec = (Context)context.lookup("java:/comp/env");
//			ds = (DataSource)ec.lookup("jdbc/datasource");
			ds = (DataSource)context.lookup("java:/comp/env/jdbc/datasource");
			System.out.println("test");
		}catch(NamingException ne) {
			throw new ServletException(ne);
		}
		
		String sql = "select now() date";
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			){
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		}catch(SQLException se) {
			throw new ServletException(se);
		}
		
		System.err.println("end");
		
		String path = "entry.jsp";
		req.getRequestDispatcher(path).forward(req, res);;
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
