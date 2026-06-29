# Lista de Tareas — Android App

> Aplicación móvil Android para gestionar tareas personales y académicas. Gratuita, offline y en español.

## Problema que resuelve

Los estudiantes universitarios de Quito gestionan múltiples actividades académicas y personales sin contar con una herramienta gratuita, en español y que funcione sin internet. Esta app resuelve ese problema ofreciendo un gestor de tareas **offline**, simple y adaptado al contexto local.

---

## Objetivo

Desarrollar una aplicación Android que permita a los usuarios crear, visualizar, editar, eliminar y marcar tareas como completadas, mejorando su organización personal sin necesidad de conexión a internet.

---

## Historias de usuario del MVP

| ID | Historia de usuario | Estado |
|---|---|---|
| `HU1` | Como usuario quiero **crear** una tarea para organizar mis actividades |  Completado |
| `HU2` | Como usuario quiero **visualizar** mis tareas para controlar pendientes |  Completado |
| `HU3` | Como usuario quiero **editar** tareas cuando sea necesario |  Completado |
| `HU4` | Como usuario quiero **eliminar** tareas que ya no necesito |  Completado |
| `HU5` | Como usuario quiero **marcar** tareas como completadas |  Completado |

---

## Funcionalidades implementadas

- [x] Registro e inicio de sesión con Firebase Authentication
- [x] Validación de formulario con manejo de errores en español
- [x] **CRUD completo de tareas** con Room (Create, Read, Update, Delete)
- [x] Lista de tareas en tiempo real con RecyclerView + LiveData
- [x] Formulario dual de creación/edición reutilizable
- [x] Marcar tareas como completadas directamente desde la lista
- [x] Eliminación con diálogo de confirmación y opción de deshacer (Snackbar)
- [x] Notificaciones locales con WorkManager para tareas próximas a vencer
- [x] Permiso de notificaciones gestionado para Android 13+

---

## Arquitectura de datos

La aplicación sigue el patrón **ViewModel → DAO → Room**:

- **View (Activity/RecyclerView)** — muestra los datos y captura interacciones del usuario, sin lógica de negocio
- **ViewModel** — expone los datos como LiveData y ejecuta las operaciones CRUD en coroutines
- **DAO (Room)** — define las consultas SQL mediante anotaciones, sin SQL escrito a mano
- **SQLite (Room)** — almacena los datos físicamente en el dispositivo, sin conexión a internet

---

## Notificaciones locales

La app usa **WorkManager** para revisar si existen tareas pendientes con fecha de vencimiento igual al día actual, y muestra una notificación local si las encuentra. No requiere conexión a internet ni servicios externos.

- Canal de notificaciones configurado (`canal_tareas_pendientes`)
- Permiso `POST_NOTIFICATIONS` solicitado en tiempo de ejecución (Android 13+)
- Worker (`RecordatorioTareasWorker`) que consulta Room directamente

---

## Tecnología utilizada

- **Kotlin** — Lenguaje oficial de Android
- **Android Studio Meerkat** — IDE oficial de Google
- **Firebase Authentication** — Gestión de usuarios y sesiones
- **Room (SQLite)** — Almacenamiento local offline de tareas
- **LiveData + ViewModel** — Arquitectura reactiva sin recargas manuales
- **RecyclerView + Adapter** — Listado eficiente de tareas
- **WorkManager** — Notificaciones locales programadas
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

## Cómo probar el CRUD

1. Registra una cuenta o inicia sesión
2. Toca el botón **+** para crear una tarea nueva con título y fecha límite
3. Verifica que la tarea aparece automáticamente en la lista principal
4. Toca la tarea creada para editar su título o descripción
5. Toca el checkbox para marcarla como completada
6. Mantén presionada una tarea para eliminarla (con confirmación)
7. Crea una tarea con fecha límite de hoy y usa el botón **"Probar notificación (debug)"** para ver el recordatorio

---

## Capturas de pantalla

| Lista de tareas | Crear/editar tarea | Notificación |
|---|---|---|
| _Ver carpeta /capturas_ | _Ver carpeta /capturas_ | _Ver carpeta /capturas_ |

---

## Estado actual del proyecto

| Hito | Estado |
|---|---|
| Entorno de desarrollo configurado | Completado |
| Repositorio GitHub creado | Completado |
| Prototipo de alta fidelidad en Figma | Completado |
| Firebase Authentication implementado | Completado |
| CRUD completo de tareas (MVP) | Completado |
| Notificaciones locales con WorkManager | Completado |
| Pulido de UI y categorías de tareas | En progreso |

---

## 👩‍💻 Autora

**Doménica Defaz**  
Universidad Central del Ecuador — 2026  
Metodología de la Investigación
