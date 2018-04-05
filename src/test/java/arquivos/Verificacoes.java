package arquivos;

import java.io.File;
import org.junit.Test;

/**
 * @author Douglas.santos
 *
 * 
 */
public class Verificacoes {
    
    @Test
    public void listarRaiz(){
        
        File obFile = new File("./");
        
        File listFiles[] = obFile.listFiles();
        
        for (File listFile : listFiles) {
            System.out.println(listFile.getName());
        }
      
        
    }

}//</(Verificacoes)>
