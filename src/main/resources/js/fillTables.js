async function dataAboutAllUsers() {
    const response = await fetch("http://localhost:8080/api/admin");
    return await response.json();
}

async function dataAboutCurrentUser() {
    const response = await fetch("http://localhost:8080/api/user")
    return await response.json();
}

//для окна админа
async function fillTableOfAllUsers() {
    const usersTable = document.getElementById("usersTable");
    const users = await dataAboutAllUsers();

    let usersTableHTML = "";
    for (let user of users) {

        usersTableHTML += `<tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.yearOfBirth}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.roles.map(role => role.roleName.substring(5)).join(' ')}</td>
                <td>
                    <button class="btn btn-info btn-sm btn-white"
                            data-bs-toggle="modal"
                            data-bs-target="#editModal"
                            data-user-id="${user.id}">
                        Edit</button>
                </td>
                <td>
                    <button class="btn btn-danger btn-sm btn-delete"
                            data-bs-toggle="modal"
                            data-bs-target="#deleteModal"
                            data-user-id="${user.id}">                     
                        Delete</button>
                </td>
            </tr>`;
    }
    usersTable.innerHTML = usersTableHTML;
}

//для окна юзера на странице админа
async function fillTableAboutCurrentUser() {
    const currentUserTable = document.getElementById("currentUserTable");
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
    currentUserTable.innerHTML = currentUserTableHTML;
}