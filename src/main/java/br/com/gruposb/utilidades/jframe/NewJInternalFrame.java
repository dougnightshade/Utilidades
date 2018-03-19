package br.com.gruposb.utilidades.jframe;

import java.awt.Dimension;

/**
 * @author douglas.santos
 *
 * Adiciona métodos e funções para um JInternalFrame
 */
public abstract class NewJInternalFrame extends javax.swing.JInternalFrame {

    /**
     * Recupera o ponto centra do monitor e centraliza frame
     */
    public void setPosicaoCentro() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }//</setPosicaoCentro>

    /**
     * Ativa ou desativa os campos
     *
     * @param ativo
     */
    public abstract void ctrlCampos(Boolean ativo);//</ctrlCampos>

}//</(NewJInternalFrame)>
