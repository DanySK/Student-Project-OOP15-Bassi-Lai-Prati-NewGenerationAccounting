package model;

import java.util.List;

public interface IOperations <L extends dataModel.IdataModel>{
    /**
     * Interfaccia delle operazioni comuni delle classi di anagrafica
     * @author niky
     */
     void add(List<Object> info);
     /**
      * operazione di aggiunta di un nuovo oggetto al dataBase del programma
      * @author niky
      */
     void remove();
     /**
      * operazione per rimuovere un oggetto dal dataBase
      * @author niky
      */
     Object search();
     /**
      * operazione per cercare un oggeto all'interno del dataBase
      * @return ritorna l'oggetto ricercato se presente nel dataBase
      */
     Object load();
     /**
      * operazione per restituire alla view i dati del dataBase da mostrare all'utente
      * @return ritorna i dati richiesti
      * @author niky
      */
     void edit();
     /**
      * operazione per cercare e modificare un oggetto all'interno del dataBase
      * @author niky
      */
}
