async function createNewUser(user) {
    await fetch("http://localhost:8080/api/admin",
        {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)})
}

let newUser = document.forms["newUser"];

async function addNewUserForm() {
    const newUserForm = document.getElementById("newUser");

    newUserForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const username = newUserForm.querySelector("#username").value.trim();
        const yearOfBirth = newUserForm.querySelector("#yearOfBirth").value.trim();
        const email = newUserForm.querySelector("#email").value.trim();
        const password = newUserForm.querySelector("#password").value.trim();

        let rolesForNewUser = [];
        for (let i = 0; i < newUser.rolesNew.options.length; i++) {
            if (newUser.rolesNew.options[i].selected) rolesForNewUser.push({
                id: newUser.rolesNew.options[i].value,
                role: "ROLE_" + newUser.rolesNew.options[i].text
            });
        }

        const newUserData = {
            username: username,
            yearOfBirth: yearOfBirth,
            email: email,
            password: password,
            roles: rolesForNewUser
        };

        await createNewUser(newUserData);
        newUserForm.reset();

        document.querySelector('a#show-users-table').click();
        await fillTableOfAllUsers();
    });
}

function loadRolesForNew() {
    let selectNew = document.getElementById("rolesNew");
    selectNew.innerHTML = "";

    fetch("http://localhost:8080/api/admin/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.roleName.toString().replace("ROLE_", "");
                selectNew.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesForNew);