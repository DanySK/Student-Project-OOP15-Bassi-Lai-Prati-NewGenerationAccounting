package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public abstract class AbstractModel {
    /**
     * Interfaccia delle operazioni comuni delle classi di anagrafica
     * 
     * @author niky
     */

    protected List<Object> datiDB = new LinkedList<>();

    @SuppressWarnings("unchecked")
    void add(Object elemDaAggiungere) throws ParseException {
        if (elemDaAggiungere.getClass().equals(Pair.class)) {
            comparisonAccount((Pair<String, Object>) elemDaAggiungere);
        } else if (elemDaAggiungere.getClass().equals(Map.class)) {
            comparisonMovement((Pair<String, List<ArrayList<String>>>) elemDaAggiungere);
        }
    }

    /**
     * operazione per cercare e modificare un oggetto all'interno del dataBase
     * 
     * @author niky
     */
    abstract void comparisonAccount(Pair<String, Object> elemDaAggiungere);

    abstract void comparisonMovement(Pair<String, List<ArrayList<String>>> infoMovimento) throws ParseException;

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
}
