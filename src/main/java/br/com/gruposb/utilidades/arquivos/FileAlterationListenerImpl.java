package br.com.gruposb.utilidades.arquivos;

import br.com.gruposb.utilidades.utilidades.UtilConstantes;
import br.com.gruposb.utilidades.logger.LoggerService;
import java.io.File;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * @author douglas.santos
 *
 *
 */
public class FileAlterationListenerImpl implements FileAlterationListener {

    private final LoggerService obLoggerService = new LoggerService(
            FileAlterationListener.class,
            UtilConstantes.ARQUIVOS.ARQ_LOG_REGISTROS
    );

    @Override
    public void onStart(FileAlterationObserver observer) {
        obLoggerService.warning("Iniciado o monitoramento para: " + observer.getDirectory().getAbsolutePath());
    }

    @Override
    public void onDirectoryCreate(File directory) {
        obLoggerService.info(directory.getAbsolutePath() + " foi criado.");

    }

    @Override
    public void onDirectoryChange(File directory) {
        obLoggerService.info(directory.getAbsolutePath() + " foi modificado.");
    }

    @Override
    public void onDirectoryDelete(File directory) {
        obLoggerService.info(directory.getAbsolutePath() + " foi excluído.");
    }

    @Override
    public void onFileCreate(File file) {
        obLoggerService.info(file.getAbsolutePath() + " onFileCreate");
    }

    @Override
    public void onFileChange(File file) {
        obLoggerService.info(file.getAbsolutePath() + " onFileChange");
    }

    @Override
    public void onFileDelete(File file) {
        obLoggerService.info(file.getAbsolutePath() + " onFileDelete");
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        obLoggerService.info(observer.getDirectory().getAbsolutePath() + " onStop");
    }

}//</(FileAlterationListenerImpl)>
