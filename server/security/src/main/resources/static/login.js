function check(form)
{
    fetch("http://localhost:9090/hello?name="+form.user.value+"&pwd="+form.pwd.value)
    .then(response => response.text())
    .then(data => {
        alert(data)  
        console.log(data) // Prints result from `response.json()` in getRequest
    })
    .catch(error => console.error(error))
//  if(form.user.value == null || form.pwd.value == null)
//   {
//     alert("Password and Username Not Filled")
//   }
//   else if(form.user.value == 'e' && form.pwd.value == 'e')
//   {
//     alert("Password and Username Correct")    
//   }
//  else
//  {
//    alert("Error Password or Username")
//   }
}