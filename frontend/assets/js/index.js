const userTableBody = document.getElementById("user-table-body");
const cardHeader = document.querySelector(".card-header");
const successMessage = document.getElementById("success-message");
const errorMessage = document.getElementById("error-message");

function populateUserTable(users) {
    users.forEach(user => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <button class="btn btn-primary btn-sm edit-user" data-id="${user.id}">Editar</button>
                <button class="btn btn-danger btn-sm delete-user" data-id="${user.id}">Excluir</button>
            </td>
        `;
        userTableBody.appendChild(row);
    });
}

fetch("http://localhost:8080/users")
    .then(response => response.json())
    .then(responseJSON => populateUserTable(responseJSON.data))
    .catch(error => console.error("Erro ao buscar usuários:", error));

const deleteButtons = document.querySelectorAll(".delete-user");
document.addEventListener("click", function(event) {
    if (event.target && event.target.classList.contains("delete-user")) {
        const userId = event.target.getAttribute("data-id");
        deleteUser(userId);
    }

    if (event.target && event.target.classList.contains("edit-user")) {
        const userId = event.target.getAttribute("data-id");
        editUser(userId);
    }
});

function deleteUser(userId) {
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
        fetch(`http://localhost:8080/users/${userId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                const row = document.querySelector(`.edit-user[data-id="${userId}"]`).closest("tr");
                row.remove();

                successMessage.textContent = "Usuário excluído com sucesso!";
                successMessage.style.display = "block";
            } else {
                return response.json().then(data => {
                    throw new Error(data.message || 'Erro ao excluir usuário');
                });
            }
        })
        .catch(error => {
            errorMessage.textContent = 'Erro ao excluir usuário';
            errorMessage.style.display = "block";
            console.error('Erro:', error);
        });
    }
}

function editUser(userId) {
    window.location.href = `edit.html?id=${userId}`;
}