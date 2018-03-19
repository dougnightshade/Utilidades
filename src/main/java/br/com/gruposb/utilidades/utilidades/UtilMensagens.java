/**
 * Autor: Douglas dos Santos
 * Desenvolvido para: SB Log
 *
 * Bases:
 * -http://www.guj.com.br/t/joptionpane-por-2-segundos/92191/2
 */
package br.com.gruposb.utilidades.utilidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author douglas
 */
public class UtilMensagens {

    /**
     * Exibe um JOptionPane de acordo com o tempo passado via parâmetro
     *
     * @param Integer tempo - tempo em milisegundos - 1000 = sec
     * @param String mensagem - Mensagem para exibição
     */
    public void mensagemComTempo(Integer tempo, String mensagem) {
        
        tempo = tempo * 1000;
        
        JOptionPane meuJOPane = new JOptionPane(mensagem);//instanciando o JOptionPane
        final JDialog dialog = meuJOPane.createDialog(null, mensagem);//aqui uso um JDialog para manipular

        dialog.setModal(true);
        //Usando o javax.swing.Timer para poder gerar um evento em um tempo determinado
        //Veja o construtor da classe Timer para mais explicações
        Timer timer = new Timer(tempo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                dialog.dispose();  //o evento(no caso fechar o meu JDialog)
            }
        });
        timer.start();
        dialog.setVisible(true);
        timer.stop();
    }//</mensagemComTempo>

}//</(UtilMensagens)>
