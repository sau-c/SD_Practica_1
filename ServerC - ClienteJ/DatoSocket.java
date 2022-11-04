import java.io.*;

public class DatoSocket implements Serializable
 {
   //se obtiene la cadena y su longitud
   public DatoSocket (String cadena)
   {
   
      if (cadena != null)
      {
         c = cadena.length();
         d = cadena;
      }
   }

  
   public int c = 0;
     

   public String d = "";
     
//meter resultado como string
   public String toString ()
   {
       String resultado;
       resultado = Integer.toString(c) + " -- " + d;
       return resultado;
   }

//Metodo para enviar los archivos
   public void writeObject(java.io.DataOutputStream out)
         throws IOException
     {
         // Se envía la longitud de la cadena + 1 por el \0 necesario en C
         out.writeInt (c+1);

         // Se envía la cadena como bytes.
         out.writeBytes (d);

         // Se envía el \0 del final
         out.writeByte ('\0');
     }
    
     //Metodo para leer los archivos que entran
     public void readObject(java.io.DataInputStream in)
     throws IOException
     {
         // Se lee la longitud de la cadena y se le resta 1 para eliminar el \0 que
         // nos envía C.
         c = in.readInt() - 1;
         
         // Array de bytes auxiliar para la lectura de la cadena.
         byte [] aux = null;
         
         aux = new byte[c];    // Se le da el tamaño 
         in.read(aux, 0, c);   // Se leen los bytes
         d = new String (aux); // Se convierten a String
         in.read(aux,0,1);     // Se lee el \0
     }
}
