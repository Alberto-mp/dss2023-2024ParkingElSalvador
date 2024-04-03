package es.uca.ParkingElSalvador;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.awt.image.BufferedImage;

import net.glxn.qrgen.*;
import net.glxn.qrgen.image.ImageType;

import com.google.zxing.Binarizer;
//import net.glxn.qrgen.core.image.ImageType;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.common.*;
import javax.imageio.ImageIO;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;


public class QRservice {
    private final int dimensiones;
    private String directorio;

    public QRservice() {
        dimensiones = 360;
        directorio = "";
    }

    public void setDirectorio(String r) {
        directorio = r;
        // Verifica si el directorio existe, si no existe, intenta crearlo
        File directorioFile = new File(directorio);
        if (!directorioFile.exists()) {
            boolean creado = directorioFile.mkdirs();
            if (creado) {
                System.out.println("Directorio creado con éxito: " + directorio);
            } else {
                System.out.println("No se pudo crear el directorio: " + directorio);
            }
        }
    }

    // Método para generar un código QR a partir de una matrícula
    public void generarCodigoQR(String contenido) {
        try {
            ByteArrayOutputStream out = QRCode.from(contenido).withSize(dimensiones, dimensiones).to(ImageType.PNG).stream();
            Path path = FileSystems.getDefault().getPath(this.directorio);
            File file = new File(path.toString() + "/codigo_qr.png");
            out.writeTo(new FileOutputStream(file));
            System.out.println("Código QR generado con éxito y guardado en: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar el código QR: " + e.getMessage());
        }
    }

    // Método para leer un código QR y devolver la cadena correspondiente
    public String leerCodigoQR() throws Exception {
        try {
            // Construye la ruta del archivo QR
            File qrCodeFile = new File(this.directorio + "/codigo_qr.png");
            // Lee la imagen del archivo QR
            BufferedImage bufferedImage = ImageIO.read(qrCodeFile);
            // Crea un objeto Binarizer a partir del BufferedImageLuminanceSource
            Binarizer binarizer = new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage));
            // Crea un objeto BinaryBitmap utilizando el Binarizer
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            // Decodifica el QR
            Result result = new MultiFormatReader().decode(binaryBitmap);
            // Devuelve el contenido del QR como una cadena
            return result.getText();
        } catch (IOException | NotFoundException e) {
            System.out.println("Error al leer el código QR: " + e.getMessage());
            throw e; // Relanzar la excepción para que el método invocador pueda manejarla
        }
    }
}
