# Ejercicio D - Aplicación JavaFX de Gestión de Personas

Este proyecto es una aplicación de escritorio desarrollada en JavaFX para la gestión de una lista de personas. La aplicación permite a los usuarios agregr personas, así como visualizar sus datos en una tabla.
## Características
    
    Interfaz Gráfica de Usuario (GUI): La aplicación utiliza JavaFX para proporcionar una interfaz intuitiva y fácil de usar.
    Gestión de Personas: Permite a los usuarios agregar nuevos registros de personas, así como modificar o eliminar registros existentes.
    Validación de Datos: Incluye verificación de entrada para asegurar que los datos ingresados sean válidos y completos.
    Alerta de Errores: Notificaciones claras para ayudar a los usuarios a corregir problemas en la entrada de datos.

## Requisitos
Para ejecutar esta aplicación, necesitarás:

    Java Development Kit (JDK) 11 o superior
    JavaFX SDK (asegúrate de tener configurado el path correctamente)
    Un IDE como IntelliJ IDEA, Eclipse, o NetBeans para compilar y ejecutar el proyecto.

## Estructura del Proyecto
    
    src/: Carpeta que contiene el código fuente de la aplicación.
        eu/andreatt/ejerciciod_dein/application/: Contiene la clase principal HelloApplication.
        eu/andreatt/ejerciciod_dein/controller/: Contiene el controlador ´HelloController' y ḿodalDController´ para la lógica de la interfaz gráfica.
        eu/andreatt/ejerciciod_dein/model/: Contiene la clase Persona, que define el modelo de datos.
    resources/: Carpeta que contiene los recursos de la aplicación.
        fxml/: Archivos FXML que definen la estructura de la interfaz gráfica.
        images/: Iconos e imágenes utilizados en la aplicación.

## Cómo Ejecutar la Aplicación
    
    Clona o descarga el repositorio a tu máquina local.
    Abre el proyecto en tu IDE y asegúrate de tener configurado el JDK y JavaFX correctamente.
    Ejecuta la clase HelloApplication para iniciar la aplicación.
    Interactúa con la interfaz para agregar personas a la lista.
    
## Uso de la Aplicación
    
    Agregar Persona: Haz clic en "Agregar Persona", te saldrá una ventana modal, rellena los campos de dale a Guardar, si le das a Cancelar la persona no será guardada en la lista.
