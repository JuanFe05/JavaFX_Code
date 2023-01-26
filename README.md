# JavaFX

Este repositorio contiene una colección de aplicaciones desarrolladas utilizando javafx, una biblioteca de interfaz gráfica de usuario para aplicaciones de escritorio java. estas aplicaciones cubren una variedad de temas y utilizan características avanzadas de javafx para proporcionar una experiencia de usuario atractiva y fácil de usar.

## Configuración de JavaFx en Visual Studio Code (VSCode)

Previo a los siguientes pasos, debe haber instalado en su equipo el **AdoptOpenJDK** y configurado la variable de entorno **JAVA_HOME**.

- Abrir el editor **Visual Studio Code (VSCode)**, ir a la sección de Extensiones.

- En el campo de búsqueda escribir javafx e instalar las extensiones **"JavaFX Support"** y **"FXML Viewer"**, dando clic en el botón Install.

- Ingresar al siguiente enlace **https://gluonhq.com/products/javafx/** y descargar **JavaFX SDK** según la versión de su sistema operativo **Nota: Descargar la versión de SDK con un soporte a largo plazo LTS**.

- Descomprimir el archivo descargado en una ubicación válida de su preferencia. Para efectos demostrativos, se descomprimirá en la ruta **"C:\javafx-sdk-19.0.2.1"**.

- En el editor **VSCode**, presionar la combinación **"Ctrl + Shift + P"** para crear un nuevo proyecto de Java usando el comando **"Java Create Java Project"**. El nombre del proyecto es de libre elección.

- En el proyecto, de clic sobre el archivo **App.java**, y en la parte inferior izquierda del editor se habilitará una sección llamada **JAVA PROJECTS**. Despliegue la sección **JAVA PROJECTS** dando clic en ella, elija la opción **"Referenced Libraries"** y de clic en el **botón +**.

<div align="center">
  <img src="https://user-images.githubusercontent.com/40324908/214439039-458631e1-01e8-47ac-a2ed-c5bd7995fe7c.PNG" alt="Install_1">
</div>

- En el siguiente cuadro de diálogo, ir a la subcarpeta **"lib"** de la ruta en la cual descomprimió **JAVA FX SDK**. Para efectos demostrativos, en este tutorial, se encuentra en la ruta **"C:\javafx-sdk-19.0.2.1\lib"**. Seleccione todos los archivos **.jar** de la carpeta **"lib"** y de clic en el botón **"Select libraries"** para agregarlos.

<div align="center">
  <img src="https://user-images.githubusercontent.com/40324908/214439823-ff525a02-c40c-4f8d-8ed8-794e8b77c15e.PNG" alt="Install_2">
</div>

- Al expandir la sección **"Referenced Libraries"** aparecerán vinculados todos los archivos **.jar**.

- Dar clic en el archivo **App.java** y dirigirse al menú **Run > Add Configuration…**.

<div align="center">
  <img src="https://user-images.githubusercontent.com/40324908/214440337-6feccc6a-5b60-4817-ad7d-eb86c18b4ecb.PNG" alt="Install_3">
</div>

- Se abre el archivo **"launch.json"**, en el cual se debe agregar la siguiente línea:

```bash
  "vmArgs": "--module-path C:/JavaFX/javafx-sdk-19.0.2.1/lib --add-modules javafx.controls,javafx.fxml",
```

<div align="center" width="300" height="200">
  <img src="https://user-images.githubusercontent.com/40324908/214441463-df8e52a5-6c26-4987-9a19-e3f290ef954f.PNG" alt="Install_4">
</div>

- Pegar el siguiente código en el archivo **App.java** para probar el funcionamiento correcto del **Java FX SDK**.

```java
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
    
public class App extends Application {
    
	public static void main(String[] args) {
		launch(args);
	}
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(app.class.getResource("view/app.fxml"));
			Pane ventana = (Pane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(ventana);
			primaryStage.setScene(scene);
			primaryStage.setTitle("APP");
			primaryStage.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
```

- Una vez haya creado la vista **.fxml** y el controlador, es necesario añadir en dicho archivo **.fxml** lo siguiente:
 
```bash
  fx:controller="NombreDelPaquete.NombreDeLaClase"
```

![op_1](https://user-images.githubusercontent.com/40324908/214718788-400c1258-4d04-401c-a05a-7cc93b00ada8.png)

- O el paso anterior, puedes agregar la ruta del controlador directamente desde el SceneBuilder.

![SB](https://user-images.githubusercontent.com/40324908/214751481-8213e399-e0b8-4bfd-890a-c0ba16191cc5.PNG)
