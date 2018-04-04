package br.com.gruposb.utilidades.excel;

import java.io.File;
import org.junit.Test;

/**
 * @author Douglas.santos
 *
 *
 */
public class LerArquivoExcelTest {

    @Test
    public void lerArquivoExcel() {

        ArquivoExecel obLerArquivoExcel = new ArquivoExecel();

        File obFile = new File("C:\\Users\\douglas.santos\\Documents\\Pasta2.xlsx");
        String retorno = obLerArquivoExcel.lerArquivoExcel(obFile, ";");

        System.out.println(retorno);

    }

}//</(LerArquivoExcelTest)>
