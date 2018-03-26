package br.com.gruposb.utilidades.criptografia;

import br.com.gruposb.utilidades.utilidades.EnumSenha;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import br.com.gruposb.utilidades.utilidades.UtilConstantes;

/**
 * @author douglas.santos
 *
 * Classe com serviços de criptografia
 */
public class EncryptionService {

    public EncryptionService(String IV, String chaveEncriptacao) {
        this.IV = IV;
        this.chaveEncriptacao = chaveEncriptacao;
    }

    private final String IV;
    private final String chaveEncriptacao;

//    private final String key = "aesEncryptionKey";
//    private final String initVector = "encryptionIntVec";

    /**
     * Criptografa o teste passado de acordo com a chave passada
     * <br>Para trabalhar com o texto criptografa pode-se utilizar um loop
     * pegando cada posição do array para formar uma String
     *
     * @param textopuro String - String limpa sem encriptação
     *
     * @return byte[] - Bytes criptografados
     * @throws java.lang.Exception
     */
    public byte[] encrypt(String textopuro) throws Exception {

        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

        SecretKeySpec obSecretKeySpec = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");

        encripta.init(Cipher.ENCRYPT_MODE, obSecretKeySpec, new IvParameterSpec(IV.getBytes("UTF-8")));

        return encripta.doFinal(textopuro.getBytes("UTF-8"));
    }//</encrypt>

    /**
     * Descriptografa o teste passado de acordo com a chave passada
     *
     * @param textoencriptado byte[] - Retorno do método irmão encrypt
     *
     * @return String - Texto descriptografado
     *
     * @throws java.lang.Exception
     */
    public String decrypt(byte[] textoencriptado) throws Exception {

        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

        SecretKeySpec obSecretKeySpec = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");

        decripta.init(Cipher.DECRYPT_MODE, obSecretKeySpec, new IvParameterSpec(IV.getBytes("UTF-8")));

        return new String(decripta.doFinal(textoencriptado), "UTF-8");
    }//</decrypt>

    /**
     * Como o método de decriptação fica muito mais simples somente utilizado
     * byte[] em vez de Receber uma string e necessário criar um método que
     * permita retornar uma string dos dados decryptados
     *
     * @param bytesEncriptados byte[]
     * @return String
     */
    public String byteToString(byte[] bytesEncriptados) {

        String textoDecriptado = "";
        StringBuilder obStringBuilder = new StringBuilder();

        for (int i = 0; i < bytesEncriptados.length; i++) {
            obStringBuilder.append(String.valueOf(bytesEncriptados[i])).append(" ");
        }

        textoDecriptado = obStringBuilder.toString();

        return textoDecriptado;
    }//</byteToString>

}//</EncryptionService>
