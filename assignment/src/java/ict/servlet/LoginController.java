
import ict.db.ProjectDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/loginController"})

public class LoginController extends HttpServlet {

    private ProjectDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ProjectDB(dbUrl, dbUser, dbPassword);
        System.out.print("123");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            String[] result = db.isValidUser(email, password);
            RequestDispatcher rd = null;
            if (result[0] != null) { // login success
                int account_id = Integer.parseInt(result[0]);
                int role_id = Integer.parseInt(result[1]);
                HttpSession session = request.getSession();
                session.setAttribute("role_id", role_id);
                session.setAttribute("account_id", account_id);
                if (role_id == 1) {//teacher
                    session.setAttribute("teacher_id", db.getTeacherID(account_id));

                    rd = getServletContext().getRequestDispatcher("/pages/teacher_main.jsp");
                    rd.forward(request, response);

                } else if (role_id == 2) {//student
                    session.setAttribute("stu_id", db.getStuID(account_id));

                    rd = getServletContext().getRequestDispatcher("/pages/student_main.jsp");
                    rd.forward(request, response);

                } else if (role_id == 3) {//admin
                    rd = getServletContext().getRequestDispatcher("/pages/admin_main.jsp");
                    rd.forward(request, response);
                }
            } else { // result is null
                rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
