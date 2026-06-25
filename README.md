# Lista de Tareas — Android App

> Aplicación móvil Android para gestionar tareas personales y académicas. Gratuita, offline y en español.

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![API](https://img.shields.io/badge/API_24%2B-378ADD?style=for-the-badge&logo=android&logoColor=white)
![Estado](https://img.shields.io/badge/Estado-En_desarrollo-EF9F27?style=for-the-badge)

---

## Problema que resuelve

Los estudiantes universitarios de Quito gestionan múltiples actividades académicas y personales sin contar con una herramienta gratuita, en español y que funcione sin internet. Esta app resuelve ese problema ofreciendo un gestor de tareas **offline**, simple y adaptado al contexto local.

---

## Objetivo

Desarrollar una aplicación Android que permita a los usuarios crear, visualizar, editar, eliminar y marcar tareas como completadas, mejorando su organización personal sin necesidad de conexión a internet.

---

## Historias de usuario del MVP

| ID | Historia de usuario | Estado |
|---|---|---|
| `HU1` | Como usuario quiero **crear** una tarea para organizar mis actividades | Pendiente |
| `HU2` | Como usuario quiero **visualizar** mis tareas para controlar pendientes | Pendiente |
| `HU3` | Como usuario quiero **editar** tareas cuando sea necesario | Pendiente |
| `HU4` | Como usuario quiero **eliminar** tareas que ya no necesito | Pendiente |
| `HU5` | Como usuario quiero **marcar** tareas como completadas | Pendiente |

---

## Funcionalidades implementadas

- [x] Registro de usuario con Firebase Authentication
- [x] Login con correo y contraseña
- [x] Validación de formulario (correo con regex, contraseña mínimo 6 caracteres)
- [x] Manejo de errores de Firebase traducidos al español
- [x] Navegación automática a pantalla principal si ya hay sesión activa
- [x] Bloqueo del botón Atrás para evitar volver al login tras autenticarse

---

## Tecnología utilizada

- **Kotlin** — Lenguaje oficial de Android
- **Android Studio Meerkat 2024.3.1** — IDE oficial de Google
- **Firebase Authentication** — Gestión de usuarios y sesiones
- **Room (SQLite)** — Almacenamiento local offline de tareas
- **Material Design 3** — Componentes de interfaz de usuario
- **MinSDK API 24** — Android 7.0+ (cubre el 94% de dispositivos)
- **Git + GitHub** — Control de versiones

---

## Instrucciones de instalación

1. Clona el repositorio:
```bash
git clone https://github.com/domedefaz/lista-de-tareas-android.git
```

2. Abre el proyecto en **Android Studio**

3. Agrega tu propio archivo `google-services.json` dentro de la carpeta `app/` (no incluido por seguridad)

4. Espera que Gradle sincronice los archivos

5. Ejecuta en un emulador **Pixel 9 con API 37** o en un dispositivo físico con Android 7.0+

---

## Autenticación

La aplicación usa **Firebase Authentication** con el método de correo y contraseña:

- Al abrir la app, se verifica si ya existe una sesión activa
- Si no hay sesión, se muestra la pantalla de **Login**
- Los usuarios nuevos pueden **registrarse** desde la misma pantalla
- Las validaciones de formulario evitan envíos con datos incorrectos antes de consultar a Firebase
- Los errores de Firebase se traducen a mensajes claros en español

---

## Capturas de pantalla

| Login | Registro | Validación de error |
|---|---|---|
| _Ver carpeta /capturas_ | _Ver carpeta /capturas_ | _Ver carpeta /capturas_ |

---

## Estado actual del proyecto

| Hito | Estado |
|---|---|
| Entorno de desarrollo configurado | Completado |
| Hola Mundo corriendo en emulador | Completado |
| Repositorio GitHub creado | Completado |
| Prototipo de alta fidelidad en Figma | Completado |
| Firebase Authentication implementado | Completado |
| Pantallas de login y registro funcionales | Completado |
| Desarrollo de funcionalidades MVP (CRUD de tareas) | En progreso |

---

## Autora

**Doménica Defaz**  
Universidad Central del Ecuador — 2026  
Metodología de la Investigación
