package mundo;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

public class Crucigrama
{
	private Properties propiedades;
	
	private int numFilas;
	private int numColumnas;

	/**
	 * Construye un nuevo crucigrama
	 * @param filas nœmero de filas
	 * @param columnas nœmero de columnas
	 */
	public Crucigrama(int filas, int columnas)
	{
		numFilas = filas;
		numColumnas = columnas;
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
	
}
