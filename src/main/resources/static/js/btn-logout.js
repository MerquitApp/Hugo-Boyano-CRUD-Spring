const btnLogout = document.querySelector('#btn-logout');

btnLogout.addEventListener('click', async function () {
    try {
        const response = await fetch('/auth/logout', {
            credentials: 'include'
        });

        if (response.ok) {
            alert('Sesión cerrada correctamente');
            window.location.reload()
        } else {
            alert('Error al cerrar sesión');
        }
    } catch (error) {
        alert('Error al cerrar sesión');
    }
});