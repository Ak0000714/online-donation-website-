package donation_db;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CampaignServlet")
public class CampaignServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String campaignName = request.getParameter("campaignName");
        String goalAmount = request.getParameter("goalAmount");
        String deadline = request.getParameter("deadline");
        String description = request.getParameter("description");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO campaigns (campaign_name, goal_amount, deadline, description) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, campaignName);
                ps.setBigDecimal(2, new BigDecimal(goalAmount));
                ps.setDate(3, Date.valueOf(deadline));
                ps.setString(4, description);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM campaigns";
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                PrintWriter out = response.getWriter();
                while (rs.next()) {
                    out.println("<div>" +
                                "<h3>" + rs.getString("campaign_name") + "</h3>" +
                                "<p>Goal: $" + rs.getBigDecimal("goal_amount") + "</p>" +
                                "<p>Deadline: " + rs.getDate("deadline") + "</p>" +
                                "<p>Description: " + rs.getString("description") + "</p>" +
                                "<button class='donate-button' data-campaign-id='" + rs.getInt("id") + "'>Donate</button>" +
                                "</div>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
