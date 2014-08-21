package mg.cufp.isr3.entite;


public class DetailCommande {
    private Integer id;
    private Commande commande;
    private Produit produit;
    private Integer quantite;
    private Float prixJour;
    
    public DetailCommande() {
	
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getPrixJour() {
        return prixJour;
    }

    public void setPrixJour(Float prixJour) {
        this.prixJour = prixJour;
    }
}
