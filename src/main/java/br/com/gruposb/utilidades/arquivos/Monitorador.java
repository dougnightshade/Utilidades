package br.com.gruposb.utilidades.arquivos;

import br.com.gruposb.utilidades.logger.LoggerService;
import java.io.File;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * @author douglas.santos
 *
 * Implementado com base no framework Apache Commons IO 2.4
 */
public class Monitorador {

    private final File ARQUIVO;

    private final LoggerService obLoggerService = new LoggerService(Monitorador.class);

    /**
     * Configura a pasta ou arquivo a ser monitorado
     *
     * Por padrão o tempo de atualização é de 15 segundos
     *
     * @param arqOuPasta File
     */
    public Monitorador(File arqOuPasta) {
        this.ARQUIVO = arqOuPasta;
    }//</Monitorador>

    /**
     * Inicia o monitoramentop para a pasta ou arquivo
     */
    public void iniciar() {

        FileAlterationObserver obFileAlterationObserver = new FileAlterationObserver(this.ARQUIVO);
        final FileAlterationMonitor obFileAlterationMonitor = new FileAlterationMonitor();

        /**
         * Configura a classe de implementação desenvolvida como a ação para
         * esse monitor
         */
        obFileAlterationObserver.addListener(new FileAlterationListenerImpl());

        try {

            Thread obThread = new Thread(() -> {
                try {
                    obFileAlterationMonitor.addObserver(obFileAlterationObserver);
                    obFileAlterationMonitor.start();
                } catch (Exception e) {
                    obLoggerService.error(e, "run");
                }
            });
        } catch (Exception e) {
            obLoggerService.error(e, "iniciar");
        }

    }//</iniciar>

}//</(Monitorador)>
