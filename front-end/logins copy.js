
let username = document.querySelector('#myusername');
let password = document.querySelector("#mypassword");
console.log("usrname = ", username.value)
let submitbutton = document.querySelector("#submitbutton");

console.log(username.value, password.value)
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
    try{
       let res = await fetch('http://localhost:8081/login' ,{
           method : "post",
           credentials : "include",
           body: JSON.stringify({
               username : username.value,
               password : password.value,
           })
        });
        let myObject = await res.json();
        console.log(myObject);
    } catch (err){
            console.log(err);
        }
}