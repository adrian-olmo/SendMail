package T1_Layouts.SendMail;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private final Properties properties;

    public ConfigManager(String propertiesFilePath) throws IOException {
        properties = new Properties();
        try (InputStream input = new FileInputStream(propertiesFilePath)) {
            properties.load(input);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("El archivo properties no se encontró: " + propertiesFilePath);
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo properties: " + propertiesFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado: " + e.getMessage(), e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}