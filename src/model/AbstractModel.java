package model;

import java.util.List;
import java.util.Map;

public abstract class AbstractModel{ //diventerà AbstractModel sarà classe astratta
    /**
     * Interfaccia delle operazioni comuni delle classi di anagrafica
     * @author niky
     */
     void add(Map<String,Object> contoDaAggiungere){}
     /**
      * operazione di aggiunta di un nuovo oggetto al dataBase del programma
      * @author niky
      */
     void remove(String nome, String natura){}
     /**
      * operazione per rimuovere un oggetto dal dataBase
      * @author niky
      */
     List<Object> search(String nome, String natura){
        int risultatoRicerca;
        return null;}//modificare mettendo al posto dei paramentri i campi per la ricerca
                                                     // il ritorno sarà una lista di oggetti
     /**
      * operazione per cercare un oggeto all'interno del dataBase
      * 
      * @return ritorna l'oggetto ricercato se presente nel dataBase
      */
     List<Object> load(){
        return null;} // restituirà una lista di oggetti
     /**
      * operazione per restituire alla view i dati del dataBase da mostrare all'utente
      * 
      * @return ritorna i dati richiesti
      * 
      * @author niky
      */
     void edit(Map<String,Object> contoDaModificare){}
     /**
      * operazione per cercare e modificare un oggetto all'interno del dataBase
      * 
      * @author niky
      */
     //nuova funzione filetr
}       
