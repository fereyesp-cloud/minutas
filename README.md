# NutriDiaria 🥗

- Aplicación móvil que ofrece una minuta nutricional semanal de recetas, con registro y autenticación de usuarios, pensada para que la dueña de casa pueda seleccionar y cocinar según el día. 
Desarrollada como actividad del curso de Aplicaciones Móviles (DUOC UC), utilizando Kotlin y Jetpack Compose.

## Descripción

- NutriDiaria facilita el acceso a recetas saludables organizadas por día de la semana, con una interfaz simple e intuitiva orientada a usuarios con baja habilidad informática. 
Permite registrar una cuenta, iniciar sesión, explorar recetas filtrando por día o ingrediente, ver el detalle completo de cada receta y compartirla con otras apps.

## Tecnologías

- **Lenguaje:** Kotlin
- **Framework:** Android Studio
- **UI Toolkit:** Jetpack Compose
- **Sistema de diseño:** Material Design 3
- **Sistema de diseño:** Navigation Compose

## Funcionalidades

- **Login:** : Autenticación contra un array de usuarios en memoria, con validación real de credenciales, tono de confirmación al ingresar correctamente y vibración al fallar.
- **Registro de usuario:** Formulario con nombre, usuario, contraseña (con confirmación), tipo de usuario (radio buttons), aceptación de términos (checkbox) y preferencia de notificaciones (switch).
                           Valida campos obligatorios y coincidencia de contraseñas, y agrega el usuario nuevo al array en    tiempo de ejecución, permitiendo iniciar sesión con la cuenta recién creada.
- **Recuperar contraseña:** Valida formato de correo (función de extensión) y existencia del usuario en el sistema, mostrando confirmación visual mediante modal.
- **Minuta semanal:** Listado de recetas filtrable por día (combo box) y por ingrediente (buscador de texto), con contador de resultados y sugerencia de receta según el momento del día (desayuno / almuerzo / once / cena, calculado con la hora del dispositivo).
- **Detalle de receta:**  Vista individual con ingredientes, preparación y recomendación nutricional completos, con opción de compartir la receta a otras aplicaciones.
- **Mi perfil:** Muestra los datos del usuario autenticado, accesible desde un menú desplegable junto con la opción de cerrar sesión.


## Componentes UI utilizados

- **OutlinedTextField, Button, TextButton, IconButton** — inputs y acciones
- **RadioButton, Checkbox, Switch** — selección y preferencias
- **ExposedDropdownMenuBox, DropdownMenu** — combo box y menú desplegable
- **LazyColumn / Card** — listado de recetas
- **AlertDialog con íconos y colores diferenciados** (éxito/error)
- **Scaffold / TopAppBar** — estructura y encabezado de cada pantalla

## Conceptos de Kotlin aplicados

- **Data classes:** Minuta y Usuarios, con propiedad calculada (cantidadIngredientes) y método privado de normalización de texto.
- **Colecciones:** arrayOf (recetas), mutableStateListOf (usuarios), uso de find, filter, count, any.
- **Funciones de orden superior:** validarCampo, reutilizada para validar nombre, contraseña y correo con distintas reglas.
- **Funciones de extensión:** String.esCorreoValido().
- **Manejo de excepciones:** funciones centralizadas reproducirTonoExito() y vibrarError() (archivo Utiles.kt), con try/catch aplicado de forma consistente en Login, Registro y Recuperar contraseña.
- **Expresión when con rangos:** cálculo del momento del día según la hora del sistema.
- **Modificadores de visibilidad:** función privada dentro de Minuta.

## Navegación

- La app utiliza Navigation Compose con un NavHost y rutas (login, registro, recuperar, minuta/{nombre}, perfil/{nombre}, receta/{titulo}), incluyendo paso de argumentos entre pantallas (nombre de usuario, título de receta codificado con Uri.encode).

## Estructura del proyecto

```
app/src/main/java/com/fereyesp/nutridiaria/
├── MainActivity.kt              # Punto de entrada, array de minutas
├── data/
│   ├── Minuta.kt                 # Modelo de receta
│   └── Usuarios.kt                # Modelo y array mutable de usuarios
└── ui/screen/
    ├── NutriAPP.kt                # NavHost y rutas
    ├── Login.kt
    ├── Registro.kt
    ├── Recuperar.kt
    ├── Home.kt                    # Minuta, SelectorDia, momento del día
    ├── PantallaReceta.kt          # Detalle de receta
    ├── MiPerfil.kt
    ├── Utiles.kt                  # Funciones centralizadas (tono, vibración)
    └── ui/theme/                  # Tema de la aplicación


## Cómo ejecutar el proyecto

1. Clonar el repositorio
2. Abrir la carpeta del proyecto en Android Studio
3. Esperar la sincronización de Gradle
4. Ejecutar en un emulador o dispositivo físico con Android

## Autor

Fernando Reyes — QA Automation Engineer / Estudiante de Ingeniería en Informática, DUOC UC
