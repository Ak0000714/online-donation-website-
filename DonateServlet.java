package donation_db;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DonateServlet")
public class DonateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String donorName = request.getParameter("donorName");
        String donationAmount = request.getParameter("donationAmount");
        String campaignId = request.getParameter("campaignId");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO donations (donor_name, donation_amount, campaign_id) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, donorName);
                ps.setBigDecimal(2, new BigDecimal(donationAmount));
                ps.setInt(3, Integer.parseInt(campaignId));
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new donation was added successfully!");
                } else {
                    System.out.println("Failed to insert donation.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("thankyou.jsp");
    }
}
