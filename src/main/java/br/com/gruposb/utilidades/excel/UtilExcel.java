package br.com.gruposb.utilidades.excel;

import br.com.gruposb.utilidades.logger.LoggerService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Douglas.santos
 *
 * Métodos para manipulação de arquivo do excel
 */
public class UtilExcel {

    private static final LoggerService OB_LOGGER_SERVICE = new LoggerService(UtilExcel.class);

    /**
     * Realiza a leitura do arquivo passado via parâmetro retornando uma String
     * separando colunas pelo parâmetro <b>Strig wrapString</b> e linhas pelo
     * caractere de quebra de linha: <b>\n</b>
     *
     * Retorna <b>null</b> caso ocorra algum problema na leitura
     *
     * @see Tipos Numericos são retornados com . (ponto) em vez de virgula
     *
     * @param idSheet Integer
     * @param obFile File
     * @param wrapString String - String de separação de colunas
     * @return File
     */
    public String excelLerXLSX(File obFile, String wrapString, Integer idSheet) {

        StringBuilder obStringBuilder = new StringBuilder();

        String mgn;

        try {

            /* Verifica se o arquivo passado existe */
            if (!obFile.exists()) {
                mgn = "O arquivo selecionado não existe.";
                OB_LOGGER_SERVICE.warning(mgn);
                JOptionPane.showMessageDialog(null, mgn);
            } else {

                /* Abre arquivo */
                FileInputStream obFileInputStream = new FileInputStream(obFile);

                /* Converte arquivo para formato da API POI */
                Workbook obXSSFWorkbook = new XSSFWorkbook(obFileInputStream);

                /* Requisita a primeia folha */
                Sheet obSheet = obXSSFWorkbook.getSheetAt(idSheet);

                /* Para linha da primeira folha */
                Iterator<Row> itrRow = obSheet.iterator();
                while (itrRow.hasNext()) {

                    /* Configura linha para leitura */
                    Row obRow = itrRow.next();

                    /* Para cada coluna da linha */
                    Iterator<Cell> itrCell = obRow.iterator();
                    while (itrCell.hasNext()) {

                        /* Gera uma coluna */
                        Cell obCell = itrCell.next();

                        /**
                         * /////////////////////////////////////////////////////////////////////////////
                         * E necesário verificar qual o tipo da coluna para
                         * poder imprimir de acordo para não ocorrem erros
                         * /////////////////////////////////////////////////////////////////////////////
                         */
                        if (obCell.getCellTypeEnum() == CellType.STRING) {
                            obStringBuilder.append(obCell.getStringCellValue() + wrapString);
                        }

                        if (obCell.getCellTypeEnum() == CellType.NUMERIC) {
                            obStringBuilder.append(obCell.getNumericCellValue() + wrapString);
                        }

                        /* Uma coluna só é em branco quando houve um valor lá e depois ele foi pagado */
                        if (obCell.getCellTypeEnum() == CellType.BLANK) {
                            obStringBuilder.append(wrapString);
                        }

                    }//</Para cada coluna da linha>

                    obStringBuilder.append("\n");

                }//</Para linha da primeira folha>

            }//</Verifica se o arquivo existe>

        } catch (EmptyFileException e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> EmptyFileException");
        } catch (IOException e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> IOException");
        } catch (Exception e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> Exception");
        }

        return obStringBuilder.toString();
    }//</analisarArquivo>

    /**
     * Realiza a leitura do arquivo passado via parâmetro retornando uma String
     * separando colunas pelo parâmetro <b>Strig wrapString</b> e linhas pelo
     * caractere de quebra de linha: <b>\n</b>
     *
     * Retorna <b>null</b> caso ocorra algum problema na leitura
     *
     * @see Tipos Numericos são retornados com . (ponto) em vez de virgula
     *
     * @param idSheet Integer
     * @param obFile File
     * @param wrapString String - String de separação de colunas
     * @return File
     */
    public String excelLerXLS(File obFile, String wrapString, Integer idSheet) {

        POIFSFileSystem obPOIFSFileSystem;
        HSSFWorkbook obHSSFWorkbook;
        HSSFSheet obHSSFSheet;
        HSSFRow obHSSFRow;

        StringBuilder obStringBuilder = new StringBuilder();

        String mgn;

        try {

            /* Verifica se o arquivo passado existe */
            if (!obFile.exists()) {
                mgn = "O arquivo selecionado não existe.";
                OB_LOGGER_SERVICE.warning(mgn);
                JOptionPane.showMessageDialog(null, mgn);
            } else {

                obPOIFSFileSystem = new POIFSFileSystem(obFile);
                obHSSFWorkbook = new HSSFWorkbook(obPOIFSFileSystem);
                obHSSFSheet = obHSSFWorkbook.getSheetAt(0);

//                if (obHSSFSheet.getProtect()) {
//                    JOptionPane.showMessageDialog(null, "Planilha protegida");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Planilha protegida");
//                }

                /* Para linha da primeira folha */
                Iterator<Row> itrRow = obHSSFSheet.iterator();
                while (itrRow.hasNext()) {

                    /* Configura linha para leitura */
                    Row obRow = itrRow.next();

                    /* Para cada coluna da linha */
                    Iterator<Cell> itrCell = obRow.iterator();

                    while (itrCell.hasNext()) {

                        /* Gera uma coluna */
                        Cell obCell = itrCell.next();

                        /**
                         * /////////////////////////////////////////////////////////////////////////////
                         * E necesário verificar qual o tipo da coluna para
                         * poder imprimir de acordo para não ocorrem erros
                         * /////////////////////////////////////////////////////////////////////////////
                         */
                        if (obCell.getCellTypeEnum() == CellType.STRING) {
                            obStringBuilder.append(obCell.getStringCellValue() + wrapString);
                        }

                        if (obCell.getCellTypeEnum() == CellType.NUMERIC) {
                            obStringBuilder.append(obCell.getNumericCellValue() + wrapString);
                        }

                        /* Uma coluna só é em branco quando houve um valor lá e depois ele foi pagado */
                        if (obCell.getCellTypeEnum() == CellType.BLANK) {
                            obStringBuilder.append(wrapString);
                        }

                    }//</Para cada coluna da linha>

                    obStringBuilder.append("\n");

                }//</Para cada linha>

            }

        } catch (EmptyFileException e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> EmptyFileException");
        } catch (IOException e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> IOException");
        } catch (Exception e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> Exception");
        }

        return obStringBuilder.toString();
    }

}//</(UtilExcel)>
