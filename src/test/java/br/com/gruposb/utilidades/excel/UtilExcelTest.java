package br.com.gruposb.utilidades.excel;

import br.com.gruposb.utilidades.arquivos.UtilArquivos;
import java.io.File;
import org.junit.Test;

/**
 * @author Douglas.santos
 *
 *
 */
public class UtilExcelTest {

    @Test
    public void excelLerXLSXTest() {

        UtilExcel obUtilExcel = new UtilExcel();

        File obFile = new File("C:\\Users\\douglas.santos\\Documents\\Pasta2.xlsx");
        String retorno = obUtilExcel.excelLerXLSX(obFile, ";", 0);
        System.out.println(retorno);
    }
    
    @Test
    public void excelLerXLSTest() {

        UtilExcel obUtilExcel = new UtilExcel();

        File obFile = new File("D:\\Projetos\\SB\\atualizador-precos-medicamentos\\AtualizadorPrecosMedicamentos\\PlanModelo.xls");
        String retorno = obUtilExcel.excelLerXLS(obFile, ";", 0);
        System.out.println(retorno);
    }

}//</(UtilExcelTest)>
