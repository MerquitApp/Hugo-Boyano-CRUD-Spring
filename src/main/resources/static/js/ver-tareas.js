const deleteBtns = document.querySelectorAll('.btn-delete');
const editBtns = document.querySelectorAll('.btn-edit');
const modal = document.querySelector('#modal');
const btnCloseModal = document.querySelector('#modal-close');
const editTareaForm = document.querySelector('#editar-tarea-form');

let editingTareaId;

btnCloseModal.addEventListener('click', function () {
    modal.classList.remove('is-active');
});

editBtns.forEach(function (editBtn) {
    editBtn.addEventListener('click', function () {
        editingTareaId = editBtn.getAttribute('tareaId');
        modal.classList.add('is-active');
    });
})

editTareaForm.addEventListener('submit', async function (event) {
    event.preventDefault();
    const formData = new FormData(editTareaForm);

    try {
        const response = await fetch(`/tareas/${editingTareaId}`, {
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

deleteBtns.forEach(function (deleteBtn) {
    deleteBtn.addEventListener('click', async function () {
        const tareaId = deleteBtn.getAttribute('tareaId');

        try {
            const response = await fetch(`/tareas/${tareaId}`, {
                method: 'DELETE',
                credentials: 'include'
            });

            if (response.ok) {
                alert('Tarea borrada correctamente');
                window.location.reload();
            } else {
                alert('Error al borrar la tarea');
            }
        } catch (error) {
            alert('Error al borrar la tarea');
        }
    });
});