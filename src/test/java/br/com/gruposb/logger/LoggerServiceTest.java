/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gruposb.logger;

import br.com.gruposb.utilidades.arquivos.UtilArquivos;
import br.com.gruposb.utilidades.properties.PropertiesDAO;
import br.com.gruposb.utilidades.utilidades.UtilConstantes;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Douglas.santos
 */
public class LoggerServiceTest {

    String arq_principal = "./teste.log";

//    public LoggerServiceTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//    @Test
    public void gerarArqLog() {

        UtilArquivos obUtilArquivos = new UtilArquivos(new File(this.arq_principal));

        for (int i = 0; i < 999999; i++) {

            String linha = LocalDateTime.now().toString();

            obUtilArquivos.addLinha(linha);

        }

    }

    /**
     * Verifica se o tamanho arquivo e maior que 100MB, caso seja o arquivo de
     * log e deletado
     */
    @Test
    public void excluirArqslogPorDias() {

        

    }

}
