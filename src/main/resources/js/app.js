document.addEventListener('DOMContentLoaded', async function () {
    await showUserUsernameOnNavbar()
    await fillTableOfAllUsers();
    await fillTableAboutCurrentUser();
    await addNewUserForm();
    await DeleteModalHandler();
    await EditModalHandler();
});

async function showUserUsernameOnNavbar() {
    const currentUserUsernameNavbar = document.getElementById("currentUserUsernameNavbar")
    const currentUser = await dataAboutCurrentUser();
    currentUserUsernameNavbar.innerHTML =
        `<strong>${currentUser.username}</strong>
                 with roles: 
                 ${currentUser.roles.map(role => role.roleName.substring(5)).join(' ')}`;
}
