<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="admin.css">
</head>
<body>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="admin.jsp">Admin Dashboard</a></li>
            <li><a href="donor.jsp">Donor Dashboard</a></li>
        </ul>
    </nav>
    <div class="dashboard-container">
        <h1>Admin Dashboard</h1>
        <form id="campaignForm">
            <input type="text" id="campaignName" placeholder="Campaign Name" required>
            <input type="number" id="goalAmount" placeholder="Goal Amount" required>
            <input type="date" id="deadline" required>
            <textarea id="description" placeholder="Description" required></textarea>
            <button type="submit">Add Campaign</button>
        </form>
        <h2>Current Campaigns</h2>
        <div id="campaignList"></div>
    </div>
    <script src="admin.js"></script>
</body>
</html>
