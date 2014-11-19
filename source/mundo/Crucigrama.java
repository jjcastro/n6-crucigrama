package mundo;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

public class Crucigrama
{
	private Properties propiedades;
	
	public int numFilas;
	public int numColumnas;
	
	public char[][] solucion;
	public char[][] casillas;
	public int[][][] palabras;

	/**
	 * Construye un nuevo crucigrama
	 * @param filas número de filas
	 * @param columnas número de columnas
	 */
	public Crucigrama(File archivo) throws Exception
	{
		cargar(archivo);
		
		casillas = new char[numFilas][numColumnas];
		solucion = new char[numFilas][numColumnas];
		palabras = new int[numFilas][numColumnas][2];
		
		// CONFIGURAR LA SOLUCIÓN SEGÚN EL ARCHIVO
		for(int i = 0; i < numFilas; i++)
		{
			int numFila = i+1;
			String fila = propiedades.getProperty("crucigrama.fila" + numFila);
			
			solucion[i] = (fila.trim()).toCharArray();
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
		
		numColumnas = Integer.parseInt(propiedades.getProperty("crucigrama.columnas"));
		numFilas = Integer.parseInt(propiedades.getProperty("crucigrama.filas"));
	}
	
	public static void main(String[] args) {
		try
		{
			Crucigrama abc = new Crucigrama(new File("./data/crucigrama.properties"));
			
			for (int i = 0; i < abc.numFilas; i++)
			{
			    for (int j = 0; j < abc.numColumnas; j++)
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
