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
        } else {
            alert('Error al crear la cuenta');
        }

        form.reset();

        setTimeout(() => {
            window.location.href = '/auth/login/form';
        }, 1000);
    } catch (error) {
        alert('Error al crear la cuenta');
    }
});