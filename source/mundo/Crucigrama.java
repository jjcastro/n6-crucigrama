package mundo;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

public class Crucigrama
{
	public static final int HORIZONTALES = 0;
	public static final int VERTICALES = 1;
	
	public static final int PALABRAS = 0;
	public static final int DESCRIPCIONES = 1;
	
	private Properties propiedades;
	
	public int filas;
	public int columnas;
	
	public char[][] solucion;
	public char[][] casillas;
	public int[][][] indicePalabras;
	
	public String[][] palabrasH;
	public String[][] palabrasV;

	public Crucigrama(File archivo) throws Exception
	{
		cargar(archivo);
	}
	
	public Properties loadProperties(File archivo) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(archivo);
		try
		{
			prop.load(fis);
		}
		catch (Exception e)
		{
			throw new Exception("Formato inválido");
		}
		fis.close();
		return prop;
	}
	
	public void cargar(File archivo) throws Exception
	{
		propiedades = loadProperties(archivo);
		
		columnas = Integer.parseInt(propiedades.getProperty("crucigrama.columnas"));
		filas = Integer.parseInt(propiedades.getProperty("crucigrama.filas"));
		
		int palabrasHorizontales = Integer.parseInt(propiedades.getProperty("crucigrama.palabrasHorizontales"));
		int palabrasVerticales = Integer.parseInt(propiedades.getProperty("crucigrama.palabrasVerticales"));
		
		palabrasH = new String[palabrasHorizontales][2];
		palabrasV = new String[palabrasVerticales][2];
		
		casillas = new char[filas][columnas];
		solucion = new char[filas][columnas];
		indicePalabras = new int[filas][columnas][2]; //0 para horizontales, 
		
		// CONFIGURAR LA SOLUCIÓN SEGÚN EL ARCHIVO
		for(int i = 0; i < filas; i++)
		{
			int numFila = i+1;
			String fila = propiedades.getProperty("crucigrama.fila" + numFila);
			
			solucion[i] = (fila.trim()).toCharArray();
		}
		
		configurarIndicesHorizontales();
		configurarIndicesVerticales();
	}
	
	private void configurarIndicesHorizontales()
	{
		for(int i = 0; i < palabrasH.length; i++)
		{
			int numPalabra = i+1;
			
			palabrasH[i][DESCRIPCIONES] = propiedades.getProperty("crucigrama.Hdescripcion" + numPalabra);
			
			String[] coordenadas = (propiedades.getProperty("crucigrama.Hpalabra" + numPalabra)).split(";");
			
			int coordenadaX = Integer.parseInt(coordenadas[1].trim());
			int coordenadaY = Integer.parseInt(coordenadas[0].trim());
			
			for(int j = coordenadaX - 1; j < columnas; j++)
			{
				if(solucion[coordenadaY - 1][j] == '$')
				{
					indicePalabras[coordenadaY - 1][j][HORIZONTALES] = -1;
				}
				else
				{
					indicePalabras[coordenadaY - 1][j][HORIZONTALES] = numPalabra;
				}
			}
		}
	}
	
	private void configurarIndicesVerticales()
	{
		for(int i = 0; i < palabrasV.length; i++)
		{
			int numPalabra = i+1;
			
			palabrasV[i][DESCRIPCIONES] = propiedades.getProperty("crucigrama.Vdescripcion" + numPalabra);
			
			String[] coordenadas = (propiedades.getProperty("crucigrama.Vpalabra" + numPalabra)).split(";");
			
			int coordenadaX = Integer.parseInt(coordenadas[1].trim());
			int coordenadaY = Integer.parseInt(coordenadas[0].trim());
			
			for(int j = coordenadaY - 1; j < filas; j++)
			{
				if(solucion[j][coordenadaX - 1] == '$')
				{
					indicePalabras[j][coordenadaX - 1][VERTICALES] = -1;
				}
				else
				{
					indicePalabras[j][coordenadaX - 1][VERTICALES] = numPalabra;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try
		{
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
			    	if(abc.indicePalabras[i][j][HORIZONTALES] > 0 && abc.indicePalabras[i][j][HORIZONTALES] < 10)
			    	{
			    		System.out.print(" " + abc.indicePalabras[i][j][HORIZONTALES] + " ");
			    	}
			    	else
			    	{
			    		System.out.print(abc.indicePalabras[i][j][HORIZONTALES] + " ");
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
			    	if(abc.indicePalabras[i][j][VERTICALES] > 0 && abc.indicePalabras[i][j][VERTICALES] < 10)
			    	{
			    		System.out.print(" " + abc.indicePalabras[i][j][VERTICALES] + " ");
			    	}
			    	else
			    	{
			    		System.out.print(abc.indicePalabras[i][j][VERTICALES] + " ");
			    	}
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
