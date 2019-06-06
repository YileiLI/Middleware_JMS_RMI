package slack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


// 这个class 可能用不到了？
public class Resultat implements Serializable{
	transient int val = 1000;
	String mess = "Coucou";
	public String toString(){
		return "val = " + val + "mess = " + mess;
	}
	public void ecrireFichier(){
		try {
			FileOutputStream fo=new FileOutputStream(new File("essaiResultat"));
			fo.write(new byte[]{'c','o','u','c','o','u'});
			// si on n a pas de permission de write fichier, ceci write ne fonctionnera pas 
			// (ce qui implique dès lors que nous avons un securityManager activé)
			// sauf si on grant explicitement l'ecriture dans un fichier, ou qu'on a
			// mis un grand all permission qui n'est pas une bonne idée
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

class ResultatFils extends Resultat implements Serializable{
	private int secret=56789;
	ResultatFils(){
		super.val=554;
		super.mess="message du petit fils";
	}
	public String toString(){
		this.ecrireFichier();
		return super.toString()+"le champ privé " + secret;
		
	}

}
