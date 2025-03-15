
# 🏃 Run O'Clock

Aplicación desarrollada en **Android Studio** utilizando **Jetpack Compose**.

## 📋 Requisitos del Proyecto

- **Android Studio**: Flamingo | 2023.2.1 o superior
- **Compile SDK**: 35
- **Minimum SDK**: 24
- **Target SDK**: 35
- **Gradle Version**: 8.10.2
- **Kotlin Version**: 2.0.0

## 🚀 Configuración del Proyecto

1. Clona este repositorio:
   ```sh
   git clone https://github.com/tu-usuario/runoclock.git
   ```
2. Abre el proyecto en **Android Studio**.
3. Asegúrate de tener instalados los SDKs correspondientes.
4. Sincroniza las dependencias con Gradle.
5. Ejecuta la aplicación en un dispositivo físico o emulador.

## 📦 Dependencias Principales

```kotlin
// Core de Android
implementation("androidx.core:core-ktx:1.10.1")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
implementation("androidx.activity:activity-compose:1.8.2")

// Jetpack Compose
implementation("androidx.compose.ui:ui:1.5.4")
implementation("androidx.compose.material:material:1.5.4")
implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

// Material 3
implementation("androidx.compose.material3:material3:1.2.0-beta01")
implementation("androidx.compose.material3:material3-window-size-class:1.2.0-beta01")
implementation("androidx.compose.material3:material3-android:1.2.0-rc01")

// Navegación
implementation("androidx.navigation:navigation-compose:2.7.5")

// Testing
testImplementation("junit:junit:4.13.2")
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
```

## 🛠 Características

- Arquitectura basada en **Jetpack Compose**.
- Uso de **Material 3** para el diseño moderno.
- Implementación de **Navigation Compose**.
- Compatible con dispositivos **Android 7.0 (API 24) en adelante**.


