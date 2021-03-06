/**
 * https://www.devmedia.com.br/utilizando-arquivos-de-propriedades-no-java/25546
 * https://stackoverflow.com/questions/15337409/updating-property-value-in-properties-file-without-deleting-other-values
 */
package br.com.gruposb.utilidades.properties;

import br.com.gruposb.utilidades.logger.LoggerService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author douglas
 *
 * Classe desenvolvida para trabalhar com arquivos de propriedades do java
 */
public class PropertiesDAO {

    static File arqProp;

    LoggerService OB_LOGGER_SERVICE = new LoggerService(PropertiesDAO.class);

    /**
     * Configura a classe com o arquivo de propriedade especificado
     *
     * @param locArqPropriedades String - Arquivo de onde será lida as
     * propriedades
     */
    public PropertiesDAO(String locArqPropriedades) {
        /* Configura o arquivo para a utilização na classe */
        arqProp = new File(locArqPropriedades);
    }

    /**
     * Realiza a abertura do arquivo arquivo de propriedades e retorna uma
     * instância para utilização do mesmo
     *
     * Existe uma validação interna verificando se o arquivo de propriedade
     * existe
     *
     * @return Properties
     */
    private Properties abrirProperties() {

        Properties obProperties = new Properties();
        File obFile;
        FileInputStream obFileInputStream;

        try {

            obFileInputStream = new FileInputStream(arqProp);
            obProperties.load(obFileInputStream);

        } catch (IOException e) {
            OB_LOGGER_SERVICE.error(e, "openProperties >> IOException");
        }

        return obProperties;

    }//</openProperties>

    /**
     * Ler e retorna o valor da propriedade local cujo nome foi passada via
     * parâmetro
     *
     * @param propriedade String - nome da propriedade que deve ser lida
     *
     * @return String
     */
    public String lerPropriedade(String propriedade) {

        String valor = null;

        try {

            Properties obProperties = abrirProperties();

            valor = obProperties.getProperty(propriedade);

        } catch (Exception e) {
            OB_LOGGER_SERVICE.error(e, "lerPropriedade >> Exception");
        }

        return valor;
    }//</lerPropLocal>

    /**
     * Realiza a gravação ou alteração da propriedade passada por parâmetro
     *
     * @param propriedade String - Nome da propriedade
     * @param valor String - Texto que será salvo na propriedade passada
     *
     * @return Boolean
     */
    public Boolean saveOrUpdateLocalProperty(String propriedade, String valor) {

        try {

            Properties obProperties = abrirProperties();

            try (FileOutputStream obFileOutputStream = new FileOutputStream(arqProp)) {
                obProperties.setProperty(propriedade, valor);
                obProperties.store(obFileOutputStream, null);
            }

            return true;

        } catch (IOException e) {
            OB_LOGGER_SERVICE.error(e, "saveOrUpdateLocalProperty >> IOException");
        } catch (Exception e) {
            OB_LOGGER_SERVICE.error(e, "saveOrUpdateLocalProperty >> Exception");
        }

        return false;
    }//</saveOrUpdateLocalProperty>

}//</ArqPropriedadesDAO>
