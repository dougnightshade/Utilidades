package br.com.gruposb.utilidades.logger;

import br.com.gruposb.utilidades.arquivos.UtilArquivos;
import br.com.gruposb.utilidades.properties.PropertiesDAO;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import javax.swing.JTextArea;
import br.com.gruposb.utilidades.utilidades.UtilConstantes;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JTextPane;

/**
 * @author douglas.santos
 *
 * Desenvolvida para centralizar o controle de log do sistema
 */
public class LoggerService {

    private final UtilArquivos obUtilArquivos;

    private final Class classe;

    private LocalDateTime agora;

    /**
     * Construtor passando com o nome da classe é passando qual será o arquivo
     * de log a ser utilizado
     *
     * @param classe Class - Classe que será utilizada para o log dessa
     * instância
     * @param arqLog String - Arquivo de log que será utilizado pelo framework
     */
    public LoggerService(Class classe, String arqLog) {
        this.obUtilArquivos = new UtilArquivos(new File(arqLog));
        this.classe = classe;

        excluirArqslogPorDias();
    }

    /**
     * Construtor passando apenas o nome da classe onde será utilizado o arquivo
     * de log principal do sistema
     *
     * @param classe Class - Classe que será utilizada para o log dessa
     * instância
     */
    public LoggerService(Class classe) {
        this.obUtilArquivos = new UtilArquivos(new File(UtilConstantes.ARQUIVOS.ARQ_LOG_PRINCIPAL));

        this.classe = classe;
    }

    /**
     * Loga/Registra no consoleJTextArea a exception passada
     *
     * @param obException Exception - Classe mãe para todas as exeptions
     * @param nomeMetodo String - padrão de localização para os logs
     */
    public void error(Exception obException, String nomeMetodo) {

        agora = LocalDateTime.now();

        StringWriter errors = new StringWriter();
        obException.printStackTrace(new PrintWriter(errors));

        System.err.println("#########################################################################################################################################################################");
        System.err.println(agora + " ERRO: " + classe.getName());
        System.err.println("Método: " + nomeMetodo);
        System.err.println(errors);

        obUtilArquivos.addLinha("####################################################################################################################################################################");
        obUtilArquivos.addLinha(agora + " ERRO: " + classe.getName());
        obUtilArquivos.addLinha("Método: " + nomeMetodo);
        obUtilArquivos.addLinha(errors.toString());

    }//</error>

    /**
     * Aviso
     *
     * @param aviso String - Texto de aviso para o consoleJTextArea
     */
    public void warning(String aviso) {
        agora = LocalDateTime.now();

        System.out.println("\n");
        System.out.println("#########################################################################################################################################################################");
        System.out.println(agora + " WARN: " + classe.getName() + " -> " + aviso);

        obUtilArquivos.addLinha("\n");
        obUtilArquivos.addLinha("#########################################################################################################################################################################");
        obUtilArquivos.addLinha(agora + " WARN: " + classe.getName() + " -> " + aviso);

    }//</waring>

    /**
     * DEBUG registrado
     *
     * @param debug String - Texto de aviso para o consoleJTextArea
     */
    public void debug(String debug) {
        agora = LocalDateTime.now();

        System.out.println(agora + " DEBUG: " + classe.getName() + " -> " + debug);

        obUtilArquivos.addLinha(agora + " DEBUG: " + classe.getName() + " -> " + debug);
    }//</waring>

    /**
     * Info
     *
     * @param info String
     */
    public void info(String info) {
        agora = LocalDateTime.now();

        System.out.println(agora + " INFO: " + info);

        obUtilArquivos.addLinha(agora + " INFO: " + info);
    }//</waring>

    /**
     * Servico de log configurado para impressão em um JTextArea
     *
     * @param txArea JTextArea - JTextArea para ser impressão o texto
     * @param txtImpresso String - Texto para adição
     */
    public void consoleJTextArea(JTextArea txArea, String txtImpresso) {

        try {

//            if (txArea.getText().length() > 500000) {
//                txArea.setText("");
//            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(txArea.getText());
            stringBuilder.append("\n");
            stringBuilder.append(txtImpresso);
            txArea.setText(stringBuilder.toString());
            txArea.setCaretPosition(txArea.getDocument().getLength());

        } catch (Exception e) {
            error(e, "console");
        }

    }//</console>

    /**
     * Servico de log configurado para impressão em um JTextPane
     *
     * <br>Para mandar limpar o console utiliza-se null como o texto para
     * impressão
     *
     * @param txArea JTextPane - JTextPane para ser impressão o texto
     * @param txtImpresso String - Texto para adição
     */
    public void consoleJTextPane(JTextPane txArea, String txtImpresso) {
        try {

            LocalDateTime obLocalDateTime = LocalDateTime.now();

            if (txtImpresso == null) {
                txArea.setText("");
            } else if (txtImpresso.equals("#")) {
                txtImpresso = "\n###########################################################################################################################################";
            } else {
                txtImpresso = obLocalDateTime + " - " + txtImpresso;
            }

//            if (txArea.getText().length() > 500000) {
//                txArea.setText("");
//            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(txArea.getText());
            if (!txArea.getText().equals("")) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(txtImpresso);
            txArea.setText(stringBuilder.toString());
            txArea.setCaretPosition(txArea.getDocument().getLength());

        } catch (Exception e) {
            error(e, "console");
        }

    }//</console>

    private void excluirArqslogPorDias() {

        PropertiesDAO obPropertiesDAO;

        File dirLogs = new File(UtilConstantes.PASTAS.DIR_LOGS_PRINCIPAL);
        File fileDirLogs[];
        File filesToDelete[]; //Lista de arquivo que estão dentro do diretorio para ser deletado

        fileDirLogs = dirLogs.listFiles();

        LocalDate dtDelete = LocalDate.now();

        String strQtdDiasExclusao = UtilConstantes.PROPRIEDADES_PADRAO.LOG_QTDDIAS_EXCLUSAO_VAL; // Necessário para verificar se o arquivo e nulo
        Long longQtdDiasExclusao = new Long(UtilConstantes.PROPRIEDADES_PADRAO.LOG_QTDDIAS_EXCLUSAO_VAL); // Valor utilizado depois da verificações

        /**
         * /////////////////////////////////////////////////////////////////////////////
         * Por padrão os logs são guardados em pastas com a data de criação do
         * arquivo
         * /////////////////////////////////////////////////////////////////////////////
         * Por padrão os logs são excluidos a cada 180 dias
         * /////////////////////////////////////////////////////////////////////////////
         */
        try {

            /* Verifica se o arquivo de propriedades existe */
            File arqPropriedades = new File(UtilConstantes.ARQUIVOS.ARQ_PROPRIEDADES);
            if (arqPropriedades.exists()) {
                obPropertiesDAO = new PropertiesDAO(UtilConstantes.ARQUIVOS.ARQ_PROPRIEDADES);

                obPropertiesDAO.lerPropriedade(UtilConstantes.PROPRIEDADES_PADRAO.LOG_QTDDIAS_EXCLUSAO_DESC);

                /* Verifica se existe a pripriedade */
                if (strQtdDiasExclusao != null) {
                    longQtdDiasExclusao = Long.valueOf(strQtdDiasExclusao);
                }
            }

            /* Calcula a data de exclusão */
            dtDelete = dtDelete.minusDays(longQtdDiasExclusao);
            System.out.println("Data para apagar logs: " + dtDelete);

            /* Para cada pasta do diretório principal de logs */
            for (File fileDirLog : fileDirLogs) {

                /**
                 * /////////////////////////////////////////////////////////////////////////////
                 * Gera a data com base no nome da pasta
                 * /////////////////////////////////////////////////////////////////////////////
                 */
                LocalDate nomePasta;

                Integer dia = Integer.parseInt(fileDirLog.getName().substring(8, 10));
                Integer mes = Integer.parseInt(fileDirLog.getName().substring(5, 7));
                Integer ano = Integer.parseInt(fileDirLog.getName().substring(0, 4));

                nomePasta = LocalDate.of(ano, mes, dia);

                /* Verifica se a data que a pasta representa e anterior a data de exclusão */
                Period obPeriod = dtDelete.until(nomePasta);

                System.out.println("Periodo: " + obPeriod.isNegative());

                if (obPeriod.isNegative()) {

                    System.out.println("Deleando pasta: " + nomePasta);

                    /* Busca pos arquivos dentro da pasta atual */
                    filesToDelete = fileDirLog.listFiles();

                    for (File file : filesToDelete) {
                        /* Realiza a tentatica de deletar */
                        if (file.delete()) {
                            file.deleteOnExit();
                        }
                    }

                    /* Deleta a pasta */
                    if (fileDirLog.delete()) {
                        fileDirLog.deleteOnExit();
                    }
                }
            }
        } catch (NumberFormatException e) {
            error(e, "verificarTamanho >> Exception");
        } catch (Exception e) {
            error(e, "verificarTamanho >> Exception");
        }

    }

}//</ServiceLogger>
