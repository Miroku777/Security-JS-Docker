async function sendDataEditUser(user) {
    await fetch("http://localhost:8080/api/admin",
        {method: "PUT", headers: {'Content-type': 'application/json'}, body: JSON.stringify(user)})
}

const editModal = document.getElementById("editModal");

async function EditModalHandler() {
    await fillModal(editModal);
}

let formEdit = document.forms["formEdit"];
formEdit.addEventListener("submit", async function (event) {
    event.preventDefault();

    //для выбора ролей для пуша
    let rolesForEdit = [];
    for (let i = 0; i < formEdit.rolesEdit.options.length; i++) {
        if (formEdit.rolesEdit.options[i].selected) rolesForEdit.push({
            roleName: "ROLE_" + formEdit.rolesEdit.options[i].text,
        })
    }

    let user = {
        id: document.getElementById("idEdit").value,
        username: document.getElementById("usernameEdit").value,
        yearOfBirth: document.getElementById("yearOfBirthEdit").value,
        email: document.getElementById("emailEdit").value,
        password: document.getElementById("passwordEdit").value,
        roles: rolesForEdit
    }

    await sendDataEditUser(user);
    await fillTableOfAllUsers();

    const modalBootstrap = bootstrap.Modal.getInstance(editModal);
    modalBootstrap.hide();
    console.log(user)
})

//для показа ролей в окне
function loadRolesForEdit() {
    let selectEdit = document.getElementById("rolesEdit");
    selectEdit.innerHTML = "";

    fetch("http://localhost:8080/api/admin/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.roleName.toString().replace("ROLE_", "");
                selectEdit.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesForEdit);


