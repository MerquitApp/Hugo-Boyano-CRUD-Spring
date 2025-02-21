const form = document.querySelector('#crear-tarea-form');
const proyectoId = document.querySelector('#proyecto_id');

const url = new URL(window.location.href);

if (url.searchParams.get('proyecto_id')) {
    proyectoId.value = url.searchParams.get('proyecto_id');
}

form.addEventListener('submit', async function (event) {
    event.preventDefault();
    const formData = new FormData(form);

    const proyectoId = formData.get('proyecto_id');

    if(!proyectoId) {
        alert('Debes seleccionar un proyecto');
        return;
    }

    try {
        const response = await fetch('/tareas', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        });

        if (response.ok) {
            alert("Tarea creada correctamente")
        } else {
            alert("Error al crear la tarea")
        }

        form.reset();
    } catch (error) {
        alert("Error al crear la tarea")
    }
});