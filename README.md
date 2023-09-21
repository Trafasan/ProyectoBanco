# <img src="images/00_icono.png" width="25" height="25"> WDBank
En este repositorio puedes encontrar dos versiones de WDBank, una aplicación de banco con diferentes características en cada versión.

## listBank
En esta versión, todos los datos se guardan en listas, por lo que las modificaciones no se guardarán. Se puede ejecutar un método llamado datosIniciales() para poblar estas listas antes de la ejecución del programa.
### Entidades
- ___Persona:___ Se ha declarado como clase abstracta para impedir la creación de objetos de esta clase. Sus atributos son los siguientes:
    - **nombre:** Este atributo no puede contener números y su longitud debe ser mayor o igual a 3 caracteres.
    - **apellido:** Este atributo no puede contener números y su longitud debe ser mayor o igual a 3 caracteres.
    - **dni:** Se ha limitado el formato de este atributo para que cumpla el formato del número del DNI.
    - **password:** Este atributo contiene una cadena de cuatro números.
    - **correo:** Se ha limitado el formato de este atributo para que cumpla el formato del correo electrónico.
    - **mensajes:** Lista de objetos de clase _Mensaje_ donde se encuentran todos los mensajes enviados (en el caso de un _gestor_) o recibidos (en caso de un _cliente_) de la persona.
- ___Gestor:___ Presenta todos los atributos de la clase _Persona_ además de los siguientes:
    - **oficina:** Este atributo solo puede contener letras y espacios y su longitud debe ser mayor o igual a 3 caracteres.
    - **clientes:** Lista de objetos de clase _Cliente_ donde se encuentran todos los clientes del gestor.
- ___Cliente:___ Presenta todos los atributos de la clase _Persona_ además de los siguientes:
    - **gestor:** Objeto de la clase _Gestor_ ligado al cliente.
    - **cuentas:** Lista de objetos de clase _Cuenta_ donde se encuentran todas las cuentas del cliente.
    - **transferencias:** Conjunto de objetos de clase _Transferencia_ donde se encuentran todas las tranferencias que involucran cuentas del cliente.
- Mensaje:___ Presenta los siguientes atributos:
    - **id:** Número entero (_int_) que identifica la transferencia. Este número se genera al crear un objeto de esta clase.
    - **gestor:** Objeto de clase _Gestor_ que establece el gestor que envía el mensaje.
    - **cliente:** Objeto de clase _Cliente_ que establece el cliente que recibe el mensaje.
    - **concepto:** Cadena que detalla el concepto del mensaje.
    - **texto:** Cadena que detalla el cuerpo del mensaje.
    - **fecha:** Fecha y hora en la cual se realizó la transferencia.
    - **leido:** Dato de tipo _boolean_ que indica si el cliente ha leído o no el mensaje. Este dato será falso por defecto.
- ___Cuenta:___ Presenta los siguientes atributos:
    - **id:** Número entero (_int_) que identifica la cuenta. Este número se genera al crear un objeto de esta clase.
    - **cliente:** Objeto de clase _Cliente_ ligado a la cuenta.
    - **saldo:** Número decimal (_double_) que especifica el saldo de la cuenta. El valor por defecto es 1000.
- ___Transferencia:___ Presenta los siguientes atributos:
    - **id:** Número entero (_int_) que identifica la transferencia. Este número se genera al crear un objeto de esta clase.
    - **cuenta_ordenante:** Objeto de clase _Cuenta_ que establece la cuenta ordenante de la transferencia.
    - **cuenta_beneficiaria:** Objeto de clase _Cuenta_ que establece la cuenta beneficiaria de la transferencia.
    - **importe:** Número decimal (_double_) que especifica el importe de la transferencia.
    - **concepto:** Cadena que detalla el concepto de la tranferencia. Este atributo puede ser nulo.
    - **fecha:** Fecha y hora en la cual se realizó la transferencia.
### Funcionamiento
Para empezar, se abrirá la pestaña del menú principal, donde puede elegir las siguientes opciones:
- **Consultar gestores:** Puedes consultar un gestor buscando su DNI, todos los gestores de una oficina o todos los gestores registrados.
- **Login:** Inicia sesión en su cuenta de gestor o cliente con tu DNI y contraseña.
- **Registro:** Crea una cuenta de gestor o cliente. En ambas cuentas se deberán rellenar los datos personales, pero en el caso de un gestor, además, deberá introducir la oficina donde trabaja. Para el caso de un cliente, se le asignará un gestor aleatoriamente.

Cuando se registre o inicie sesión, se le redigirá a su perfil, donde encontrará las siguientes opciones:
- **Ver tus datos:** Imprime por consola todos los datos de la persona.
- **Actualizar cuenta:** Actualiza cualquiera de los atributos del objeto.
- **Borrar cuenta:** Borra la cuenta junto con otras acciones que varían en función de la cuenta:
    - En el caso de la cuenta de un gestor, cambia de gestor a todos sus clientes y les manda un mensaje para avisarle de dicho cambio.
    - En el caso de la cuenta de un cliente, lo elimina de la lista de clientes del gestor.

Además, se encontrará con diferentes opciones dependiendo de su tipo de cuenta:
- Para las cuentas de gestores, las demás opciones son las siguientes:
    - **Ver lista de clientes:** Imprime los datos principales de los clientes del gestor. Si el gestor no tiene clientes imprimirá un mensaje de error.
    - **Gestionar cliente:** Envía un mensaje o cambia de gestor a un cliente de su lista.
    - **Consultar mensajes enviados:** Imprime por consola todos los mensajes enviados por el gestor.
- Para las cuentas de clientes, las demás opciones son las siguientes:
    - **Consultar mensajes:** Imprime por consola el concepto de los mensajes y, si el cliente lo desea, imprime también todos los detalles de uno de los mensajes y lo marca como leído.
    - **Gestionar cuentas:** En esta opción, el cliente puede consultar sus cuentas (si tiene alguna), añadir o eliminar una cuenta.
    - **Gestionar transferencias:** En esta opción, el cliente puede consultar sus transferencias (si ha realizado alguna) tanto de forma resumida como detallada o enviar una transferecia si tiene alguna cuenta.
## dbBank
En esta versión, todos los datos se guardan en una base de datos MySQL, por lo que las modificaciones se guardarán. Este cambio afecta tanto a las entidades como al funcionamiento de la versión anterior.
### Tablas de la base de datos
- ___Gestor:___ Los cambios en la clase _Persona_ que afectan a esta clase hija han sido los siguientes:
    - **id:** Se ha añadido este atributo de tipo _int_ para identificar más fácilmente al gestor.
    - **apellido:** En esta versión, este atributo puede ser nulo.
    - **dni:** Se ha creado una clave única en la base de datos para que no puedan haber dos gestores con el mismo DNI.
    - **correo:** Se ha creado una clave única en la base de datos para que no puedan haber dos gestores con el mismo correo electrónico.
    **mensajes:** Este atributo se ha eliminado.
- ___Cliente:___ Aparte de los cambios presentados en la clase _Gestor_, se encuentra el siguiente:
    - **gestor:** En la base de datos, esta columna será el ID del gestor.
    - **cuentas:** Este atributo se ha eliminado.
    - **transferencias:** Este atributo se ha eliminado.
- Mensaje:___ Los cambios son los siguientes:
    - **gestor:** En la base de datos, esta columna será el ID del gestor.
    - **cliente:** En la base de datos, esta columna será el ID del cliente.
    - **concepto:** En esta versión, este atributo puede ser nulo.
    - **fecha:** Este dato se creará en la base de datos.
    - **leido:** En la base de datos, esta columna será de tipo _tinyint(1)_ y será 0 por defecto.
- ___Cuenta:___ El cambio realizado es el siguiente:
    - **cliente:** En la base de datos, esta columna será el ID del cliente.
- ___Transferencia:___ Los cambios son los siguientes:
    - **cuenta_ordenante:** En la base de datos, esta columna será el ID de la cuenta ordenante.
    - **cuenta_beneficiaria:** En la base de datos, esta columna será el ID de la cuenta beneficiaria.
    - **fecha:** Este dato se creará en la base de datos.
### Funcionamiento
El funcionamiento es el mismo que en la versión anterior salvo algunos detalles:
 - La consulta de un solo gestor se realiza introduciendo su ID en vez de su DNI.
- Cuando se borra un cliente, se eliminan los mensajes que ha recibido, todas sus cuentas y las transferencias que haya realizado entre sus cuentas o que la otra cuenta involucrada se haya eliminado.
- Si el cliente no tiene ninguna cuenta, no podrá acceder al menú de gestión de transferencias.