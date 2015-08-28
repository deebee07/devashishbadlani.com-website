package ServletForm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Form
 */
public class Form extends HttpServlet  {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Form() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("contactName");
        String email = request.getParameter("contactEmail");
        String subject =  request.getParameter("contactSubject");
        String message = request.getParameter("contactMessage");
	
	
	String entry= "NAME:"+name+"----------"+"EMAIL:"+email+"----------"+"SUBJECT:"+subject+"----------"+"MESSAGE:"+message+"----------";
	
	System.out.println(entry);

	
    response.sendRedirect(response.encodeRedirectURL(request.getContextPath())+"/contactSuccess.html");
	
	
	
	
	
	
	}
	

}
