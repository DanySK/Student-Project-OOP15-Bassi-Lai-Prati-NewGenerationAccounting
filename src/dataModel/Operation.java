package dataModel;

public class Operation {
    private Account conto;
    private float dare;
    private float avere;
    
    public Operation(Account conto, float dare, float avere){
        this.conto = conto;
        this.dare = dare;
        this.avere = avere;
    }

    public Account getConto() {
        return conto;
    }

    public void setConto(Account conto) {
        this.conto = conto;
    }

    public float getDare() {
        return dare;
    }

    public void setDare(float dare) {
        this.dare = dare;
    }

    public float getAvere() {
        return avere;
    }

    public void setAvere(float avere) {
        this.avere = avere;
    }
    
}
