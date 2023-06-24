# Descripción del proyecto

El proyecto consiste en una aplicación de análisis de datos de tweets relacionados con la Fórmula 1. La aplicación carga los datos de un archivo CSV y un archivo de texto, y proporciona diferentes funcionalidades para obtener información útil a partir de los datos.

## Carga de datos

La aplicación carga dos tipos de datos para su análisis:

1. **Archivo CSV**: El archivo CSV contiene información sobre los tweets relacionados con la Fórmula 1. Se espera que el archivo esté ubicado en la ruta "src/main/resources/f1_dataset_test.csv". Este archivo contiene detalles como el contenido del tweet, la fecha, los hashtags asociados, etc.

2. **Archivo de texto**: El archivo de texto contiene información sobre los pilotos activos en la Fórmula 1. Se espera que el archivo esté ubicado en la ruta "src/main/resources/drivers.txt". Cada línea del archivo debe contener el nombre de un piloto.

## Funcionalidades

La aplicación proporciona las siguientes funcionalidades:

1. Listar los 10 pilotos activos más mencionados en los tweets.
2. Mostrar el top 15 de usuarios con más tweets.
3. Calcular la cantidad de hashtags distintos para un día dado.
4. Identificar el hashtag más utilizado para un día dado.
5. Mostrar el top 7 de cuentas con más favoritos.
6. Contar la cantidad de tweets que contienen una palabra o frase específica.

## Carga de datos

La clase `GetFilesInfo` se encarga de cargar los datos necesarios para la aplicación. A continuación, se describen las principales funciones de carga implementadas:

### Función `GetDriversInfo()`

Esta función carga información sobre los conductores desde un archivo de texto llamado `drivers.txt`. Utiliza un objeto `BufferedReader` para leer el archivo línea por línea y agrega cada línea, en minúsculas, a una lista enlazada llamada `driversLinkedList`.

### Función `formatDate(String dateString)`

Esta función recibe una cadena de fecha y la formatea en el formato deseado (YYYY-MM-DD). Divide la cadena en fecha y hora, extrae los componentes de la fecha y los reorganiza para obtener el formato deseado.

### Función `GetUsersInfo()`

Esta función carga información sobre los usuarios y sus tweets desde un archivo CSV llamado `f1_dataset_test.csv`. Utiliza un objeto `BufferedReader` y un `CSVParser` para leer y analizar el archivo CSV. Itera sobre cada registro del archivo, ignorando la primera línea, y procesa los datos para crear objetos `User` y `Tweets`. 

Dentro de cada registro, se extraen los diferentes campos, como la fecha, el ID del tweet, el contenido, los hashtags, la fuente y si es un retweet. Se formatea la fecha utilizando la función `formatDate()`. Los hashtags se separan y se crean objetos `Hashtag`, que se agregan a una lista enlazada. Luego, se crea un objeto `Tweets` y se le asignan los valores correspondientes.

Si el usuario ya existe en la lista `Users`, se obtiene una referencia al usuario existente y se agrega el tweet a su lista de tweets. De lo contrario, se crea un nuevo objeto `User` y se le asignan los valores correspondientes, incluido el tweet actual.
