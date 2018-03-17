package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.UserPojo;



/**
 * Servlet implementation class Tips
 */
@WebServlet("/Tips")
public class Tips extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tips() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		UserPojo  user=(UserPojo)session.getAttribute("user");
		if(user==null||(user.getState()!=1)){
			response.sendRedirect("/HomeworkUpload/index.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String tipInforCmd=request.getParameter("tipInforCmd");
		if(tipInforCmd!=null&&tipInforCmd.length()>0){
			if("FtpConnectError".equals(tipInforCmd)){
				out.print("{\"errorMsg\":1}");
			}
			else if("FtpConnectSuccess".equals(tipInforCmd)){
				 out.print("{\"success\":true}");
			}
				
		}
		else{
			
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
