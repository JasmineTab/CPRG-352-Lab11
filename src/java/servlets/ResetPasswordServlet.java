package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURL().toString();
        String urlQuery = request.getQueryString();

        if (urlQuery != null && urlQuery.contains("uuid")) {

            request.setAttribute("uuid", urlQuery);

            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            String action = request.getParameter("action");

            switch (action) {

                case "sendReset":

                    String email = request.getParameter("emailReset");

                    Cookie cookie = new Cookie("email", email);
                    cookie.setMaxAge(60 * 60 * 24 * 365 * 3);
                    response.addCookie(cookie);

                    String url = request.getRequestURL().toString();

                    AccountService as = new AccountService();
                    String path = getServletContext().getRealPath("/WEB-INF");
                    User user = as.resetPassword(email, path, url);

                    if (user == null) {
                        request.setAttribute("email", email);
                        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                        return;
                    }

                    session.setAttribute("email", email);

                    request.setAttribute("message", "Password reset email sent to the email provided.");
                    response.sendRedirect("login");

                    break;

                case "resetPass":
                    
                    String newPass = request.getParameter("newPass");
                    String uuid = request.getParameter("uuid");

                    AccountService asTwo = new AccountService();
                    asTwo.changePassword(uuid, newPass);

                    response.sendRedirect("login");

                    break;

                default:

                    request.setAttribute("message", "action could not be completed");
                    response.sendRedirect("login");

            }

        } catch (Exception e) {
            System.out.println("something went wrong: " + e);
        }

    }

}
