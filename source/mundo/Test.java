package mundo;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		try
		{
			Crucigrama abc = new Crucigrama(new File("./data/crucigrama2.properties"));
			
			System.out.println(abc.solucion[0][10] == '$');
			
			// IMPRIMIR SOLUCIÓN
			for(int i = 0; i < abc.filas; i++)
			{
			    for (int j = 0; j < abc.columnas; j++)
			    {
			        System.out.print(" " + abc.solucion[i][j] + " ");
			    }
			    
			    System.out.print("\n");
			}

			System.out.print("\n");
			
			// IMPRIMIR ÍNDICES HORIZONTALES
			for(int i = 0; i < abc.filas; i++)
			{
			    for (int j = 0; j < abc.columnas; j++)
			    {
			    	if(abc.indicePalabras[i][j][Crucigrama.HORIZONTALES] < 10)
			    	{
			    		System.out.print(" " + abc.indicePalabras[i][j][Crucigrama.HORIZONTALES] + " ");
			    	}
			    	else
			    	{
			    		System.out.print(abc.indicePalabras[i][j][Crucigrama.HORIZONTALES] + " ");
			    	}
			    }
			    
			    System.out.print("\n");
			}
			
			System.out.print("\n");
			
			// IMPRIMIR ÍNDICES VERTICALES
			for(int i = 0; i < abc.filas; i++)
			{
			    for (int j = 0; j < abc.columnas; j++)
			    {
			    	if(abc.indicePalabras[i][j][Crucigrama.VERTICALES] < 10)
			    	{
			    		System.out.print(" " + abc.indicePalabras[i][j][Crucigrama.VERTICALES] + " ");
			    	}
			    	else
			    	{
			    		System.out.print(abc.indicePalabras[i][j][Crucigrama.VERTICALES] + " ");
			    	}
			    }
			    
			    System.out.print("\n");
			}
			
			System.out.print("\n");
			
			// IMPRIMIR PALABRAS VERTICALES
			for(int i = 0; i < abc.palabrasV.length; i++)
			{
				System.out.println(abc.palabrasV[i][Crucigrama.PALABRAS] + ": " + abc.palabrasV[i][Crucigrama.DESCRIPCIONES]);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
