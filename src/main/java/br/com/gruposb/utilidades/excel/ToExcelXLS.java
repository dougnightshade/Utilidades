package br.com.gruposb.utilidades.excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author douglas.santos
 *
 * @author Gustavo Machado
 */
public class ToExcelXLS {

    /**
     * Exporta a JTable recebida como Excel de acordo com os parâmetros
     * passadados
     */
    public static void exportTable(JTable table, File arqRelatorio, HSSFWorkbook workbook, String nomePlanilha) throws IOException {

        int i; // Controle de linha

        HSSFSheet obHSSFSheet = workbook.createSheet(nomePlanilha);

        FileOutputStream fos = new FileOutputStream(arqRelatorio);
        TableModel model = table.getModel();
        HSSFRow row = obHSSFSheet.createRow(0);

        /* Controle de colunas */
        int TOTAL_COLUNAS = model.getColumnCount();
        
        /* Gera colunas */
        for (i = 0; i < TOTAL_COLUNAS; ++i) {
            row.createCell(i).setCellValue(model.getColumnName(i));
        }
        
        /* Gera linhas */
        for (i = 0; i < model.getRowCount(); ++i) {
            row = obHSSFSheet.createRow(i + 1);
            for (int j = 0; j < TOTAL_COLUNAS; ++j) {
                row.createCell(j).setCellValue(model.getValueAt(i, j).toString());
            }
        }
        
        /* Salvar arquivo */
        workbook.write((OutputStream) fos);
        System.gc();
        fos.flush();
        fos.close();

        /**
         * /////////////////////////////////////////////////////////////////////////////
         * Abre o arquivo
         * /////////////////////////////////////////////////////////////////////////////
         */
        Desktop obDesktop = Desktop.getDesktop();
        obDesktop.open(arqRelatorio);

    }

}//</(ToExcelXLS)>
