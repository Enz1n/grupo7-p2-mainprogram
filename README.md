# Descripción del proyecto

El proyecto consiste en una aplicación de análisis de datos de tweets relacionados con la Fórmula 1. La aplicación carga los datos de un archivo CSV y un archivo de texto, y proporciona diferentes funcionalidades para obtener información útil a partir de los datos.

## Carga de datos

La aplicación carga dos tipos de datos para su análisis:

1. **Archivo CSV**: El archivo CSV contiene información sobre los tweets relacionados con la Fórmula 1. Se espera que el archivo esté ubicado en la ruta "src/main/resources/f1_dataset.csv". Este archivo contiene detalles como el contenido del tweet, la fecha, los hashtags asociados, etc.

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

## Reportes

### 1. `listarPilotosActivos(Scanner scanner)`

Esta función genera un reporte de los 10 pilotos activos más mencionados en los tweets. Utiliza una tabla hash (`MyHashTable`) para almacenar y contar las menciones de cada piloto. La lista enlazada (`MyLinkedList`) se utiliza para iterar sobre los tweets y los nombres de los pilotos. Luego, se utiliza un montículo (`MyHeap`) para obtener los pilotos más mencionados.

El código recorre los tweets y, para cada tweet, busca menciones de pilotos. Si se encuentra una coincidencia, se actualiza la cuenta de menciones en la tabla hash. Al final, se obtienen los 10 pilotos con más menciones y se muestran en pantalla.

### 2.`topUsuariosConMasTweets(Scanner scanner)`

Esta función genera un informe de los 15 usuarios con más tweets. Utiliza una estructura de datos de cola de prioridad (`PriorityQueue`) para almacenar y ordenar los usuarios según la cantidad de tweets que han publicado. Primero, se obtiene la lista de usuarios a partir del archivo CSV utilizando el método `getUsers` de la clase `Csv`. Luego, se itera sobre la lista de usuarios y se agrega cada usuario a la cola de prioridad, asignando la prioridad según el tamaño de su lista de tweets.

Después de que todos los usuarios se agregan a la cola de prioridad, se realiza un bucle para obtener los 15 usuarios con más tweets. En cada iteración, se extrae el usuario con mayor prioridad de la cola (es decir, el usuario con más tweets) utilizando el método `dequeueRight`. Se muestra en pantalla la posición, nombre del usuario y la cantidad de tweets que ha publicado.

**Nota:** Esta función puede lanzar una excepción `EmptyQueueException` si la cola de prioridad está vacía en el momento de la extracción del usuario con mayor prioridad.


### 3. `cantidadHashtagsDistintos(Scanner scanner)`

Esta función genera un reporte de la cantidad de hashtags distintos para un día dado. Utiliza una lista enlazada (`MyLinkedList`) para almacenar los hashtags encontrados y un contador para realizar el seguimiento de la cantidad. El código recorre los tweets y, para cada tweet, verifica si pertenece al día ingresado por el usuario. Si es así, busca hashtags únicos y los agrega a la lista enlazada. Al final, se muestra la cantidad de hashtags distintos en pantalla.

### 4. `hashtagMasUsado(Scanner scanner)`

Esta función genera un reporte del hashtag más utilizado para un día dado. Utiliza una tabla hash (`MyHashTable`) para contar la frecuencia de cada hashtag. El código recorre los tweets y, para cada tweet, verifica si pertenece al día ingresado por el usuario. Si es así, cuenta la aparición de cada hashtag en la tabla hash. Al final, se encuentra el hashtag con mayor frecuencia y se muestra en pantalla.

### 5. `topCuentasConMasFavoritos(Scanner scanner)`

Esta función genera un reporte de las 7 cuentas con más favoritos. Utiliza una lista enlazada (`MyLinkedList`) para almacenar los usuarios y un montículo (`MyHeap`) para ordenar los usuarios por la cantidad de favoritos. El código recorre la lista de usuarios y los agrega al montículo. Luego, se eliminan los 7 usuarios con más favoritos del montículo y se muestran en pantalla.

### 6. `contarTweetsConPalabraFrase(Scanner scanner)`

Esta función genera un reporte del número de tweets que contienen una palabra o frase específica. Utiliza una lista enlazada (`MyLinkedList`) para almacenar los tweets. El código recorre la lista de tweets y, para cada tweet, verifica si contiene la palabra o frase ingresada por el usuario. Si es así, se incrementa el contador. Al final, se muestra el número de tweets encontrados en pantalla.


Si el usuario ya existe en la lista `Users`, se obtiene una referencia al usuario existente y se agrega el tweet a su lista de tweets. De lo contrario, se crea un nuevo objeto `User` y se le asignan los valores correspondientes, incluido el tweet actual.


### Calcular Eficiencia

Todas las funciones mencionadas en este proyecto utilizan la función `calcularEficiencia` para medir y mostrar la eficiencia de su ejecución. Esta función toma como argumento un objeto `Runnable` que contiene el código a medir.

Al llamar a la función `calcularEficiencia`, se registra el tiempo de inicio antes de ejecutar el código y el tiempo de finalización después de ejecutarlo. Luego, se calcula la duración de ejecución y se muestra en segundos. Además, se obtiene la cantidad de memoria RAM total y la memoria utilizada por el código y se muestra en bytes.

El uso de la función `calcularEficiencia` permite evaluar el rendimiento de cada función y obtener información sobre su consumo de memoria y tiempo de ejecución.

