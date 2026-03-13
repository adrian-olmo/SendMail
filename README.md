# SendMail

## Descripción

SendMail es una aplicación Java para enviar correos electrónicos con soporte para adjuntos y una interfaz gráfica de usuario (GUI). Permite gestionar configuraciones y enviar emails de manera sencilla.

## Características

- Envío de correos electrónicos con adjuntos
- Interfaz gráfica intuitiva
- Gestión de configuraciones
- Soporte para múltiples destinatarios

## Requisitos

- Java 8 o superior
- Librerías: JavaMail API (javax.mail)

## Instalación

1. Clona el repositorio:
   ```
   git clone https://github.com/adrian-olmo/SendMail.git
   ```

2. Navega al directorio del proyecto:
   ```
   cd SendMail
   ```

3. Compila el proyecto:
   ```
   javac -cp . *.java Email/*.java
   ```

4. Ejecuta la aplicación:
   ```
   java -cp . Main
   ```

## Uso

1. Configura tus credenciales en `cred.properties` (asegúrate de que esté en el .gitignore).
2. Ejecuta la aplicación.
3. Usa la interfaz para redactar y enviar emails.

## Estructura del Proyecto

- `Main.java`: Punto de entrada de la aplicación.
- `ConfigManager.java`: Gestiona la configuración.
- `UIComponentsManager.java`: Maneja los componentes de la interfaz.
- `Email/`: Carpeta con clases relacionadas con el envío de emails.
  - `AttachService.java`: Servicio para adjuntos.
  - `EmailSender.java`: Envía los emails.
  - `EmailWindow.java`: Ventana de la interfaz para emails.

## Contribución

Si deseas contribuir, por favor crea un fork del repositorio y envía un pull request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
