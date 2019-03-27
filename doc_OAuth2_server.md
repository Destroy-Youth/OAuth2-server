# Documentacion del Servidor OAuth2
***
*Creado:* 26/03/2019
***
## Indice
1. Componentes
3. funcionamiento del servidor de configuración
4. Funcionamiento de los servicios REST
5. Funcionamiento del cliente HTML
***
## Componentes
La carpeta principal del proyecto esta compuesta por seis carpetas que contienen los archivos generados durante el desarrollo, estas carpetas son:

* ConfigurationServer
* Diagramas de secuencia
* Documentacion
* Liquibase (contiene los archivos usados para la elaboración de la base de datos)
* Server (contiene los archivos que conforman el servicio REST)
***
## Funcionamiento del servidor de configuración
***
## Funcionamiento del servicio REST
### Composición
Dentro de la carpeta server del proyecto principal y en la ruta `OAuth2-server\server\security\src\main\java\com\axity\security` se pueden encontrar las carpetas con las capas que contienen los elementos de nuestro servicio rest.
| Capa | Contenido |
| ------ | ------ |
| Commons | contiene las clases TO necesarios para el servicio asi como un Aspect para el control de errores probables durante el login. |
| Model | contiene la clase DO utilizada para mapear e interactuar con la base de datos que esta en uso |
| Persistence | contiene la interface usada para la interacción con la base de datos y la autenticación de los campos de un usuario |
| Services | contiene toda la logica de negocios que usan los microservicios de login a base de datos, de creación de token, el facade que expone los microservios y el design pattern implementado (strategy)|
| Web | contiene los controladores mediante los cuales la pagina de html donde el usuario puede interactuar con los servicios |

### Funcionamiento 

La clase Login controller de la capa web mediante la URI "/login" y la introduccion de los datos "name" y "password" inicia el proceso de logeo a la aplicacion, ambos datos entran en un jason que se convierte en un objeto UserTO el cual es requerido por el metodo login expuesto por el facade e, el cual regresa un token en jason como ResponseEntity.

El metodo login internamente tiene un if que por el momento evalua una futura opcion con un unico valor, este valor sirve para poder seleccionar entre las clases que implementan la interface ConnectionStrategy, dependiendo de este se puede usar una clase u otra para logearse en un determinado servicio, el servicio de login esta planeado para jira y myAxity pero por el momento la unica opcion disponible es la de la conexión con base de datos.
desde facade se accede a los metdodos necesarios para hacer todo eso, se trata de conservar el principio de responsabilidad unica al delegar una sola accion a cada metodo, facade envia un userTO a loginService para que este use otro metodo obtenga el nombre y la contraseña que este contiene y de esta manera usar los datos para enviarlos al metodo de authentication para que este nos regrese un userDO, este userDO se envia hasta facade donde este usa el nombre de userDO para generar un token mediante la llamada al metodo createToken que tiene como parametros un String nombre y un tiempo de vida en segundos.
al final del metodo facade se envia el token generado en JWT para que sea enviado por el servicio rest al cliente y este pueda usar el token para los redirect.

***
## Funcionamiento del cliente HTML
***
