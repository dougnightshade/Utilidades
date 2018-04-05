package br.com.gruposb.utilidades.arquivos;

import java.io.File;
import javax.swing.JOptionPane;
import org.junit.Test;

/**
 * @author Douglas.santos
 *
 *
 */
public class UtilArquivosTest {

    @Test
    public void verificarExtensaoTest() {

        File obFile = new File("D:\\Projetos\\SB\\atualizador-precos-medicamentos\\AtualizadorPrecosMedicamentos\\PlanModelo.xls");
        String extensao = UtilArquivos.verificarExtensao(obFile);

        JOptionPane.showMessageDialog(null, extensao);

    }

}//</(UtilArquivosTest)>
