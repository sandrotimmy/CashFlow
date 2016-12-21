/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashFlow.MVC.Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ParametrosCtrl {

    private static Properties INSTANCE;

    public static Properties getInstance() throws IOException {
        if (INSTANCE == null) {
            INSTANCE = leArquivo();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    private static Properties leArquivo() throws FileNotFoundException, IOException {
        Properties propiedades = new Properties();
        FileInputStream file = new FileInputStream("./parametros.properties");
        propiedades.load(file);
        return propiedades;
    }

}
