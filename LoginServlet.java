package donation_db;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            String hashedPassword = hashPassword(password);
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM users WHERE username=? AND password=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedPassword);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            response.sendRedirect("admin.jsp");
                        } else {
                            response.sendRedirect("login.jsp?error=1");
                        }
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
