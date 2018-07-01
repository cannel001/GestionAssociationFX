package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.vue.VueAdherentControlleur;
import com.objis.gestassociation.vue.VueBureauControlleur;
import com.objis.gestassociation.vue.VueCommissaireControleur;
import com.objis.gestassociation.vue.VueCotisationAnnuelleControlleur;
import com.objis.gestassociation.vue.VueCotisationControlleur;
import com.objis.gestassociation.vue.VueDiversControlleur;
import com.objis.gestassociation.vue.VueEntreeSortieControlleur;
import com.objis.gestassociation.vue.VueEvenementControlleur;
import com.objis.gestassociation.vue.VueRencontreControlleur;
import com.objis.gestassociation.vue.VueVieSocialeControlleur;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class VueCentre {
	
	private String url;
	private GestionAssociation association;
	
	public VueCentre(String url,GestionAssociation association) {
		
		this.association=association;
		this.url=url;
		
	}
	
	//methode permettant d'afficher la vue
	public void afficherVue() {
		
		new Thread(()->{
			Platform.runLater(()->{
				
				//charger les controller
				if (url.equals("../vue/VueAdherent.fxml")) {
					
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());
						VueAdherentControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if(url.equals("../vue/VueAccueil.fxml")) {
					
					try {
						AnchorPane parent=new AnchorPane();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(AnchorPane)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());
						System.out.println("okdkdf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				} else if(url.equals("../vue/VueBureau.fxml")) {
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());
						VueBureauControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} 
				
				else if(url.equals("../vue/VueCotisation.fxml")) {
					
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());		
						VueCotisationControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if(url.equals("../vue/VueCotisationAnnuelle.fxml")) {
					
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());		
						VueCotisationAnnuelleControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}  else if(url.equals("../vue/VueDivers.fxml")) {
					
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());		
						VueDiversControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if(url.equals("../vue/VueVieSociale.fxml")) {
					
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());		
						VueVieSocialeControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if(url.equals("../vue/VueRencontre.fxml")) {
					
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());		
						VueRencontreControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if(url.equals("../vue/VueEvenement.fxml")) {
					
					try {
						VBox parent=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						parent=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(parent);	
						association.setMenuPrincipal(association.getMenuPrincipal());
						VueEvenementControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if(url.equals("../vue/VueEntreeSortie.fxml")) {
					
					try {
						VBox root=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						root=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(root);	
						association.setMenuPrincipal(association.getMenuPrincipal());
						VueEntreeSortieControlleur controlleur=loader.getController();
						controlleur.setGestionAssociation(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if(url.equals("../vue/VueComissaireCompte.fxml")) {
					
					try {
						VBox root=new VBox();
						FXMLLoader loader=new FXMLLoader();
						loader.setLocation(getClass().getResource(url));
						root=(VBox)loader.load();
						association.getMenuPrincipal().setCenter(root);	
						association.setMenuPrincipal(association.getMenuPrincipal());
						VueCommissaireControleur controleur=loader.getController();
						controleur.setGestion(association);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
			});
		}).start();
		
	}

}
