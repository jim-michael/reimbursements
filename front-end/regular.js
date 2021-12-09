window.addEventListener('load', async () => {

    let res = await fetch('http://localhost:8081/checkloginstatus', {
        credentials: 'include',
        method: 'GET'
    });

    if (res.status === 200) {
        let userObj = await res.json();

        if (userObj.userRole === 'regular') {
            window.location.href = 'regular.html';
        }
    } else if (res.status === 401) {
        window.location.href = 'index.html';
    }

    // If we make it past the authorization checks, call another function that will 
    // retrieve all assignments
    populateTableWithAssignments();

});

async function populateTableWithAssignments() {
    console.log("yahoo");
    let res = await fetch('http://localhost:8081/client/receipts', {
        credentials: 'include',
        method: 'GET'
    });
     console.log("statuss", res.status);
    let tbodyElement = document.querySelector("#assignment-table tbody");
    tbodyElement.innerHTML = '';

    let assignmentArray =  await res.json();
    //console.log(assignmentArray.length)

    for (let i = 0; i < assignmentArray.length; i++) {
        let assignment = assignmentArray[i];

        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        td1.innerHTML = assignment.type;

        let td2 = document.createElement('td');
        td2.innerHTML = assignment.description;

        let td3 = document.createElement('td');
        td3.innerHTML = assignment.amount;
        

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        

        tbodyElement.appendChild(tr);
    }
}


let logoutBtn = document.querySelector('#logout');

logoutBtn.addEventListener('click', async () => {

    let res = await fetch('http://localhost:8081/logout', {
        'method': 'POST',
        'credentials': 'include'
    });

    if (res.status === 200) {
        window.location.href = 'indexsss.html';
    }

})