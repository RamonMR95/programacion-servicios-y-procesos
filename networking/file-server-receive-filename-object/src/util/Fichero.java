package util;

import java.io.File;
import java.io.Serializable;

public class Fichero implements Serializable {

    private File fichero;

    public Fichero(String nombreFichero, String pathFichero) {
        this.fichero = new File(pathFichero + File.separatorChar + nombreFichero);
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }
}
