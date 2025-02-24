const form = document.querySelector('#register-form');

form.addEventListener('submit', async function (event) {
    event.preventDefault();
    const formData = new FormData(form);

    try {
        const response = await fetch('/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        });

        if (response.ok) {
            alert('Cuenta creada correctamente');
            window.location.href = '/auth/login/form';
        } else {
            alert('Error al crear la cuenta');
        }

        form.reset();
    } catch (error) {
        alert('Error al crear la cuenta');
    }
});