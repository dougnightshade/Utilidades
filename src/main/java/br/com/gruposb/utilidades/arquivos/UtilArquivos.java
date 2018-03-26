/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gruposb.utilidades.arquivos;

import br.com.gruposb.utilidades.utilidades.UtilConstantes;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author douglas.santos
 *
 * Responsável pelos métodos de CRUD em arquivos
 */
public class UtilArquivos {

    private final File ARQUIVO;

    private FileInputStream obFileInputStream;
    private InputStreamReader obInputStreamReader;
    private BufferedReader obBufferedReader;

    private Integer tamanhoArquivo = 20;

    /**
     * Construtor que recebe o arquivo quer será utilizado nos métodos da classe
     *
     * @param arquivo File - Arquivo que será utilizado nos métodos da classe
     */
    public UtilArquivos(File arquivo) {
        ARQUIVO = arquivo;

        abrirArquivo();

    }

    /**
     * Abre o arquivo passado via parâmetro
     *
     * @param arquivo File - Arquivo que deve ser aberto para o Buffer
     *
     * @return BufferedReader BufferedReader referênte ao arquivo passado via
     * parâmetro
     *
     * @throws java.io.FileNotFoundException
     */
    private BufferedReader abrirArquivo() {

        try {

            /**
             * Verifica se o arquivo não existe Caso não cria
             */
            if (!this.ARQUIVO.exists()) {
//                System.out.println("Arquivo não existe.");

                File diretorios = new File(this.ARQUIVO.getParent());

                if (!diretorios.exists()) {
//                    System.out.println("Diretórios não existem");
                    if (diretorios.mkdirs()) {
//                        System.out.println("Diretórios criados com sucesso.");
                    } else {
//                        System.out.println("Não foi possível criar os diretórios.");
                    }
                }

//                System.out.println("Criando o arquivo");
                FileOutputStream obFileOutputStream = new FileOutputStream(this.ARQUIVO);
                obFileOutputStream.close();
            }

            obFileInputStream = new FileInputStream(this.ARQUIVO);

            obInputStreamReader = new InputStreamReader(obFileInputStream);

            obBufferedReader = new BufferedReader(obInputStreamReader);

        } catch (IOException e) {
            System.out.println("CrudFiles -> abrirArquivo");
            e.printStackTrace();
        }

        return obBufferedReader;
    }//</abrirArquivo>

    /**
     * Adiciona no final do arquivo a String passada
     *
     * @param linha String - String a ser adicionada
     */
    public void addLinha(String linha) {

        try {

            FileWriter obFileWriter = new FileWriter(this.ARQUIVO, true);

            obFileWriter.write(linha);
            obFileWriter.write("\n");

            obFileWriter.close();

        } catch (IOException e) {
            System.out.println("CrudFiles -> addLinha");
            e.printStackTrace();
        }

    }//</addLinha>

    /**
     * Abre a janela para seleção de uma pasta
     *
     * @return String - caminho da pasta escolhida
     */
    public static String escolherPasta() {

        File diretorio = new File(UtilConstantes.PASTAS.DIR_DOWNLOADS);

        try {

            JFileChooser obJFileChooser = new JFileChooser(diretorio.getPath());
            obJFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int res = obJFileChooser.showOpenDialog(null);

            if (res == JFileChooser.APPROVE_OPTION) {
                diretorio = obJFileChooser.getSelectedFile();
            } else {
                JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro na seleção da pasta, "
                    + "favor informar o setor de sistemas.");
            System.out.println("CrudFiles -> escolherPasta");
            e.printStackTrace();
        }

        return diretorio.getPath();

    }//</escolherPasta>

    /**
     * Abre a janela para seleção de um arquivo Caso nenhum arquivo seja
     * selecionado retorna null
     *
     * @return String - caminho da pasta escolhida
     */
    public static File selecionarArquivo() {

        File arquivo = new File("./");

        try {

            JFileChooser obJFileChooser = new JFileChooser(arquivo.getPath());
            obJFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int res = obJFileChooser.showOpenDialog(null);

            if (res == JFileChooser.APPROVE_OPTION) {
                arquivo = obJFileChooser.getSelectedFile();
//                JOptionPane.showMessageDialog(null, "Arquivo selecionado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
                arquivo = null;
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro na seleção o arquivo, "
                    + "favor informar o setor de sistemas.");
            System.out.println("UtilArquivos -> selecionarArquivo");
            e.printStackTrace();
        }

        return arquivo;

    }//</selecionarArquivo>

    /**
     * Abre a janela para seleção de um arquivo Caso nenhum arquivo seja
     * selecionado retorna null
     *
     * @param extensao String - xml
     *
     * @return File
     */
    public static File selecionarArquivo(String descricao, String extensao) {

        File arquivo = new File("./");

        try {

            JFileChooser obJFileChooser = new JFileChooser(arquivo.getPath());
            obJFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter obFileNameExtensionFilter = new FileNameExtensionFilter(descricao, extensao);

//            obJFileChooser.setAcceptAllFileFilterUsed(false);
            obJFileChooser.setFileFilter(obFileNameExtensionFilter);

            int res = obJFileChooser.showOpenDialog(null);

            if (res == JFileChooser.APPROVE_OPTION) {
                arquivo = obJFileChooser.getSelectedFile();
//                JOptionPane.showMessageDialog(null, "Arquivo selecionado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
                arquivo = null;
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro na seleção o arquivo, "
                    + "favor informar o setor de sistemas.");
            System.out.println("UtilArquivos -> selecionarArquivo");
            e.printStackTrace();
        }

        return arquivo;

    }//</selecionarArquivo>

    /**
     * Abre a janela para seleção de uma pasta
     *
     * @param extensao String - Exemplo: "xml"
     *
     * @return String - caminho da pasta escolhida
     */
    public static File salvarArquivo(String extensao) {

        File arquivo = new File(System.getProperty("user.home"));

        try {

            JFileChooser obJFileChooser = new JFileChooser(arquivo.getPath());

            int res = obJFileChooser.showSaveDialog(null);

            if (res == JFileChooser.APPROVE_OPTION) {
                arquivo = obJFileChooser.getSelectedFile();
                arquivo = new File(arquivo.getPath() + "." + extensao);
            } else {
                JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro na seleção da pasta, "
                    + "favor informar o setor de sistemas.");
            System.out.println("CrudFiles -> escolherPasta");
            e.printStackTrace();
        }

        return arquivo;

    }//</escolherPasta>

//    /**
//     * Verifica o tamanho em MB do arquivo e caso seja maior que o valor passado
//     * via parâmetro o arquivo e excluido
//     * 
//     * @param tamanho Integer
//     */
//    public void excluirArqPorTamanho(Integer tamanho) {
//        
//        try {
//            
//            File obFile = new File(this.ARQUIVO.getPath());
//            
//            if (obFile.getTotalSpace() > (tamanho * 1024000)) {
//                if (obFile.delete()) {
//                    System.out.println("Log deletado com sucesso");   
//                }else{
//                    System.out.println("Não foi possivel deletar o arquivo de log");
//                }
//            }
//            
//        } catch (Exception e) {
//            System.out.println("Erro ao excluir o arquivo");
//            e.printStackTrace();
//        }
//
//    }//</excluirArqPorTamanho>
}
