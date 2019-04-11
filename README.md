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

## Tech
* [Springboot]()
* [SpringCloud]()
* [Hibernate]()

## Componentes
La carpeta principal del proyecto esta compuesta por seis carpetas que contienen los archivos generados durante el desarrollo, estas carpetas son:

* ConfigurationServer
* Diagramas de secuencia
* Documentacion
* Liquibase (contiene los archivos usados para la elaboración de la base de datos)
* Server (contiene los archivos que conforman el servidor OAuth2)
***
## Funcionamiento del servidor de configuración

El propósito del servidor de configuraciones es aprovisionar al servidor OAuth2 y a las aplicaciones que hagan uso del mismo con archivos de configuración guardados en el servidor de configuraciones o en un repositorio en línea de Git.

Existen casos en los que distintos microservicios consumen la misma base de datos, por lo cual es necesaria la misma configuración en todos ellos. Si todos requieren del mismo archivo este puede ser creado una vez y ser despachado por el servidor de configuración.

El servidor se configura en el archivo `ConfigurationServer/src/main/resources/application.yaml`

Tiene dos implementaciones:
* Una permite servir los archivos almacenados en el mismo servidor, en su carpeta de resources/config.
* La otra le indoca al servidor que los recursos se encuentran en un repositorio de Git (en este caso un repositorio en GitHub)

### Cliente del servidor de configuraciones
Al usar SpringCloud, la configuración de la aplicación cambia, ya que al iniciar un programa generado con SpringBoot el contexto de Spring se encarga de configurar la aplicación con las especificaciones dadas en el archivo `aplication.yaml` ó `aplication.properties`. Ahora en vez de que directamente el contexto de Spring configure la aplicación, se tiene un bootstrap que, dependiendo de la configuración del archivo `bootstrap.yaml` (contiene la dirección del servidor que contiene la configuración de la aplicación), decide cómo se va a configurar el contexto de la aplicación a partir del recurso solicitado al servidor de configuración. En otras palabras, la configuración de la aplicación ya no es responsabilidad de esta misma.

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
Para el apartado del cliente se diseñaron recursos estaticos para el projecto, los cuales se componen de:
- Archivo HTML
- Archivo CSS
- Archivo JavaScript
- Imagen en formato PNG

### Archivo HTML

Para el apartado del Front se creo un login con HTML5, el cual contiene las cosas basicas para su uso.
- Inputs para el usuario y la contraseña
- Un boton para Acceder
- Un logo

### Archivo CSS

Este archivo contiene lo que son los estilos para la pagina.
Principalmente se utiliza lo que es FlexBox para acomodar los elementos dentro de la pagina.

### Archivo JavaScript
Este javascript es el que contiene la logica para consumir el servicio del login.
Aqui se le da logica al boton de login, para poder obtener los datos de los inputs(Usuario y Password), los convierte en un json y los prepara para llamar al servicio rest
Para consumir el servicio rest se utiliza la funcion fetch.
Para mas informacion de la funcion fetch:[Using Fetch](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch).
Al final al recibir la respuesta, en caso de se exitosa, se obtiene el token del servicio y se redirecciona a otra pagina web con el token(Actualmente solo redirecciona a la pagina de Google con el token como un parametro). En caso contrario se le manda un mensaje al usuario atravez de una alerta.

