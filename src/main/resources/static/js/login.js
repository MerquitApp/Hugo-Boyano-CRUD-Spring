const form = document.querySelector('#login-form');

form.addEventListener('submit', async function (event) {
    event.preventDefault();
    const formData = new FormData(form);

    try {
        const response = await fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        });

        if (response.ok) {
            alert('Sesión iniciada correctamente');
            window.location.reload();
        } else {
            alert('Error al iniciar sesión');
        }

        form.reset();
    } catch (error) {
        alert('Error al iniciar sesión');
    }
});