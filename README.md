# NutriDiaria 🥗

Aplicación móvil que ofrece una minuta nutricional semanal de recetas, pensada para que la dueña de casa pueda seleccionar y cocinar según el día. Desarrollada como actividad formativa del curso de Aplicaciones Móviles (DUOC UC).

## Descripción

NutriDiaria facilita el acceso a recetas saludables organizadas por día de la semana, con una interfaz simple e intuitiva orientada a usuarios con baja habilidad informática.

## Tecnologías

- **Lenguaje:** Kotlin
- **Framework:** Android Studio
- **UI Toolkit:** Jetpack Compose
- **Sistema de diseño:** Material Design 3

## Características

- **Login:** Ingreso con usuario y contraseña, con accesos a Registro y Recuperar contraseña.
- **Registro de usuario:** Formulario con nombre, selección de tipo de usuario (radio buttons) y aceptación de términos y condiciones (checkbox).
- **Recuperar contraseña:** Vista de recuperación de acceso.
- **Minuta semanal:** Listado de 5 recetas (Lunes a Viernes), cada una con ingredientes, preparación y recomendación nutricional. Incluye un selector desplegable (combo box) para filtrar la receta por día.

## Componentes UI utilizados

- `OutlinedTextField` — campos de texto (usuario, contraseña, nombre)
- `Button` / `TextButton` — acciones y vínculos de navegación
- `RadioButton` — selección de tipo de usuario
- `Checkbox` — aceptación de términos y condiciones
- `ExposedDropdownMenuBox` — combo box para selección de día
- `LazyColumn` / `Card` — listado de recetas
- `Scaffold` / `TopAppBar` — estructura y encabezado de cada pantalla

## Navegación

La app maneja la navegación entre vistas (Login, Registro, Recuperar contraseña y Minuta) mediante un estado controlado con un `enum class Pantalla`, sin uso de Navigation Compose en esta primera entrega.

## Estructura del proyecto

```
app/src/main/java/com/fereyesp/nutridiaria/
├── MainActivity.kt          # Pantallas y lógica de navegación
├── data/
│   └── Minuta.kt             # Modelo de datos de las recetas
└── ui/theme/                 # Tema de la aplicación (colores, tipografía)
```

## Cómo ejecutar el proyecto

1. Clonar el repositorio
2. Abrir la carpeta del proyecto en Android Studio
3. Esperar la sincronización de Gradle
4. Ejecutar en un emulador o dispositivo físico con Android

## Autor

Fernando Reyes — QA Automation Engineer / Estudiante de Ingeniería en Informática, DUOC UC
