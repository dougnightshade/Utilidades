package br.com.gruposb.utilidades.excel;

import br.com.gruposb.utilidades.logger.LoggerService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
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
public class ArquivoExecel {

    private static final LoggerService OB_LOGGER_SERVICE = new LoggerService(ArquivoExecel.class);

    /**
     * Realiza a leitura do arquivo passado via parâmetro retornando uma String
     * separando colunas pelo parâmetro <b>Strig wrapString</b> e linhas pelo
     * caractere de quebra de linha: <b>\n</b>
     *
     * Retorna <b>null</b> caso ocorra algum problema na leitura
     *
     * @see Tipos Numericos são retornados com . (ponto) em vez de virgula
     *
     * @param obFile File
     * @param wrapString String - String de separação de colunas
     * @return File
     */
    public String lerArquivoExcel(File obFile, String wrapString) {

        StringBuilder obStringBuilder = new StringBuilder();

        try {

            /* Abre arquivo */
            FileInputStream excelFile = new FileInputStream(obFile);

            /* Converte arquivo para formato da API POI */
            Workbook workbook = new XSSFWorkbook(excelFile);

            /* Requisita a primeia folha */
            Sheet datatypeSheet = workbook.getSheetAt(0);

            /* Para linha da primeira folha */
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {

                /* Configura linha para leitura */
                Row currentRow = iterator.next();

                /* Para cada coluna da linha */
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {

                    /* Gera uma coluna */
                    Cell currentCell = cellIterator.next();

                    /**
                     * /////////////////////////////////////////////////////////////////////////////
                     * E necesário verificar qual o tipo da coluna para poder
                     * imprimir de acordo para não ocorrem erros
                     * /////////////////////////////////////////////////////////////////////////////
                     */
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        obStringBuilder.append(currentCell.getStringCellValue() + wrapString);
                    }

                    if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        obStringBuilder.append(currentCell.getNumericCellValue() + wrapString);
                    }

                    /* Uma coluna só é em branco quando houve um valor lá e depois ele foi pagado */
                    if (currentCell.getCellTypeEnum() == CellType.BLANK) {
                        obStringBuilder.append(wrapString);
                    }

                }//</Para cada coluna da linha>

                obStringBuilder.append("\n");

            }//</Para linha da primeira folha>

        } catch (IOException e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> IOException");
        } catch (Exception e) {
            obStringBuilder = null;
            OB_LOGGER_SERVICE.error(e, "lerArquivoExcel >> Exception");
        }

        return obStringBuilder.toString();
    }//</analisarArquivo>

}//</(ArquivoExecel)>
