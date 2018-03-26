package br.com.gruposb.utilidades.utilidades;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author douglas.santos
 *
 * Constantes utilizados pelo sistema
 */
public interface UtilConstantes {

    String VERSAO = "1.0";
    String CNPJ_GERAL = "04429478000192";

    LocalDate HOJE = LocalDate.now();
    LocalTime HORA = LocalTime.now();

    /**
     * Localização e nomes dos arquivos utilizados pelo sistema
     */
    public interface ARQUIVOS {

        String ARQ_LOG_PRINCIPAL = PASTAS.DIR_LOGS + "logs-principal.log";
        String ARQ_LOG4J = "./log4j.properties";
        String ARQ_PROPRIEDADES = "./configs.properties";
        String ARQ_LOG_REGISTROS = PASTAS.DIR_LOGS + "registros.log";

    }

    public interface PASTAS {

        String DIR_DOWNLOADS = "./Downloads/";
        String DIR_LOGS = "./logs/" + HOJE + "/";
        String DIR_LOGS_PRINCIPAL = "./logs/";
        String DIR_RELATORIOS = System.getProperty("user.home");
    }

    /**
     * Senhas especificadas no sistema
     *
     * Colocado para resolução de problemas com saida de programadores
     */
    public interface SENHAS {

        String SENHA_MESTRA = "Dev20180102@grupoSB";
        String CHAVE_ENCRIPTACAO = "AAAAAAAAAAAAAAAAAAAAAAAA";

    }

    /**
     * Status desenvoldidos para o controle de notas
     * <br>Quando e verificado a emissão de uma nota ela pode receber dois
     * status ou EMITIDA ou CANCELADA pois não e baixada nenhuma nota que já foi
     * realizada uma iteração pelo sistema, apenas pela SEFAZ e pelo fornecedor
     */
    public interface STATUS_NOTAS {

        Integer CANCELADA = 5;
        Integer EMITIDA = 0;
        Integer FECHADA = 1;

    }

    /**
     * Propriedades padrão para qualquer sistema
     */
    public interface PROPRIEDADES_PADRAO {

        String LOG_QTDDIAS_EXCLUSAO_DESC = "log.qtddias.exclusao";
        String LOG_QTDDIAS_EXCLUSAO_VAL = "180";
        String SENHA_PADRAO_DESC = "password.default";
        String SENHA_PADRAO_VAL = SENHAS.SENHA_MESTRA;
    }
}
