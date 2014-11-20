package mundo;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		try
		{
			
			//dfsdf
			Crucigrama abc = new Crucigrama(new File("./data/crucigrama.properties"));
			
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
			
			System.out.print("\nPALABRAS VERTICALES:\n");
			
			// IMPRIMIR PALABRAS VERTICALES
			for(int i = 0; i < abc.palabrasV[0].length; i++)
			{
				System.out.println("" + (i+1) + ". " + abc.palabrasV[Crucigrama.PALABRAS][i] + ": " + abc.palabrasV[Crucigrama.DESCRIPCIONES][i]);
			}
			
			System.out.print("\nPALABRAS HORIZONTALES:\n");
			
			// IMPRIMIR PALABRAS VERTICALES
			for(int i = 0; i < abc.palabrasH[0].length; i++)
			{
				System.out.println("" + (i+1) + ". " + abc.palabrasH[Crucigrama.PALABRAS][i] + ": " + abc.palabrasH[Crucigrama.DESCRIPCIONES][i]);
			}
			
			abc.jugar(0, 0, "D");
			abc.jugar(1, 0, "E");
			abc.jugar(2, 0, "S");
			abc.jugar(3, 0, "C");
			abc.jugar(4, 0, "O");
			abc.jugar(5, 0, "N");
			abc.jugar(6, 0, "F");
			abc.jugar(7, 0, "I");
			abc.jugar(8, 0, "A");
			abc.jugar(9, 0, "R");
			
			System.out.print("\n");
			
			System.out.print(abc.validarHorizontal(14));
			
			System.out.print("\n\n");
			
			// IMPRIMIR CASILLAS
			for(int i = 0; i < abc.filas; i++)
			{
			    for (int j = 0; j < abc.columnas; j++)
			    {
			    	char letra = abc.casillas[i][j];
			    	if(letra == '\u0000') letra = 'X';
			    	System.out.print(" " + letra + " ");
			    }
			    
			    System.out.print("\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
