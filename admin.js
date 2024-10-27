function fetchCampaigns() {
    fetch('CampaignServlet')
    .then(response => response.text())
    .then(data => {
        document.getElementById('campaignList').innerHTML = data;
        console.log("Fetched campaigns:", data); // Debugging log
    })
    .catch(error => console.error('Error:', error));
}

document.getElementById('campaignForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const campaignName = document.getElementById('campaignName').value;
    const goalAmount = document.getElementById('goalAmount').value;
    const deadline = document.getElementById('deadline').value;
    const description = document.getElementById('description').value;

    fetch('CampaignServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `campaignName=${campaignName}&goalAmount=${goalAmount}&deadline=${deadline}&description=${description}`
    })
    .then(() => fetchCampaigns())
    .catch(error => console.error('Error:', error));
});

fetchCampaigns();
