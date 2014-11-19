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
		
		// CONFIGURAR EL ÍNDICE DE PALABRAS
		for(int i = 0; i < filas; i++)
		{
			for(int j = 0; j < columnas; j++)
			{
					
			}
		}
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
