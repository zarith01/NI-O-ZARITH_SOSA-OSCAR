const API_BASE_URL = 'http://localhost:63342/api';  // Asegúrate de que la URL sea correcta

async function fetchOdontologos() {
    const response = await fetch(`${API_BASE_URL}/odontologos`);
    return response.json();
}

async function addOdontologo(odontologo) {
    const response = await fetch(`${API_BASE_URL}/odontologos`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontologo)
    });
    return response.json();
}

// Similar functions for patients and appointments

