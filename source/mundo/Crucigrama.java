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

	/**
	 * Construye un nuevo crucigrama
	 * @param filas nœmero de filas
	 * @param columnas nœmero de columnas
	 */
	public Crucigrama(File archivo) throws Exception
	{
		cargar(archivo);
		
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
	
	public void cargar(File archivo) throws Exception
	{
		propiedades = loadProperties(archivo);
		
		int columnas = Integer.parseInt(propiedades.getProperty("crucigrama.columnas"));
		int filas = Integer.parseInt(propiedades.getProperty("crucigrama.filas"));
		
		solucion = new char[filas][columnas];
		
		for(int i = 0; i < filas; i++)
		{
			int numFila = i+1;
			String fila = propiedades.getProperty("crucigrama.fila" + numFila);
			
			solucion[i] = (fila.trim()).toCharArray();
		}
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
