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
		indicePalabras = new int[filas][columnas][2];
		
		// CONFIGURAR LA SOLUCIÓN SEGÚN EL ARCHIVO
		for(int i = 0; i < filas; i++)
		{
			int numFila = i+1;
			String fila = propiedades.getProperty("crucigrama.fila" + numFila);
			
			solucion[i] = (fila.trim()).toCharArray();
		}
		
		configurarPalabrasHorizontales();
		configurarPalabrasVerticales();
	}
	
	private void configurarPalabrasHorizontales()
	{
		for(int i = 0; i < palabrasH.length; i++)
		{
			String palabra = "";
			int numPalabra = i+1;

			String[] coordenadas = (propiedades.getProperty("crucigrama.Hpalabra" + numPalabra)).split(";");
			
			int coordenadaX = Integer.parseInt(coordenadas[1].trim());
			int coordenadaY = Integer.parseInt(coordenadas[0].trim());
			
			boolean stop = false;
		
			for(int j = coordenadaX - 1; j < columnas && !stop; j++)
			{
				char letra = solucion[coordenadaY - 1][j];
				
				if(letra == '$')
				{
					indicePalabras[coordenadaY - 1][j][HORIZONTALES] = 0;
					stop = true;
				}
				else
				{
					indicePalabras[coordenadaY - 1][j][HORIZONTALES] = numPalabra;
					palabra += letra;
				}
			}
			
			palabrasH[i][PALABRAS] = palabra;
			palabrasH[i][DESCRIPCIONES] = propiedades.getProperty("crucigrama.Hdescripcion" + numPalabra);
		}
	}
	
	private void configurarPalabrasVerticales()
	{
		for(int i = 0; i < palabrasV.length; i++)
		{
			String palabra = "";
			int numPalabra = i+1;

			String[] coordenadas = (propiedades.getProperty("crucigrama.Vpalabra" + numPalabra)).split(";");
			
			int coordenadaX = Integer.parseInt(coordenadas[1].trim());
			int coordenadaY = Integer.parseInt(coordenadas[0].trim());
			
			boolean stop = false;
			
			for(int j = coordenadaY - 1; j < filas && !stop; j++)
			{
				char letra = solucion[j][coordenadaX - 1];
				
				if(letra == '$')
				{
					indicePalabras[j][coordenadaX - 1][VERTICALES] = 0;
					stop = true;
				}
				else
				{
					indicePalabras[j][coordenadaX - 1][VERTICALES] = numPalabra;
					palabra += letra;
				}
			}
			
			palabrasV[i][PALABRAS] = palabra;
			palabrasV[i][DESCRIPCIONES] = propiedades.getProperty("crucigrama.Vdescripcion" + numPalabra);
		}
	}
}
