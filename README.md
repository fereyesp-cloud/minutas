# NutriDiaria 🥗

- Aplicación móvil que ofrece una minuta nutricional semanal de recetas, con registro y autenticación de usuarios, pensada para que la dueña de casa pueda seleccionar y cocinar según el día.
 Desarrollada como actividad del curso de Aplicaciones Móviles (DUOC UC), utilizando Kotlin y Jetpack Compose
## Descripción

- NutriDiaria facilita el acceso a recetas saludables organizadas por día de la semana, con una interfaz simple e intuitiva orientada a usuarios con baja habilidad informática. 
Permite registrar una cuenta, iniciar sesión, explorar recetas filtrando por día o ingrediente, ver el detalle completo de cada receta y compartirla con otras apps. 
Toda la información de usuarios y recetas se persiste en una base de datos local (Room / SQLite), por lo que los datos se mantienen aunque la app se cierre o el dispositivo se reinicie.

## Tecnologías

- **Lenguaje:** Kotlin
- **Framework:** Android Studio
- **UI Toolkit:** Jetpack Compose
- **Sistema de diseño:** Material Design 3
- **Navegacion:** Navigation Compose
- **Persistencia:** Room (SQLite)

## Funcionalidades

- **Login:** : Autenticación contra la base de datos (Room), con validación real de credenciales, tono de confirmación al ingresar correctamente y vibración al fallar.
- **Registro de usuario:** Formulario con nombre, usuario, contraseña (con confirmación), tipo de usuario (radio buttons), aceptación de términos (checkbox) y preferencia de notificaciones (switch). 
Valida campos obligatorios y coincidencia de contraseñas mediante una función de orden superior (validarCampo), e inserta el nuevo usuario directamente en la base de datos, permitiendo iniciar sesión de inmediato con la cuenta recién creada.
- **Recuperar contraseña:** alida formato de correo (función de extensión) y existencia del usuario en la base de datos, mostrando confirmación visual mediante modal.
- **Minuta semanal:** Listado de recetas obtenido desde la base de datos, filtrable por día (combo box) y por ingrediente (buscador de texto), con contador de resultados y sugerencia de receta según el momento del día (desayuno / almuerzo / once / cena, calculado con la hora del dispositivo).
- **Detalle de receta:**  Vista individual (consultada por título en la base de datos) con ingredientes, preparación y recomendación nutricional completos, con opción de compartir la receta a otras aplicaciones mediante Intent.ACTION_SEND.
- **Mi perfil:** Muestra los datos del usuario autenticado, accesible desde un menú desplegable junto con la opción de cerrar sesión.

## Persistencia de datos (Room)

- **Entidades (@Entity):** Usuarios y Minuta, cada una mapeada a una tabla (usuarios, minutas) con clave primaria autogenerada (@PrimaryKey(autoGenerate = true)).
- **DAOs (@Dao):** UsuarioDao y MinutaDao, con operaciones @Insert y @Query (por ejemplo, buscar usuario por credenciales, o recetas por día/título).
- **Base de datos (AppDatabase):** clase @Database que expone ambos DAO, implementada como singleton (companion object + @Volatile + synchronized) para asegurar una única instancia en toda la app.
- **Carga inicial de datos:** en MainActivity.onCreate, se verifica si la tabla de minutas está vacía y, de ser así, se insertan las recetas semilla una sola vez.

- Todas las operaciones contra la base de datos son funciones suspend, ejecutadas desde Composables mediante rememberCoroutineScope() / LaunchedEffect, evitando bloquear el hilo principal.

## Extensiones KTX utilizadas

- Como refuerzo del feedback recibido, se detalla explícitamente el uso de las extensiones KTX del proyecto:

- **core-ktx:** entrega funciones de extensión de Kotlin sobre las APIs base de Android, simplificando el código en comparación con la API de Java tradicional.
- **lifecycle-runtime-ktx:** habilita lifecycleScope, usado en MainActivity.onCreate para lanzar la corrutina que carga las recetas semilla en la base de datos sin bloquear el ciclo de vida de la Activity.
- **androidx.room:room-ktx:** permite declarar las funciones de los DAO como suspend fun (en vez de usar callbacks o LiveData), integrando Room de forma natural con corrutinas de Kotlin en toda la capa de datos.

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
- **Manejo de excepciones:** unciones centralizadas reproducirTonoExito() y vibrarError() (archivo Utiles.kt), con try/catch aplicado de forma consistente en Login, Registro y Recuperar contraseña.
- **Expresión when con rangos:** álculo del momento del día según la hora del sistema.
- **Modificadores de visibilidad:** función privada dentro de Minuta.
- **Corrutinas:** suspend fun en los DAO, rememberCoroutineScope() / LaunchedEffect en las pantallas y lifecycleScope en MainActivity.

## Navegación

- La app utiliza Navigation Compose con un NavHost y rutas (login, registro, recuperar, minuta/{nombre}, perfil/{nombre}, receta/{titulo}), incluyendo paso de argumentos entre pantallas (nombre de usuario, título de receta codificado con Uri.encode).

## Estructura del proyecto

```
app/src/main/java/com/fereyesp/nutridiaria/
├── MainActivity.kt              # Punto de entrada, carga inicial de recetas en Room
├── data/
│   ├── Minuta.kt                 # Entidad Room de receta
│   ├── MinutaDao.kt              # DAO de recetas
│   ├── Usuarios.kt               # Entidad Room de usuario
│   ├── UsuarioDao.kt             # DAO de usuarios
│   └── AppDatabase.kt            # Base de datos Room (singleton)
└── ui/screen/
    ├── NutriAPP.kt               # NavHost y rutas
    ├── Login.kt
    ├── Registro.kt
    ├── Recuperar.kt
    ├── Home.kt                   # Minuta, SelectorDia, momento del día
    ├── PantallaReceta.kt         # Detalle de receta
    ├── MiPerfil.kt
    ├── Utiles.kt                 # Funciones centralizadas (tono, vibración)
    └── ui/theme/                 # Tema de la aplicación

## Cómo ejecutar el proyecto

1. Clonar el repositorio
2. Abrir la carpeta del proyecto en Android Studio
3. Esperar la sincronización de Gradle (incluye KSP para el procesamiento de anotaciones de Room)
4. Ejecutar en un emulador o dispositivo físico con Android 7.0 (API 24) o superior

## Autor

Fernando Reyes — QA Automation Engineer / Estudiante de Ingeniería en Informática, DUOC UC
