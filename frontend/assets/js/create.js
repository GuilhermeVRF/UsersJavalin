const createUserForm = document.getElementById('create-user-form');
const nameInput = document.getElementById('name');
const emailInput = document.getElementById('email');
const successMessage = document.getElementById("success-message");
const errorMessage = document.getElementById("error-message");

createUserForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const userData = {
        name: nameInput.value,
        email: emailInput.value
    };

    fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (response.ok) {
            successMessage.textContent = response.message || "Usuário criado com sucesso!";
            successMessage.style.display = "block";
            createUserForm.reset();
        } else {
            return response.json().then(data => {
                throw new Error(data.message || 'Erro ao criar usuário');
            });
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        errorMessage.textContent = 'Erro ao criar usuário';
        errorMessage.style.display = "block";
        console.error('Erro:', error);
    });
});