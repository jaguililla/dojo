// P A C K A G E ///////////////////////////////////////////////////////////////
package co.popapp.servlet;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

// C L A S S ///////////////////////////////////////////////////////////////////
/**
 * Servlet que "vigila" su descarga por parte del servidor de aplicaciones.
 * Este servlet garantiza que no quedarán peticiones a medias cuando el servidor
 * decida finalizarlo, ademas controla las excepciones en los puntos de entrada
 * de manera que no pueda quedar ninguna sin trazar.
 *
 * PENDIENTE:
 *  - Sacar la configuración de las trazas aparte de modo que cualquier
 *    aplicación las pueda usar.
 *  - Sacar la información de JAXP fuera de esta clase, por la misma razón que
 *    la anterior.
 *  - Cambiar los nombres (variables, metodos) de modo que el sistema de trazas
 *    no este vinculado con log4j (para no depender de el y poder cambiarlo
 *    manteniendo coherente el código fuente.
 *
 * @author jamming
 */
public class BaseServlet extends HttpServlet {
    // Constantes
    private static final String LOG4J_CONFIG_PARAM = "log4j";
    private static final String PROPERTIES_EXT = "properties";
    private static final String XML_EXT = "xml";
    // logger
    private static Logger logger = null;
    // Propiedades para la salida limpia
    private int interval = 100;
    private int serviceCounter = 0;
    private boolean shuttingDown = false;

    protected synchronized void enteringServiceMethod () {
        serviceCounter++;
    }

    protected boolean isShuttingDown () {
        return shuttingDown;
    }

    protected synchronized void leavingServiceMethod () {
        serviceCounter--;
    }

    protected synchronized int numServices () {
        return serviceCounter;
    }

    @Override
    protected void service (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (!shuttingDown) {
            enteringServiceMethod();
            try {
                super.service(request, response);
            }
            catch (Throwable unhandledEx) {
                logger.fatal("Excepcion no controlada.", unhandledEx);
            }
            finally {
                leavingServiceMethod();
            }
        }
    }

    /**
     * Configura las trazas según el tipo de fichero de configuración.
     * @param configFileURL
     */
    public void configureTrace (URL configFileURL) {
        String configFileName = configFileURL.getFile();
        int extStart = configFileName.lastIndexOf('.') + 1;
        String configFileExt = configFileName.substring(extStart);
        if (configFileExt.equalsIgnoreCase(PROPERTIES_EXT)) {
            logger.info("Inicializando trazas con el fichero de propiedades \"" + configFileName
                + "\".");
            PropertyConfigurator.configure(configFileURL);
            initClassLogger();
        }
        else if (configFileExt.equalsIgnoreCase(XML_EXT)) {
            logger.info("Inicializando trazas con el fichero XML \"" + configFileName + "\".");
            DOMConfigurator.configure(configFileURL);
            initClassLogger();
        }
        else {
            logger.error("Formato del fichero (\"" + configFileName
                + "\") no conocido. Usando configuración por defecto.");
        }
    }

    @Override
    public void destroy () {
        shuttingDown = true;
        while (numServices() > 0) {
            try {
                Thread.sleep(interval);
            }
            catch (InterruptedException interruptedEx) {
                logger.fatal("Thread del servlet interumpido.", interruptedEx);
            }
            catch (Throwable unhandledEx) {
                logger.fatal("Excepcion no controlada.", unhandledEx);
            }
        }
    }

    @Override
    public void init (ServletConfig config) throws ServletException {
        super.init(config);
        try {
            initTraceSystem();
        }
        catch (Throwable unhandledEx) {
            logger.fatal("Excepcion no controlada.", unhandledEx);
        }
    }

    /**
     * Inicializa el logger con la categoría correspondiente al nombre de la clase.
     */
    public void initClassLogger () {
        if (LogManager.exists(getClass().getName()) == null) {
            logger = Logger.getRootLogger();
            logger.warn("Categoria no existente. Usando \"root\"");
        }
        else {
            logger = Logger.getLogger(getClass().getName());
            logger.info("Categoria \"" + getClass().getName() + "\" cargada.");
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void initTraceSystem () {
        // Inicializa la configuración básica para poder usar las trazas desde el principio
        BasicConfigurator.configure();
        logger = Logger.getRootLogger();
        // Carga el nombre del fichero de configuración de trazas desde el archivo "web.xml"
        String log4jConfigFileName = getServletContext().getInitParameter(LOG4J_CONFIG_PARAM);
        if (log4jConfigFileName == null) {
            logger.warn("Parámetro del contexto (\"" + LOG4J_CONFIG_PARAM
                + "\") no existe. Usando configuración por defecto.");
        }
        else {
            log4jConfigFileName.trim();
            try {
                URL log4jConfigURL = getServletContext().getResource(log4jConfigFileName);
                if (log4jConfigURL == null) {
                    throw new MalformedURLException();
                }
                File log4jConfigFile = new File(log4jConfigURL.getFile());
                if (log4jConfigFile.exists()) {
                    configureTrace(log4jConfigURL);
                }
                else {
                    logger.error("Fichero de configuración \"" + log4jConfigURL.getFile()
                        + "\" no existe. Usando configuración por defecto.");
                }
            }
            catch (MalformedURLException malformedURLEx) {
                logger.error("Formato del nombre de fichero no valido \"" + log4jConfigFileName
                    + "\".", malformedURLEx);
            }
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void logContextInfo () {
        // TODO Pendiente de implementar
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void logJAXPInfo () {
        // TODO Pendiente de implementar
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void logServerInfo () {
        // TODO Pendiente de implementar
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void logServletInfo () {
        // TODO Pendiente de implementar
    }
}
// E O F ///////////////////////////////////////////////////////////////////////
