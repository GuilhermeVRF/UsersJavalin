const editUserForm = document.getElementById('edit-user-form');
const nameInput = document.getElementById('name');
const emailInput = document.getElementById('email');
const userId = new URLSearchParams(window.location.search).get('id');
const successMessage = document.getElementById("success-message");
const errorMessage = document.getElementById("error-message");

fetch(`http://localhost:8080/users/${userId}`)
    .then(response => response.json())
    .then(responseJSON => {
        nameInput.value = responseJSON.data.name;
        emailInput.value = responseJSON.data.email;
    })
    .catch(error => {
        console.error('Erro ao buscar usuário:', error);
        alert('Erro ao buscar usuário: ' + error.message);
    });

editUserForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const userId = new URLSearchParams(window.location.search).get('id');

    const userData = {
        name: nameInput.value,
        email: emailInput.value
    };

    fetch(`http://localhost:8080/users/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (response.ok) {
            successMessage.textContent = response.message || "Usuário atualizado com sucesso!";
            successMessage.style.display = "block";
        } else {
            return response.json().then(data => {
                throw new Error(data.message || 'Erro ao atualizar usuário');
            });
        }
    })
    .catch(error => {
        errorMessage.textContent = 'Erro ao atualizar usuário';
        errorMessage.style.display = "block";
        console.error('Erro:', error);
    });
});