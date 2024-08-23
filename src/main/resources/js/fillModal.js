async function getUserDataById(userId) {
    const response = await fetch(`http://localhost:8080/api/admin/${userId}`);
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
        let rolesInput = modalBody.querySelector("input[data-user-id='roles']");
        if (passwordInput !== null) {
            passwordInput.value = user.password;
        }

        idInput.value = user.id;
        usernameInput.value = user.username;
        yearOfBirthInput.value = user.yearOfBirth;
        emailInput.value = user.email;
    })
}

