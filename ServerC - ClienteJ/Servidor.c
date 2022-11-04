#include "Socket_Servidor.h"
#include "Socket.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h> 
#include <unistd.h> 
#include <arpa/inet.h>
main ()
{
	int Socket_Servidor;
	int Socket_Cliente;
	int valor;
   int Aux;
   int Longitud_Cadena;
	char Cadena[100];

//Se abre el socket
	Socket_Servidor = Abre_Socket_Inet ("cpp_java");
	if (Socket_Servidor == -1)
	{
		printf ("No se puede abrir socket servidor\n");
		exit (-1);
	}

//Esperamos al cliente
	Socket_Cliente = Acepta_Conexion_Cliente (Socket_Servidor);
	if (Socket_Servidor == -1)
	{
		printf ("No se puede abrir socket de cliente\n");
		exit (-1);
	}

//Aqui inicia el while para que se conecte y se quede en el bucle recibiendo los datos del cliente y mandandoselos
   while(1)  {
   Longitud_Cadena = 5;
   strcpy (Cadena, "Adios");

	//Leemos los datos del cliente
   Lee_Socket (Socket_Cliente, (char *)&Aux, sizeof(Longitud_Cadena));

   //Lo transformamos en base a la longitud a formato hardware
   Longitud_Cadena = ntohl(Aux);

 
	Lee_Socket (Socket_Cliente, Cadena, Longitud_Cadena);
   printf ("El Servidor recibio: %s\n", Cadena);
//Aqui se suma en 1
valor=atoi(Cadena)+1;
//itoa(valor,Cadena,10);
sprintf(Cadena,"%d",valor);
//............................................
 //Transformamos el dato a formato red
   Aux = htonl (Longitud_Cadena);

 //Se envia
   Escribe_Socket (Socket_Cliente, (char *)&Aux, sizeof(Longitud_Cadena));
  Escribe_Socket (Socket_Cliente, Cadena, Longitud_Cadena);
   printf ("El Servidor Enviara: %s\n", Cadena);
   
 //............................................  


}
	close (Socket_Cliente);
	close (Socket_Servidor);
}
