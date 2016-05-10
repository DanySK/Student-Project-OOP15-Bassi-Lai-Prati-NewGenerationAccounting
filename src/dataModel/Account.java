package dataModel;

import dataEnum.Natures;

/**
 * classe per la gestione del singolo conto
 * 
 * @author niky
 *
 */
public class Account {
    private Natures natura;
    private String nome;
    private long avere;
    private long dare;

    public Account(String nome, Natures natura, long dare, long avere) {
        this.nome = nome;
        this.natura = natura;
        this.avere = avere;
        this.dare = dare;
    }

    public Natures getNature() {
        return natura;
    }

    public String getName() {
        return nome;
    }

    public void setNature(Natures natura) {
        this.natura = natura;
    }

    public long getAvere() {
        return avere;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public void setAvere(long avere) {
        this.avere = avere;
    }

    public long getDare() {
        return dare;
    }

    public void setDare(long dare) {
        this.dare = dare;
    }
}