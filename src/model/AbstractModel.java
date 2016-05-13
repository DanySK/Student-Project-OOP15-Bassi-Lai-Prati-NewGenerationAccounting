package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dataModel.IDataTableModel;

public abstract class AbstractModel {
    /**
     * Interfaccia delle operazioni comuni delle classi di anagrafica
     * 
     * @author niky
     * @throws ParseException
     */
    protected Map<String, Object> mappa = new HashMap<>();

    /**
     * operazione di aggiunta di un nuovo oggetto al dataBase del programma
     * 
     * @author niky
     * @throws ParseException
     * @param elem
     *            mappa contenente le informazioni sull'elemento da aggiungere
     */
    void add(Map<String, Object> elem) throws ParseException {
        addElem(elem);
    }

    /**
     * operazione per cercare e modificare un oggetto all'interno del dataBase
     * 
     * @author niky
     */
    void edit(IDataTableModel obj,Map<String, Object> infoDaModificare) {
        editElem(obj,infoDaModificare);
    }

    /**
     * operazione per restituire alla view i dati del dataBase da mostrare
     * all'utente
     * 
     * @return ritorna i dati richiesti
     * 
     * @author niky
     */
    List<Object> load() {
        return null;
    }

    /**
     * operazione per rimuovere un oggetto dal dataBase
     * 
     * @author niky
     */
    void remove() throws ParseException {
        removeElem(mappa);
    }

    /**
     * operazione per cercare un oggeto all'interno del dataBase
     * 
     * @return ritorna l'oggetto ricercato se presente nel dataBase
     */
    List<Object> search() {
        return null;
    }

    abstract void editElem(IDataTableModel obj,Map<String, Object> ifoDaModificare);
    abstract void removeElem(Map<String, Object> elemDaEliminare) throws ParseException;

    abstract void addElem(Map<String, Object> elem) throws ParseException;

    public Map<String, Object> getMap(IDataTableModel obj) {
        List<Object> lista = new LinkedList<>();
        mappa.clear();
        if (obj == null) {
            mappa.put("Nome Conto", new String());
            mappa.put("Natura Conto", new String());
            mappa.put("Data Movimento", new Date());
            mappa.put("lista conti usati nel movimento", lista.add(new ArrayList<Object>(3)));
            return mappa;
        } else
            mappa.putAll((Map<? extends String, ? extends Object>) obj);
            return mappa;
    }
}
