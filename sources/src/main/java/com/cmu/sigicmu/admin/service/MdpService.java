package com.cmu.sigicmu.admin.service;

import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.util.EnvoiMail;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Random;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class MdpService implements Serializable {

    private static final Random rand = new Random();
   
    private static final   String Xsi ="abcdefghijklmnopqrstuvwxyz";
    private static final String Gamm ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
    private static final String Iot = "1234567890";
    private static final String Zeta="&~#|`-_)('/?,;:.";
    private static String demo =""; 
    private static double i =0;
    
    private Agent agent;
   
    // Methode  pour generer aleatoirement des mot de passe 
   public void  generationMdpAndSend(){
       //création d'un fichier dico.txt à l'emplacement de la compilation ,taille illimité max essayé 8 Go;)
        FileWriter writer;
    try {
      writer = new FileWriter("password.txt");

        {
//nombre d 'iteration à executer afin de générer du code et un nombre de mots de
// passe __________;))))))))
	for (i=0;i<31415926L;i++){
	demo="";
//randomisation des caractères selon leur nombre par type définis ,entre six et dix caratères
        while ((demo.length() != 6)&& (demo.length() != 7)&& (demo.length() != 8)&& (demo.length() != 9)&& (demo.length() != 10)) {
//selection aleatoire du type de caractère puis selection parmis les differents modèles de caractères              
              int caracType=rand.nextInt(4);
           if (caracType ==0) {
	      int erzat=rand.nextInt(25);
              demo+=Xsi.charAt(erzat);
         } else if (caracType == 1) {
	      int erzat=rand.nextInt(9);
	      demo+=Iot.charAt(erzat);
         } else if (caracType==2) {
              int erzat=rand.nextInt(25);
              demo+=Gamm.charAt(erzat);
         }else if (caracType==3) {
              int erzat=rand.nextInt(15);
              demo+=Zeta.charAt(erzat);
	}
           
	}
//Ecrire le caractères selectionnée pour être mis en place comme caractère transcris 
	writer.write(demo+"\n");
}
        EnvoiMail envoiMail=new EnvoiMail();
        String destinaire=agent.getEmailProfessionnel();
        EnvoiMail.sendMessage("Mot de passe", demo, "destinaire");
}
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
}
  
}
   //cette methode permet de verifier si le mot de passe saisi par l'utilisateur est le meme qu'on lui a genere et si tel est le cas on enregistre dans la base
  public void verifierMdp(){ 
   
//     //codification et cryptage du du mot de passe generer et envoyer a lutilisateur
//    String mdpsended=mdp.getInsertDate()+demo+mdp.getDateFinChangement();
//    
//       byte[]mdpsendedByte=mdpsended.getBytes();
//       Chiffrement cf=new Chiffrement(mdpsendedByte);
//        byte[]mdpsendedcryp=mdpsended.getBytes();
//    //recuperation dela chaine envoyé par lutilisateur et le crypter
//     String mdprecup= mdp.getInsertDate()+ agent.getUtilisateur().getNewMdp()+mdp.getDateFinChangement();
//      byte[]mdprecupByte=mdprecup.getBytes();
//       Chiffrement cff=new Chiffrement(mdprecupByte);
//       if(mdpsendedByte==mdprecupByte){
//        
//           mdp.setCleControle(mdprecup);
//           agent.getUtilisateur().setMotDePasse(demo);
//         
//       }
//       else{
//           System.out.println("Le mot de passe saisi n'est pas le meme que celui envoyé");
//       }
       
}
  
  public static void sauvegardeClePrivee(PrivateKey clePrivee, String nomFichier) throws InvalidKeySpecException {
	RSAPrivateKeySpec specification = null;
	try {
	    KeyFactory usine = KeyFactory.getInstance("RSA");
	    specification = usine.getKeySpec(clePrivee, RSAPrivateKeySpec.class);
	} catch(NoSuchAlgorithmException e) {
	    System.err.println("Algorithme RSA inconnu : " + e);
	    System.exit(-1);
	} catch(InvalidKeySpecException e) {
	    System.err.println("Clé incorrecte : " + e);
	    System.exit(-1);  
	}
 
	try {
            
	    ObjectOutputStream fichier = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomFichier)));
	    fichier.writeObject(specification.getModulus());
	    fichier.writeObject(specification.getPrivateExponent());
	    fichier.close();	
	} catch(IOException e) {
	    System.err.println("Erreur lors de la sauvegarde de la clé : " + e);
	    System.exit(-1);
	}
    }
 
    /**
     * Lecture d'une clé privée depuis un fichier.
     * @param nomFichier le nom du fichier contenant la clé privée
     * @return la clé privée
     */
  
  
  public static void sauvegardeClePublique(PublicKey clePublique, String nomFichier) {
	RSAPublicKeySpec specification = null;
	try {
	    KeyFactory usine = KeyFactory.getInstance("RSA");
	    specification = usine.getKeySpec(clePublique, RSAPublicKeySpec.class);
	} catch(NoSuchAlgorithmException e) {
	    System.err.println("RSA inconnu : " + e);
	    System.exit(-1);
	} catch(InvalidKeySpecException e) {
	    System.err.println("Cle incorrecte : " + e);
	    System.exit(-1);  
	}
 
	try {
	    ObjectOutputStream fichier = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomFichier)));
	    fichier.writeObject(specification.getModulus());
	    fichier.writeObject(specification.getPublicExponent());
	    fichier.close();	
	} catch(IOException e) {
	    System.err.println("Erreur lors de la sauvegarde de la clé : " + e);
	    System.exit(-1);
	}
    }
  
    public static PrivateKey lectureClePrivee(String nomFichier) {
	BigInteger modulo = null, exposant = null;
	try {
	    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomFichier)));	    
	    modulo = (BigInteger) ois.readObject();
	    exposant = (BigInteger) ois.readObject();
	} catch(IOException e) {
	    System.err.println("Erreur lors de la lecture de la clé : " + e);
	    System.exit(-1);
	} catch(ClassNotFoundException e) {
	    System.err.println("Fichier de cle incorrect : " + e);
	    System.exit(-1);
	}
 
	PrivateKey clePrivee = null;
	try {
	    RSAPrivateKeySpec specification = new RSAPrivateKeySpec(modulo, exposant);
	    KeyFactory usine = KeyFactory.getInstance("RSA");
	    clePrivee = usine.generatePrivate(specification);
	} catch(NoSuchAlgorithmException e) {
	    System.err.println("Algorithme RSA inconnu : " + e);
	    System.exit(-1);
	} catch(InvalidKeySpecException e) {
	    System.err.println("Spécification incorrecte : " + e);
	    System.exit(-1);
	}
	return clePrivee;
    }
 
  
    public static String getDemo() {
        return demo;
    }

    public static void setDemo(String demo) {
        MdpService.demo = demo;
    }

}
      
 

   






















