async function getUserDataById(userId) {
    const response = await fetch(`/api/admin/${userId}`);
    return await response.json();
}

async function fillModal(modal) {

    modal.addEventListener("show.bs.modal", async function (event) {

        const userId = event.relatedTarget.dataset.userId;
        const user = await getUserDataById(userId);

        const modalBody = modal.querySelector(".modal-body");

        const idInput = modalBody.querySelector("input[data-user-id='id']");
        const usernameInput = modalBody.querySelector("input[data-user-id='username']");
        const yearOfBirthInput = modalBody.querySelector("input[data-user-id='yearOfBirth']");
        const emailInput = modalBody.querySelector("input[data-user-id='email']");
        const passwordInput = modalBody.querySelector("input[data-user-id='password']");
        if (passwordInput !== null) {
            passwordInput.value = user.password;
        }

        idInput.value = user.id;
        usernameInput.value = user.username;
        yearOfBirthInput.value = user.yearOfBirth;
        emailInput.value = user.email;


        let rolesSelect = HTMLSelectElement;

        let rolesSelectEdit = modalBody.querySelector("select[data-user-id='rolesEdit']");
        let rolesSelectDelete = modalBody.querySelector("select[data-user-id='rolesDelete']");
        let userRolesHTML = "";

        if (rolesSelectEdit !== null) {
            rolesSelect = rolesSelectEdit;
            userRolesHTML +=
                `<option value="ROLE_USER">USER</option>
                 <option value="ROLE_ADMIN">ADMIN</option>`
        } else if (rolesSelectDelete !== null) {
            rolesSelect = rolesSelectDelete;
            for (let i = 0; i < user.roles.length; i++) {
                userRolesHTML +=
                    `<option value="${user.roles[i].roleName}">${user.roles[i].roleName.substring(5)}</option>`;
            }
        }
        rolesSelect.innerHTML = userRolesHTML;
    })
}

