document.addEventListener('DOMContentLoaded', function() {
    // Obtener elementos del DOM
    const odontologoForm = document.getElementById('odontologoForm');
    const odontologosTable = document.getElementById('odontologosTable').getElementsByTagName('tbody')[0];

    // Manejar el evento de envío del formulario
    odontologoForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(odontologoForm);
        const data = {
            numMatricula: formData.get('numMatricula'),
            nombre: formData.get('nombre'),
            apellido: formData.get('apellido')
        };

        // Enviar datos al servidor
        fetch('/api/odontologos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(odontologo => {
            // Agregar nuevo odontólogo a la tabla
            const newRow = odontologosTable.insertRow();
            newRow.innerHTML = `
                <td>${odontologo.numMatricula}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
            `;
        })
        .catch(error => console.error('Error:', error));
    });

    // Obtener lista de odontólogos al cargar la página
    fetch('/api/odontologos')
        .then(response => response.json())
        .then(odontologos => {
            odontologos.forEach(odontologo => {
                const newRow = odontologosTable.insertRow();
                newRow.innerHTML = `
                    <td>${odontologo.numMatricula}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.apellido}</td>
                `;
            });
        })
        .catch(error => console.error('Error:', error));
});

