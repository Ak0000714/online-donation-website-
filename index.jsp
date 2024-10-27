<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
           <li><a href="login.jsp">Login</a></li>
            <li><a href="admin.jsp">AdminDashboard</a></li>
            <li><a href="donor.jsp">DonorDashboard</a></li>
        </ul>
    </nav>
    <section class="hero">
        <h1>Welcome to Our Donation Platform</h1>
        <p>Join us in making a difference. Donate now!</p>
        <a href="login.jsp"><button>Login</button></a>
    </section>
    <section class="donation-categories">
        <h2>Select a Donation Category</h2>
        <form>
            <label for="donationCategory">Choose a category:</label>
            <select id="donationCategory" name="donationCategory">
                <option value="education">Education</option>
                <option value="healthcare">Health care</option>
                <option value="environment">Environment</option>
                <option value="animalWelfare">Animal Welfare</option>
                <option value="community">Community Development</option>
            </select>
            <button type="submit">Proceed</button>
        </form>
    </section>
</body>
</html>
    