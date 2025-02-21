const deleteBtns = document.querySelectorAll('.btn-delete');
const editBtns = document.querySelectorAll('.btn-edit');
const modal = document.querySelector('#modal');
const btnCloseModal = document.querySelector('#modal-close');
const editTareaForm = document.querySelector('#editar-proyecto-form');

let editingProyectoId;

btnCloseModal.addEventListener('click', function () {
    modal.classList.remove('is-active');
});

editBtns.forEach(function (editBtn) {
    editBtn.addEventListener('click', function () {
        const proyectoId = editBtn.getAttribute('proyectoId');
        editingProyectoId = proyectoId;
        modal.classList.add('is-active');
    });
})

deleteBtns.forEach(function (deleteBtn) {
    deleteBtn.addEventListener('click', async function () {
        const proyectoId = deleteBtn.getAttribute('proyectoId');

        try {
            const response = await fetch(`/proyectos/${proyectoId}`, {
                method: 'DELETE',
                credentials: 'include'
            });

            if (response.ok) {
                alert('Proyecto borrado correctamente');
                window.location.reload();
            } else {
                alert('Error al borrar el proyecto');
            }
        } catch (error) {
            alert('Error al borrar el proyecto');
        }
    });
});

editTareaForm.addEventListener('submit', async function (event) {
    event.preventDefault();
    const formData = new FormData(editTareaForm);

    try {
        const response = await fetch(`/proyectos/${editingProyectoId}`, {
            method: 'PATCH',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        });

        if (response.ok) {
            alert('Tarea editada correctamente');
            window.location.reload();
        } else {
            alert('Error al editar la tarea');
        }
    } catch (error) {
        alert('Error al editar la tarea');
    }finally {
        modal.classList.remove('is-active');
        editTareaForm.reset();
    }
});