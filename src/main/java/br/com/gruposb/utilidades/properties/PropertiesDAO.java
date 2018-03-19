/**
 * https://www.devmedia.com.br/utilizando-arquivos-de-propriedades-no-java/25546
 * https://stackoverflow.com/questions/15337409/updating-property-value-in-properties-file-without-deleting-other-values
 */
package br.com.gruposb.utilidades.properties;

import br.com.gruposb.utilidades.criptografia.EncryptionService;
import br.com.gruposb.utilidades.logger.LoggerService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import br.com.gruposb.utilidades.utilidades.UtilConstantes;
import br.com.gruposb.utilidades.arquivos.UtilArquivos;

/**
 *
 * @author douglas
 */
public class PropertiesDAO {

    static File arqProp;

    LoggerService obLoggerService = new LoggerService(PropertiesDAO.class);

    /**
     * Configura a classe com o arquivo de propriedade especificado
     *
     * @param locArqPropriedades String - Arquivo de onde será lida as
     * propriedades
     */
    public PropertiesDAO(String locArqPropriedades) {
        /* Cria o arquivo caso ele não exista */
//        UtilArquivos obUtilArquivos = new UtilArquivos(new File(locArqPropriedades));

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
    public Properties abrirProperties() {

        Properties obProperties = new Properties();
        File obFile;
        FileInputStream obFileInputStream;

        try {

            obFileInputStream = new FileInputStream(arqProp);
            obProperties.load(obFileInputStream);

        } catch (IOException e) {
            obLoggerService.error(e, "openProperties");
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

        String valor = "";

        try {

            Properties obProperties = abrirProperties();

            valor = obProperties.getProperty(propriedade);

        } catch (Exception e) {
            obLoggerService.error(e, "readLocalProperty");
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
            obLoggerService.error(e, "saveOrUpdateLocalProperty");
        }

        return false;
    }//</saveOrUpdateLocalProperty>

}//</ArqPropriedadesDAO>
