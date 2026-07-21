# Manual Técnico — Lista de Tareas v1.0

## 1. Descripción del sistema
Aplicación Android nativa para gestión de tareas personales orientada a estudiantes universitarios de Quito, Ecuador. Resuelve el problema de la falta de una herramienta gratuita, offline y en español. El MVP cubre 5 historias de usuario: crear, visualizar, editar, eliminar y marcar tareas como completadas.

## 2. Arquitectura
Patrón: MVVM (Model-View-ViewModel)

View (Activity/XML) → ViewModel (LiveData) → DAO (Room) → SQLite

## 3. Tecnologías y versiones
- Kotlin (built-in, AGP 9.1.1)
- Android Studio Meerkat 2024.3.1
- Firebase Authentication (BOM 34.15.0)
- Room 2.7.0 + KSP 2.1.10-1.0.31
- WorkManager 2.9.0
- Material Design 3 1.14.0
- LiveData / ViewModel 2.7.0
- RecyclerView 1.3.2
- MinSDK: API 24 (Android 7.0)

## 4. Instrucciones para compilar
1. `git clone https://github.com/domedefaz/lista-de-tareas-android.git`
2. Abrir el proyecto en Android Studio
3. Agregar `google-services.json` dentro de la carpeta `app/` (obtener de Firebase Console — **requerido para compilar**)
4. File → Sync Project with Gradle Files
5. Run → Run app (o Shift+F10)

## 5. Estructura del repositorio
app/src/main/java/com/metodologiaDefaz/listadetareas/
├── MainActivity.kt # Pantalla principal con RecyclerView
├── LoginActivity.kt # Login con Firebase
├── RegisterActivity.kt # Registro de usuario
├── FormularioTareaActivity.kt # Crear/editar tarea (formulario dual)
├── Tarea.kt # Entidad Room
├── TareaDao.kt # DAO con operaciones CRUD
├── AppDatabase.kt # Singleton de la base de datos
├── TareaViewModel.kt # ViewModel con LiveData
├── TareaAdapter.kt # Adapter del RecyclerView
├── ValidadorFormulario.kt # Lógica de validación testeable
├── RecordatorioTareasWorker.kt # Worker de notificaciones
└── ListaTareasApplication.kt # Canal de notificaciones

## 6. Limitaciones conocidas
- El primer inicio de sesión requiere conexión a internet (Firebase Authentication)
- Las tareas se almacenan localmente — no se sincronizan entre dispositivos
- El archivo google-services.json no está incluido en el repositorio por seguridad

## 7. Historial de versiones
- **v1.0** — Julio 2026 — MVP completo: login Firebase, CRUD tareas Room, notificaciones WorkManager, 13 pruebas automatizadas
