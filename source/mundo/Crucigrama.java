package mundo;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

public class Crucigrama
{
	private Properties propiedades;
	
	public int numFilas;
	public int numColumnas;

	/**
	 * Construye un nuevo crucigrama
	 * @param filas nœmero de filas
	 * @param columnas nœmero de columnas
	 */
	public Crucigrama()
	{
		char[][] solucion = obtenerSolucion();
		
		numColumnas = solucion[0].length;
		numFilas = solucion.length;
		
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
			throw new Exception("Formato inv‡lido");
		}
		fis.close();
		return prop;
	}
	
	public char[][] obtenerSolucion()
	{
		try
		{
		propiedades = loadProperties(new File("./data/crucigrama.properties"));
		}
		catch(Exception e)
		{
			System.out.println(":(");
			System.exit(0);
		}
		
		int columnas = Integer.parseInt(propiedades.getProperty("crucigrama.columnas"));
		int filas = Integer.parseInt(propiedades.getProperty("crucigrama.filas"));
		
		char[][] retorno = new char[filas][columnas];
		return retorno;
		
	}
	
	public static void main(String[] args) {
		Crucigrama dummy = new Crucigrama();
		System.out.println("" + dummy.numColumnas + " " + dummy.numFilas);
	}
	
}
