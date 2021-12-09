
let username = document.querySelector('#myusername');
let password = document.querySelector("#mypassword");
let submitbutton = document.querySelector("#submitbutton");

console.log("gg",username.value, password.value)
let me ={
    'username': username.value,
    'password': password.value }

    console.log(username.value);

    let myform = new FormData();
    myform.append("username", username.value)
    myform.append("password", password.value);
     
    /*fetch('http://localhost:8081/login',{
        method: 'post',
        headers:{'Content-Type' : 'applicaton/json' },
        body : myform
    }).catch(error => { console.log(error)});*/

     /*fetch('http://locathost:8081/login',{
        method:'post',
        credential: true,
        headers:{'Content-Type' : 'applicaton/json' },
        body:JSON.stringify(username , username.value,
            password , password.value)
    }).catch (error =>{
        console.log(error)
    })*/

    submitbutton.addEventListener('click', buttonClickedToLogin);

    function buttonClickedToLogin()
    {
        login();
       
    }
async function login() {
    console.log("it sdd me");
        username = document.querySelector("#myusername");
        password = document.querySelector("#mypassword");
        console.log("usernamex value = ", username.value)
    try{
       let res = await fetch('http://localhost:8081/login' ,{
           method : "post",
           credentials : "include",
           body: JSON.stringify({
               username : username.value,
               password : password.value,
           })
        });
        let data = await res.json();
          console.log("status", res.status)
        if(res.status === 400){
            let myelement = document.createElement('p');
            let myelement2 = document.querySelector("#mydiv");
             myelement.innerText = data.message;
             myelement.style.color= 'red';
            myelement2.appendChild(myelement);

            window.location.href = 'indexsss.html';
        }
        window.location.href = 'admin.html';
        /*if(res.status === 200 )
        {
            if(data.userRole === 'regular')
            window.location.href="regular.html"
            else if(data.userRole === 'admin')
            window.location.href= "admin.html"
        }*/
        //
    } catch (err){
            console.log(err);
        }
}