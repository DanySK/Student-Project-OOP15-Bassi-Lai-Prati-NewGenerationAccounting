package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import dataModel.Account;
import dataModel.IDataTableModel;

public abstract class AbstractModel {
    /**
     * Interfaccia delle operazioni comuni delle classi di anagrafica
     * 
     * @author niky
     */

   void add(){}
    /**
     * operazione per cercare e modificare un oggetto all'interno del dataBase
     * 
     * @author niky
     */
   
    /**
     * operazione per restituire alla view i dati del dataBase da mostrare
     * all'utente
     * 
     * @return ritorna i dati richiesti
     * 
     * @author niky
     */
    void edit(Map<String, Object> contoDaModificare) {
    }

    /**
     * operazione per cercare un oggeto all'interno del dataBase
     * 
     * @return ritorna l'oggetto ricercato se presente nel dataBase
     */
    List<Object> load() {
        return null;
    }

    /**
     * operazione di aggiunta di un nuovo oggetto al dataBase del programma
     * 
     * @author niky
     */

    void remove(String nome, String natura) {
    }

    /**
     * operazione per rimuovere un oggetto dal dataBase
     * 
     * @author niky
     */

    List<Object> search(String nome, String natura) {
        return null;
    }
    public Map<String,Object> getMap(IDataTableModel obj){
        Map<String,Object> mappa = new HashMap<>();
        if(obj==null){
           mappa.put("Nome Conto", new String(""));
           mappa.put("Natura Conto", new String (""));
           mappa.put("Data Movimento", new Date(0, 0, 0));
           mappa.put("lista conti usati nel movimento", new HashSet<Account>());
           return mappa;
        }
        else
            return mappa;
    }
}
