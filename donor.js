document.addEventListener('DOMContentLoaded', async (event) => {
    async function fetchCampaigns() {
        try {
            const response = await fetch('CampaignServlet');
            const data = await response.text();
            document.getElementById('campaignList').innerHTML = data;
            console.log("Fetched campaigns:", data);
        } catch (error) {
            console.error('Error:', error);
        }
    }

    document.getElementById('campaignList').addEventListener('click', async function(event) {
        if (event.target && event.target.matches('.donate-button')) {
            await handleDonation(event.target.dataset.campaignId);
        }
    });

    async function handleDonation(campaignId) {
        const donorName = prompt('Enter your name:');
        const donationAmount = prompt('Enter donation amount:');

        if (donorName && donationAmount) {
            try {
                await fetch('DonateServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: `donorName=${donorName}&donationAmount=${donationAmount}&campaignId=${campaignId}`
                });
                await fetchCampaigns();
            } catch (error) {
                console.error('Error:', error);
            }
        }
    }

    await fetchCampaigns();
});
