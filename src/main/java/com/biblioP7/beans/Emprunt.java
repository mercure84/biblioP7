package com.biblioP7.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="emprunt")
@AllArgsConstructor @NoArgsConstructor
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
    private boolean isProlonge = false;

    @Column(name="a_ete_rendu")
    private boolean isRendu = false;


}
