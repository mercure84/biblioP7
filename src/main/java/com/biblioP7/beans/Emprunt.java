package com.biblioP7.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable= false, unique = true)
    private int id;

    @Column(name="debut_date")
    private Date debutDate;

    @Column(name="fin_date")
    private Date finDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="membre_id")
    private Membre membre;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="livre_id")
    private Livre livre;

    @Column(name="a_ete_prolonge")
    private boolean aEteProlonge = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(Date debutDate) {
        this.debutDate = debutDate;
    }

    public Date getFinDate() {
        return finDate;
    }

    public void setFinDate(Date finDate) {
        this.finDate = finDate;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public boolean isaEteProlonge() {
        return aEteProlonge;
    }

    public void setaEteProlonge(boolean aEteProlonge) {
        this.aEteProlonge = aEteProlonge;
    }

    public Emprunt() {
            }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", debutDate=" + debutDate +
                ", finDate=" + finDate +
                ", membre=" + membre +
                ", livre=" + livre +
                ", aEteProlonge=" + aEteProlonge +
                '}';
    }




    public Emprunt(Date debutDate, Date finDate, Membre membre, Livre livre) {
        this.debutDate = debutDate;
        this.finDate = finDate;
        this.membre = membre;
        this.livre = livre;
    }



}
