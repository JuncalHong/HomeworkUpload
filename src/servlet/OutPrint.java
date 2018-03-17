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

import net.sf.json.JSONArray;
import database.SqlLog;
import database.SqlUser;
import pojo.UserPojo;

/**
 * Servlet implementation class OutPrint
 */
@WebServlet("/OutPrint")
public class OutPrint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private SqlUser sqlUser=new SqlUser();
       private SqlLog sqlLog=new SqlLog();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutPrint() {
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
		if(user==null||user.getState()!=1||user.getType()!=1){
			response.sendRedirect("/HomeworkUpload/index.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String outPrintInforCmd=request.getParameter("outPrintInforCmd");
		if(outPrintInforCmd!=null&&outPrintInforCmd.length()>0){
			if("FtpConnectError".equals(outPrintInforCmd)){
				out.print("{\"errorMsg\":1}");
			}
			else if("FtpConnectSuccess".equals(outPrintInforCmd)){
				 out.print("{\"success\":true}");
			}
			else if("yhlb_r".equals(outPrintInforCmd)){ 
				 out.print(JSONArray.fromObject(sqlUser.getAllUser()));
				     
			}
			else if("loglist".equals(outPrintInforCmd)){
				 out.print(JSONArray.fromObject(sqlLog.getAllLog()));
			}
				
		}
		else{
			response.sendRedirect("/HomeworkUpload/index.jsp");
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
