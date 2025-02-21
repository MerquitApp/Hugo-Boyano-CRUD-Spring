const form = document.querySelector('#crear-proyecto-form');

form.addEventListener('submit', async function (event) {
    event.preventDefault();
    const formData = new FormData(form);

    try {
        const response = await fetch('/proyectos', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        });

        if (response.ok) {
            alert("Proyecto creado correctamente")
        } else {
            alert("Error al crear el proyecto")
        }

        form.reset();
    } catch (error) {

        alert("Error al crear el proyecto")
    }
});