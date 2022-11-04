import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SocketCliente
 {
 	//valores de entrada y numeros que se enviaran
 	public Scanner entrada=new Scanner(System.in);
 	public int n;
 	
     public static void main (String [] args)
     {
         new SocketCliente();
     }
     
     public SocketCliente()
     {
     
 	   
 	  
         try
         {
         
             //Creamos el socket y se piden los valores
             Socket socket = new Socket ("localhost", 15557);
             System.out.println ("conectado");
		System.out.println ("Inserta un valor de 1 - 9 a enviar ");
		System.out.println ("Si es 0 saldra del programa ");
             socket.setSoLinger (true, 10);
             do{ 
             n=entrada.nextInt();
		String numcadena=n+"";
             //Nos preparamos para enviar los datos
             DataOutputStream bufferSalida =
               new DataOutputStream (socket.getOutputStream());

             //Usamos la funcion Dato socket la cual nos ayuda para comunicarnos eficientemente con C
             DatoSocket aux = new DatoSocket (numcadena);
             aux.writeObject (bufferSalida);
             System.out.println ("Mensaje de Cliente Java Enviado " + aux.toString());
                          //............................................
             //Se prepara para obtener los valores de entrada, para asi con la funcion dato socket creamos el objeto para que lea el valor
             //recibido
             DataInputStream bufferEntrada =
                new DataInputStream (socket.getInputStream());
             
             DatoSocket dato = new DatoSocket("");
             dato.readObject(bufferEntrada);
             System.out.println ("Mensaje de Cliente Java Recibido " + dato.toString());


 
   
 //............................................  
             
             
            }while(n!=0);
             socket.close();
             
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
         
     }
}
