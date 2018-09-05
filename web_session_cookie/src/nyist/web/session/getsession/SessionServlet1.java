package nyist.web.session.getsession;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object attribute = request.getSession().getAttribute("name");
		String str = String.valueOf(attribute);
		response.getWriter().write(str+"\n");
		String id = request.getSession().getId();
		response.getWriter().write(id);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}