document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('LoginServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `username=${username}&password=${password}`
    })
    .then(response => response.text())
    .then(data => {
        if (data.includes('error=1')) {
            document.getElementById('errorMessage').innerText = 'Invalid username or password';
        } else {
            window.location.href = 'admin.jsp';
        }
    })
    .catch(error => console.error('Error:', error));
});
