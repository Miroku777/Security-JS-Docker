document.addEventListener('DOMContentLoaded', async function () {
    await showUserUsernameOnNavbar()
    await fillTableAboutUser();
});

async function dataAboutCurrentUser() {
    const response = await fetch("/api/user")
    return await response.json();
}

async function fillTableAboutUser() {
    const currentUserTable1 = document.getElementById("currentUserTable");
    const currentUser = await dataAboutCurrentUser();

    let currentUserTableHTML = "";
    currentUserTableHTML +=
        `<tr>
            <td>${currentUser.id}</td>
            <td>${currentUser.username}</td>
            <td>${currentUser.yearOfBirth}</td>
            <td>${currentUser.email}</td>
            <td>${currentUser.password}</td>
            <td>${currentUser.roles.map(role => role.roleName.substring(5)).join(' ')}</td>
        </tr>`
    currentUserTable1.innerHTML = currentUserTableHTML;
}

async function showUserUsernameOnNavbar() {
    const currentUserUsernameNavbar = document.getElementById("currentUserUsernameNavbar")
    const currentUser = await dataAboutCurrentUser();
    currentUserUsernameNavbar.innerHTML =
        `<strong>${currentUser.username}</strong>
                 with roles: 
                 ${currentUser.roles.map(role => role.roleName.substring(5)).join(' ')}`;
}

