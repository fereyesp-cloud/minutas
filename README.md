# NutriDiaria 🥗

- Aplicación móvil que ofrece una minuta nutricional semanal de recetas, con registro y autenticación de usuarios, pensada para que la dueña de casa pueda seleccionar y cocinar según el día. 
Desarrollada como actividad del curso de Aplicaciones Móviles (DUOC UC), utilizando Kotlin y Jetpack Compose.
## Descripción

- NutriDiaria facilita el acceso a recetas saludables organizadas por día de la semana, con una interfaz simple e intuitiva orientada a usuarios con baja habilidad informática.
Permite registrar una cuenta, iniciar sesión, explorar recetas filtrando por día o ingrediente, ver el detalle completo de cada receta y compartirla con otras apps. Toda la información de usuarios 
y recetas se persiste en una base de datos local (Room / SQLite), y los datos básicos de sesión se mantienen con SharedPreferences, por lo que la app recuerda al usuario aunque se cierre y se vuelva a abrir.

## Tecnologías

- **Lenguaje:** Kotlin
- **Framework:** Android Studio
- **UI Toolkit:** Jetpack Compose
- **Sistema de diseño:** Material Design 3
- **Navegacion:** Navigation Compose
- **Persistencia:** Room (SQLite) + SharedPreferences

## Funcionalidades

- **Login:** : Autenticación contra la base de datos (Room), con validación real de credenciales, tono de confirmación al ingresar correctamente, vibración al fallar, y guardado de la sesión en SharedPreferences.
- **Registro de usuario:** Formulario con nombre, usuario, contraseña (con confirmación), tipo de usuario (radio buttons), aceptación de términos (checkbox) y preferencia de notificaciones (switch). 
Valida campos obligatorios y coincidencia de contraseñas mediante una función de orden superior (validarCampo), e inserta el nuevo usuario directamente en la base de datos.
- **Recuperar contraseña:** Valida formato de correo (función de extensión) y existencia del usuario en la base de datos, mostrando confirmación visual mediante modal.
- **Minuta semanal:** Listado de recetas obtenido desde la base de datos, filtrable por día (combo box) y por ingrediente (buscador de texto), con contador de resultados y sugerencia de receta según el momento del día (desayuno / almuerzo / once, calculado con la hora del dispositivo).
- **Detalle de receta:**  Vista individual (consultada por título en la base de datos) con ingredientes, preparación y recomendación nutricional completos, con opción de compartir la receta a otras aplicaciones mediante Intent.ACTION_SEND.
- **Mi perfil:** Muestra los datos del usuario autenticado, accesible desde un menú desplegable junto con la opción de cerrar sesión.
- **Administración de usuarios (solo admin):**Un usuario administrador sembrado por defecto en la base de datos (no se puede crear desde el formulario de registro) puede listar, editar y eliminar usuarios, con diálogo de confirmación antes de eliminar.
- **Sesión persistente**Al iniciar sesión, los datos básicos (nombre de usuario y si es administrador) se guardan en SharedPreferences. Si la app se cierra y se vuelve a abrir sin haber cerrado sesión explícitamente, se salta la pantalla de login y se entra directo a la minuta.

## Persistencia de datos (Room)

- La app reemplazó el manejo de datos en memoria (arrayOf / mutableStateListOf) por una base de datos local con Room

- **Entidades (@Entity):** Usuarios (incluye el campo esAdmin: Boolean) y Minuta, cada una mapeada a una tabla (usuarios, minutas) con clave primaria autogenerada (@PrimaryKey(autoGenerate = true)).
- **DAOs (@Dao):** UsuarioDao y MinutaDao, con el CRUD completo: @Insert, @Query (buscar por credenciales, por día, por título), @Update y @Delete.
- **Base de datos (AppDatabase):** clase @Database que expone ambos DAO, implementada como singleton (companion object + @Volatile + synchronized) para asegurar una única instancia en toda la app.
- **Carga inicial de datos:**en MainActivity.onCreate, se verifica si la tabla de minutas está vacía y, de ser así, se insertan las recetas semilla; de la misma forma, se siembra un usuario administrador por defecto (admin / admin123) si aún no existe.

- Todas las operaciones contra la base de datos son funciones suspend, ejecutadas desde Composables mediante rememberCoroutineScope() / LaunchedEffect, evitando bloquear el hilo principal.

## Sesión con SharedPreferences

- La clase SesionPreferences (paquete preferences) centraliza el manejo de los datos básicos de sesión:

- **guardarSesion(nombre, esAdmin):**se llama al iniciar sesión correctamente, guardando el nombre de usuario y si es administrador.
- **obtenerNombreUsuario() / esAdmin():**permiten consultar la sesión guardada al abrir la app.
- **cerrarSesion()**borra los datos guardados cuando el usuario cierra sesión desde el menú.

- En NutriDiarioApp(), el NavHost calcula su startDestination consultando SesionPreferences: si existe una sesión guardada, la app arranca directo en la minuta del usuario; si no, arranca en el login.

## Extensiones KTX utilizadas

- Como refuerzo del feedback recibido, se detalla explícitamente el uso de las extensiones KTX del proyecto:

- **core-ktx:** entrega funciones de extensión de Kotlin sobre las APIs base de Android, simplificando el código en comparación con la API de Java tradicional.
- **lifecycle-runtime-ktx:** habilita lifecycleScope, usado en MainActivity.onCreate para lanzar la corrutina que carga las recetas y el usuario admin en la base de datos sin bloquear el ciclo de vida de la Activity.
- **androidx.room:room-ktx:** permite declarar las funciones de los DAO como suspend fun, integrando Room de forma natural con corrutinas de Kotlin en toda la capa de datos.

## Componentes UI utilizados

- **OutlinedTextField, Button, TextButton, IconButton** — inputs y acciones
- **RadioButton, Checkbox, Switch** — selección y preferencias
- **ExposedDropdownMenuBox, DropdownMenu** — combo box y menú desplegable
- **LazyColumn / Card** — listado de recetas
- **AlertDialog con íconos y colores diferenciados** (éxito/error)
- **Scaffold / TopAppBar** — estructura y encabezado de cada pantalla

## Conceptos de Kotlin aplicados

- **Data classes:** Minuta y Usuarios, ambas como entidades Room, con propiedad calculada (cantidadIngredientes) y método privado de normalización de texto en Minuta.
- **Colecciones:** uso de find, filter, count, any sobre las listas obtenidas desde Room.
- **Funciones de orden superior:** validarCampo, reutilizada para validar nombre, contraseña y correo con distintas reglas.
- **Funciones de extensión:** String.esCorreoValido().
- **Manejo de excepciones:** funciones centralizadas reproducirTonoExito() y vibrarError() (archivo Utiles.kt), con try/catch aplicado de forma consistente en Login, Registro y Recuperar contraseña.
- **Expresión when con rangos:** cálculo del momento del día según la hora del sistema.
- **Modificadores de visibilidad:** función privada dentro de Minuta.
- **Corrutinas:** suspend fun en los DAO, rememberCoroutineScope() / LaunchedEffect en las pantallas y lifecycleScope en MainActivity.

## Navegación

- La app utiliza Navigation Compose con un NavHost y rutas (login, registro, recuperar, minuta/{nombre}, perfil/{nombre}, receta/{titulo}, admin), incluyendo paso de argumentos entre pantallas (nombre de usuario,
título de receta codificado con Uri.encode) y un startDestination dinámico según si hay sesión guardada.

## Pruebas unitarias

Se implementaron pruebas unitarias con **JUnit**, ubicadas en `app/src/test/java/com/fereyesp/nutridiaria/`, cubriendo lógica propia de la aplicación sin depender del emulador ni de Android:

- **`MinutaTest`:** valida el método `contieneIngredientes()` (incluyendo sensibilidad a mayúsculas y casos sin coincidencia) y la propiedad calculada `cantidadIngredientes` de la entidad `Minuta`.
- **`ValidarCampoTest`:** prueba la función de orden superior `validarCampo()` con distintas reglas de validación (nombre obligatorio, longitud mínima de contraseña), verificando tanto el caso válido como el de error.
- **`EsCorreoValidoTest`:** prueba la función de extensión `String.esCorreoValido()` con correos válidos, sin arroba, sin dominio y vacíos.

En total, 12 tests que corren en segundos sobre la JVM local (`./gradlew testDebugUnitTest`), sin necesidad de levantar un emulador.

## Estructura del proyecto

```
app/src/main/java/com/fereyesp/nutridiaria/
├── MainActivity.kt                 # Punto de entrada, siembra de recetas y usuario admin en Room
├── data/
│   └── local/                      # Persistencia con Room
│       ├── Minuta.kt                # Entidad de receta
│       ├── MinutaDao.kt             # DAO de recetas (CRUD completo)
│       ├── Usuarios.kt              # Entidad de usuario (incluye esAdmin)
│       ├── UsuarioDao.kt            # DAO de usuarios (CRUD completo)
│       └── AppDatabase.kt           # Base de datos Room (singleton)
├── preferences/
│   └── SesionPreferences.kt        # Manejo de sesión con SharedPreferences
└── ui/screen/
    ├── NutriAPP.kt                  # NavHost y rutas
    ├── Login.kt
    ├── Registro.kt
    ├── Recuperar.kt
    ├── Home.kt                      # Minuta, SelectorDia, momento del día
    ├── PantallaReceta.kt            # Detalle de receta
    ├── MiPerfil.kt
    ├── PantallaAdmin.kt             # Administración de usuarios (solo admin)
    ├── Utiles.kt                    # Funciones centralizadas (tono, vibración)
    └── ui/theme/                    # Tema de la aplicación

## Cómo ejecutar el proyecto

- Clonar el repositorio
- Abrir la carpeta del proyecto en Android Studio
- Esperar la sincronización de Gradle (incluye KSP para el procesamiento de anotaciones de Room)
- Ejecutar en un emulador o dispositivo físico con Android 7.0 (API 24) o superior
- Para acceder a la administración de usuarios, iniciar sesión con el usuario sembrado por defecto: admin / admin123
- ## Autor

- Fernando Reyes — QA Automation Engineer / Estudiante de Ingeniería en Informática, DUOC UC
