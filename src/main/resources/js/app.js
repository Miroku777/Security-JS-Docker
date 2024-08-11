document.addEventListener('DOMContentLoaded', async function () {
    await showUserUsernameOnNavbar()
    await fillTableOfAllUsers();
    await fillTableAboutCurrentUser();
    await addNewUserForm();
    await DeleteModalHandler();
    await EditModalHandler();
});

const ROLE_USER = {roleId: 1, roleName: "ROLE_USER"};
const ROLE_ADMIN = {roleId: 2, roleName: "ROLE_ADMIN"};

async function showUserUsernameOnNavbar() {
    const currentUserUsernameNavbar = document.getElementById("currentUserUsernameNavbar")
    const currentUser = await dataAboutCurrentUser();
    currentUserUsernameNavbar.innerHTML =
        `<strong>${currentUser.username}</strong>
                 with roles: 
                 ${currentUser.roles.map(role => role.roleName.substring(5)).join(' ')}`;
}
