package mg.cufp.isr3.entite;

import java.util.List;

public class Categorie {
    private Integer id;
    private String libelle;
    private List<Produit> produits;
    
    public Categorie() {
	
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
