let logoutbutton = document.querySelector("#logout");
logoutbutton.addEventListener('click', async()=>
{
    let res = await fectch('http://localhot:8081/logout',
    {
        mehthod : 'post',
        credentials : 'include'
    })
    if(res.status === 200)
    {
        window.location.href = 'index.html'
    }
})