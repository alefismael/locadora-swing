package util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alefi
 */

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageUtil {
    public static Image loadImageFromResources(String resourcePath) {
        String cleanPath = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
        Image image = null;

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(cleanPath)) {

            if (is == null) {
                System.err.println("ERRO: Imagem não encontrado no classpath: " + cleanPath);
                return null;
            }
            
            image = ImageIO.read(is);

        } catch (IOException e) {
            System.err.println("ERRO de IO ao carregar imagem: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("ERRO: O InputStream fornecido era nulo ou inválido.");
            e.printStackTrace();
        }

        return image;
}
}
