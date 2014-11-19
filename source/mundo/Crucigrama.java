package mundo;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

public class Crucigrama
{
	private Properties propiedades;
	
	public int filas;
	public int columnas;
	
	public int palabrasHorizontales;
	public int palabrasVerticales;
	
	public char[][] solucion;
	public char[][] casillas;
	public int[][][] indicePalabras;
	
	public String[] descripcionH;
	public String[] descripcionV;

	/**
	 * Construye un nuevo crucigrama
	 * @param filas número de filas
	 * @param columnas número de columnas
	 */
	public Crucigrama(File archivo) throws Exception
	{
		cargar(archivo);
	}
	
	/**
	 * Carga las propiedades desde un archivo
	 * @param archivo
	 * @return
	 * @throws Exception
	 */
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
		
		palabrasHorizontales = Integer.parseInt(propiedades.getProperty("crucigrama.palabrasHorizontales"));
		palabrasVerticales = Integer.parseInt(propiedades.getProperty("crucigrama.palabrasVerticales"));
		
		descripcionH = new String[palabrasHorizontales];
		descripcionV = new String[palabrasVerticales];
		
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
		
		// CONFIGURAR LAS PALABRAS HORIZONTALES
		for(int i = 0; i < palabrasHorizontales; i++)
		{
			int numPalabra = i+1;
			
			descripcionH[i] = propiedades.getProperty("crucigrama.Hdescripcion" + numPalabra);
			
			String[] coordenadas = (propiedades.getProperty("crucigrama.Hpalabra" + numPalabra)).split(";");
			
			int coordenadaX = Integer.parseInt(coordenadas[1].trim());
			int coordenadaY = Integer.parseInt(coordenadas[0].trim());
			
			for(int j = coordenadaX - 1; j < columnas; j++)
			{
				if(solucion[coordenadaY-1][j] == '$')
				{
					indicePalabras[coordenadaY-1][j][0] = -1;
				}
				else
				{
					indicePalabras[coordenadaY-1][j][0] = numPalabra;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try
		{
			Crucigrama abc = new Crucigrama(new File("./data/crucigrama.properties"));
			
			for (int i = 0; i < abc.filas; i++)
			{
			    for (int j = 0; j < abc.columnas; j++)
			    {
			        System.out.print(abc.solucion[i][j] + " ");
			    }
			    
			    System.out.print("\n");
			}
			
			for (int i = 0; i < abc.filas; i++)
			{
			    for (int j = 0; j < abc.columnas; j++)
			    {
			        System.out.print(abc.indicePalabras[i][j][0] + " ");
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
