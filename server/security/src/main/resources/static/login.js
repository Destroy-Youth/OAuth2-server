var url="http://localhost:9090/auth/login";
 
function check(form)
{
    // METODO POR GET
    // fetch("http://localhost:9090/hello?name="+form.user.value+"&pwd="+form.pwd.value)
    // .then(response => response.text())
    // .then(data => {
    //     alert(data)  
    //     console.log(data) // Prints result from `response.json()` in getRequest
    // })
    // .catch(error => console.error(error))    

    // METODO POR POST
    if(form.user.value=="" || form.pwd.value==""){
        alert("Campos no llenados");
    }else{
        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            headers: new Headers({
                    'Content-Type': 'application/json', // <-- Specifying the Content-Type
                }),
            body: JSON.stringify({
                name: form.user.value,
                password: form.pwd.value
              }) // <-- Post parameters
            })  
        .then((response) => response.text())
        .then((response) => {
        alert(response);

        })
        .catch((error) => {
            console.error(error);
    });

    }
}