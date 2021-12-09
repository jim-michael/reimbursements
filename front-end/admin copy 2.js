window.addEventListener('load', async () => {

    let res = await fetch('http://localhost:8081/checkloginstatus', {
        credentials: 'include',
        method: 'GET'
    });

    
     if (res.status === 401) {
        window.location.href = 'index.html';
    }

    //populateTableWithAssignments();
});

// Logout btn
let logoutBtn = document.querySelector('#logout');

logoutBtn.addEventListener('click', async () => {

    let res = await fetch('http://localhost:8081/logout', {
        'method': 'POST',
        'credentials': 'include'
    });

    if (res.status === 200) {
        window.location.href = 'index.html';
    }

})
let approvedBtns = document.querySelector('#all');
approvedBtns.addEventListener('click',populateTableWithAssignmentsx);

async function populateTableWithAssignmentsx() {
    let res = await fetch('http://localhost:8081/admin/allreceipts', {
        credentials: 'include',
        method: 'GET'
    });
     console.log(res.status);
    let tbodyElement = document.querySelector("#assignment-table tbody");
    tbodyElement.innerHTML = '';
    let assignmentArray =  await res.json();
    let nn =assignmentArray.length
         console.log("lengh of array" , nn);
    for (let i = 0; i < assignmentArray.length; i++) {
        let assignment = assignmentArray[i];

        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        td1.innerHTML = assignment.id;
        console.log("assigmentid =" , assignment.id);
        let td2 = document.createElement('td');
        td2.innerHTML = assignment.type;

        let td3 = document.createElement('td');
        td3.innerHTML = assignment.amount;
        
        let td4 = document.createElement('td');
        td4.innerHTML = assignment.status; 

         // new lines are added
         let td5 = document.createElement('td');
         //let xx = assignment.timestamp * 1000;
         let d = new Date(assignment.timestamp);
             d.setTime(assignment.timestamp);
        d.setTime(assignment.timestamp);
        console.log("yaoo",assignment.timestamp);
        let s = d.getDate()+"/"+ d.getMonth()+"/"+d.getFullYear();
        console.log("s",s);
        td5.innerHTML = s;

        //let td5 = document.createElement('td');
        //td5.innerHTML = assignment.timestamp;  
        
        

       // if(assignment.status === 'pending'){
        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);
       // }


        //tr.appendChild(td1);
        //tr.appendChild(td2);
        //tr.appendChild(td3);
        //tbodyElement.appendChild(tr);
        /*let td3 = document.createElement('td'); // grade
        let td4 = document.createElement('td'); // grader id

        if (assignment.graderId === 0) {
            td3.innerHTML = assignment.grade;
            td4.innerHTML = assignment.graderId;
        } else {
            td3.innerHTML = 'Not graded';
            td4.innerHTML = '-';
        }
 

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.authorId;

        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);*/

        viewImageButton.addEventListener('click', async () => {
            // Add the is-active class to the modal (so that the modal appears)
            // inside of the modal on div.modal-content (div w/ class modal-content)
            //  -> img tag with src="http://localhost:8080/assignments/{id}/image"
            let assignmentImageModal = document.querySelector('#assignment-image-modal');

            // Close button
            let modalCloseElement = assignmentImageModal.querySelector('button');
            modalCloseElement.addEventListener('click', () => {
                assignmentImageModal.classList.remove('is-active');
            });

            // you can take an element and use querySelector on it to find the child elements
            // that are nested within it
            let modalContentElement = assignmentImageModal.querySelector('.modal-content');
            modalContentElement.innerHTML = '';

            let imageElement = document.createElement('img');
            //imageElement.setAttribute('src', `http://localhost:8081/assignments/${assignment.id}/image`);
            imageElement.setAttribute('src', `http://localhost:8081/client/getimage/${assignment.id}`);
            
            modalContentElement.appendChild(imageElement);

            assignmentImageModal.classList.add('is-active'); // add a class to the modal element to have it display
        });

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);

        tbodyElement.appendChild(tr);
    }
}
//=================================================================

let deniedBtn = document.querySelector('#denied');
 deniedBtn.addEventListener('click',populateTableWithAssignment);

async function populateTableWithAssignment() {
    let res = await fetch('http://localhost:8081/client/denied/receipts', {
        credentials: 'include',
        method: 'GET'
    });
     console.log(res.status);
    let tbodyElement = document.querySelector("#assignment-table tbody");
    tbodyElement.innerHTML = '';
    let assignmentArray =  await res.json();
    let nn =assignmentArray.length
         console.log("lengh of array" , nn);
    for (let i = 0; i < assignmentArray.length; i++) {
        let assignment = assignmentArray[i];

        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        td1.innerHTML = assignment.id;
        console.log("assigmentid =" , assignment.id);
        let td2 = document.createElement('td');
        td2.innerHTML = assignment.type;

        let td3 = document.createElement('td');
        td3.innerHTML = assignment.amount;
        
        let td4 = document.createElement('td');
        td4.innerHTML = assignment.status;  

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.timestamp;  
         
       // if(assignment.status === 'pending'){
        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);
       // }


        //tr.appendChild(td1);
        //tr.appendChild(td2);
        //tr.appendChild(td3);
        //tbodyElement.appendChild(tr);
        /*let td3 = document.createElement('td'); // grade
        let td4 = document.createElement('td'); // grader id

        if (assignment.graderId === 0) {
            td3.innerHTML = assignment.grade;
            td4.innerHTML = assignment.graderId;
        } else {
            td3.innerHTML = 'Not graded';
            td4.innerHTML = '-';
        }
 

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.authorId;

        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);*/

        viewImageButton.addEventListener('click', async () => {
            // Add the is-active class to the modal (so that the modal appears)
            // inside of the modal on div.modal-content (div w/ class modal-content)
            //  -> img tag with src="http://localhost:8080/assignments/{id}/image"
            let assignmentImageModal = document.querySelector('#assignment-image-modal');

            // Close button
            let modalCloseElement = assignmentImageModal.querySelector('button');
            modalCloseElement.addEventListener('click', () => {
                assignmentImageModal.classList.remove('is-active');
            });

            // you can take an element and use querySelector on it to find the child elements
            // that are nested within it
            let modalContentElement = assignmentImageModal.querySelector('.modal-content');
            modalContentElement.innerHTML = '';

            let imageElement = document.createElement('img');
            //imageElement.setAttribute('src', `http://localhost:8081/assignments/${assignment.id}/image`);
            imageElement.setAttribute('src', `http://localhost:8081/client/getimage/${assignment.id}`);
            
            modalContentElement.appendChild(imageElement);

            assignmentImageModal.classList.add('is-active'); // add a class to the modal element to have it display
        });

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);

        tbodyElement.appendChild(tr);
    }
}

//================================================================3
let pendingBtn = document.querySelector('#pending');
pendingBtn.addEventListener('click',populateTableWithAssignmentss);

async function populateTableWithAssignmentss() {
    let res = await fetch('http://localhost:8081/client/pending/receipts', {
        credentials: 'include',
        method: 'GET'
    });
     console.log(res.status);
    let tbodyElement = document.querySelector("#assignment-table tbody");
    tbodyElement.innerHTML = '';
    let assignmentArray =  await res.json();
    let nn =assignmentArray.length
         console.log("lengh of array" , nn);
    for (let i = 0; i < assignmentArray.length; i++) {
        let assignment = assignmentArray[i];

        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        td1.innerHTML = assignment.id;
        console.log("assigmentid =" , assignment.id);
        let td2 = document.createElement('td');
        td2.innerHTML = assignment.type;

        let td3 = document.createElement('td');
        td3.innerHTML = assignment.amount;
        
        let td4 = document.createElement('td');
        td4.innerHTML = assignment.status;  

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.timestamp;  
         
       // if(assignment.status === 'pending'){
        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);
       // }

       let td7 = document.createElement('td');
       let changeStatusButton = document.createElement('button');
       changeStatusButton.innerHTML= 'changeStatus';
       td7.appendChild(changeStatusButton);

       let td8 = document.createElement('td');
       let textBox = document.createElement('input');
       textBox.placeholder= " approved or denies";
       textBox.setAttribute("type","text");
       td8.appendChild(textBox);

       changeStatusButton.addEventListener('click',async () => {
          console.log("xxxxxxxxxxxxxx");
        console.log("assignment.id = ", assignment.id);
           let status = textBox.value;
           //console.log("x", x);
           let status1;
           let result1 = status.localeCompare('approved');
           let result2 = status.localeCompare("denied");
           if(result1 === 0)
            status1 ='approved';
           else if(result2 === 0)
            status1 = 'denied';
           console.log(status1)
           console.log("result1,result2", result1,result2);
           if(result1 == 0 || result2 == 0 )
           {
               console.log("statusxxx =", status);
        let res = await fetch(`http://localhost:8081/client/receipt/${assignment.id}/${status1}`, {
            credentials: 'include',
            method: 'put'
        });
        console.log(res.status);
        textBox.style.display="none";
        changeStatusButton.style.display="none";

    }
       });



        //tr.appendChild(td1);
        //tr.appendChild(td2);
        //tr.appendChild(td3);
        //tbodyElement.appendChild(tr);
        /*let td3 = document.createElement('td'); // grade
        let td4 = document.createElement('td'); // grader id

        if (assignment.graderId === 0) {
            td3.innerHTML = assignment.grade;
            td4.innerHTML = assignment.graderId;
        } else {
            td3.innerHTML = 'Not graded';
            td4.innerHTML = '-';
        }
 

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.authorId;

        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);*/

        viewImageButton.addEventListener('click', async () => {
            // Add the is-active class to the modal (so that the modal appears)
            // inside of the modal on div.modal-content (div w/ class modal-content)
            //  -> img tag with src="http://localhost:8080/assignments/{id}/image"
            let assignmentImageModal = document.querySelector('#assignment-image-modal');

            // Close button
            let modalCloseElement = assignmentImageModal.querySelector('button');
            modalCloseElement.addEventListener('click', () => {
                assignmentImageModal.classList.remove('is-active');
            });

            // you can take an element and use querySelector on it to find the child elements
            // that are nested within it
            let modalContentElement = assignmentImageModal.querySelector('.modal-content');
            modalContentElement.innerHTML = '';

            let imageElement = document.createElement('img');
            //imageElement.setAttribute('src', `http://localhost:8081/assignments/${assignment.id}/image`);
            imageElement.setAttribute('src', `http://localhost:8081/client/getimage/${assignment.id}`);
            
            modalContentElement.appendChild(imageElement);

            assignmentImageModal.classList.add('is-active'); // add a class to the modal element to have it display
        });

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        
        tbodyElement.appendChild(tr);
    }
}


//=================================================================4
let approvedBtn = document.querySelector('#approved');
approvedBtn.addEventListener('click',populateTableWithAssignmentsss);

async function populateTableWithAssignmentsss() {
    let res = await fetch('http://localhost:8081/client/approved/receipts', {
        credentials: 'include',
        method: 'GET'
    });
     console.log(res.status);
    let tbodyElement = document.querySelector("#assignment-table tbody");
    tbodyElement.innerHTML = '';
    let assignmentArray =  await res.json();
    let nn =assignmentArray.length
         console.log("lengh of array" , nn);
    for (let i = 0; i < assignmentArray.length; i++) {
        let assignment = assignmentArray[i];

        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        td1.innerHTML = assignment.id;
        console.log("assigmentid =" , assignment.id);
        let td2 = document.createElement('td');
        td2.innerHTML = assignment.type;

        let td3 = document.createElement('td');
        td3.innerHTML = assignment.amount;
        
        let td4 = document.createElement('td');
        td4.innerHTML = assignment.status;  

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.timestamp;  
         
       // if(assignment.status === 'pending'){
        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);
       // }


        //tr.appendChild(td1);
        //tr.appendChild(td2);
        //tr.appendChild(td3);
        //tbodyElement.appendChild(tr);
        /*let td3 = document.createElement('td'); // grade
        let td4 = document.createElement('td'); // grader id

        if (assignment.graderId === 0) {
            td3.innerHTML = assignment.grade;
            td4.innerHTML = assignment.graderId;
        } else {
            td3.innerHTML = 'Not graded';
            td4.innerHTML = '-';
        }
 

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.authorId;

        let td6 = document.createElement('td');
        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td6.appendChild(viewImageButton);*/

        viewImageButton.addEventListener('click', async () => {
            // Add the is-active class to the modal (so that the modal appears)
            // inside of the modal on div.modal-content (div w/ class modal-content)
            //  -> img tag with src="http://localhost:8080/assignments/{id}/image"
            let assignmentImageModal = document.querySelector('#assignment-image-modal');

            // Close button
            let modalCloseElement = assignmentImageModal.querySelector('button');
            modalCloseElement.addEventListener('click', () => {
                assignmentImageModal.classList.remove('is-active');
            });

            // you can take an element and use querySelector on it to find the child elements
            // that are nested within it
            let modalContentElement = assignmentImageModal.querySelector('.modal-content');
            modalContentElement.innerHTML = '';

            let imageElement = document.createElement('img');
            //imageElement.setAttribute('src', `http://localhost:8081/assignments/${assignment.id}/image`);
            imageElement.setAttribute('src', `http://localhost:8081/client/getimage/${assignment.id}`);
            
            modalContentElement.appendChild(imageElement);

            assignmentImageModal.classList.add('is-active'); // add a class to the modal element to have it display
        });

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);

        tbodyElement.appendChild(tr);
    }
}

//==================================================================4
// Submitting assignment
/*let assignmentSubmitButton = document.querySelector('#submit-assignment-btn');

assignmentSubmitButton.addEventListener('click', submitAssignment);

async function submitAssignment() {

    let assignmentNameInput = document.querySelector('#assignment-name');
    let assignmentImageInput = document.querySelector('#assignment-file');

    const file = assignmentImageInput.files[0];

    let formData = new FormData();
    formData.append('assignment_name', assignmentNameInput.value);
    formData.append('assignment_image', file);

    let res = await fetch('http://localhost:8081/assignments', {
        method: 'POST',
        credentials: 'include',
        body: formData
    });

    if (res.status === 201) { // If we successfully added an assignment
        populateTableWithAssignments(); // Refresh the table of assignments
    } else if (res.status === 400) {
        let assignmentForm = document.querySelector('#assignment-submit-form');

        let data = await res.json();

        let pTag = document.createElement('p');
        pTag.innerHTML = data.message;
        pTag.style.color = 'red';

        assignmentForm.appendChild(pTag);
    }
}*/