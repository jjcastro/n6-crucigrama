package mundo;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

/**
 * Representa un juego de crucigrama
 * @author jj.castro10
 *
 */
public final class Crucigrama
{
	/**
	 * Constante para identificar palabras horizontales en las matrices
	 */
	public static final int HORIZONTALES = 0;
	/**
	 * Constante para identificar palabras verticales en las matrices
	 */
	public static final int VERTICALES = 1;
	
	/**
	 * Constante para identificar las palabras en las matrices
	 */
	public static final int PALABRAS = 0;
	/**
	 * Constante para identificar las descripciones en las matrices
	 */
	public static final int DESCRIPCIONES = 1;
	
	/**
	 * Propiedades del crucigrama
	 */
	private Properties propiedades;
	
	/**
	 * Número de filas
	 */
	private int filas;
	/**
	 * Número de columnas
	 */
	private int columnas;
	
	/**
	 * Matriz con la solución del crucigrama
	 */
	private char[][] solucion;
	/**
	 * Matriz con las casillas vacías del crucigrama
	 */
	private char[][] casillas;
	/**
	 * Matriz con las posiciones y números de las palabras horizontales y verticales
	 */
	private int[][][] indicePalabras;
	
	/**
	 * Matriz con las palabras horizontales y sus descripciones
	 */
	private String[][] palabrasH;
	/**
	 * Matriz con las palabras verticales y sus descripciones
	 */
	private String[][] palabrasV;
	
	/**
	 * Crea un nuevo crucigrama
	 * @param archivo archivo del que se obtendrán las propiedades
	 * @throws Exception lanza excepción si no es posible leer el archivo
	 */
	public Crucigrama(File archivo) throws Exception
	{
		cargar(archivo);
	}
	
	/**
	 * Valida una palabra horizontal
	 * @param palabra número de la palabra a validar
	 * @return true si la palabra es correcta, false de lo contrario
	 */
	public boolean validarHorizontal(int palabra)
	{
		String palabraEnCasillas = "";
		boolean finalizado = false;
		
		for(int i = 0; i < filas && !finalizado; i++)
		{
			for(int j = 0; j < columnas && !finalizado; j++)
			{
				if(indicePalabras[i][j][HORIZONTALES] == palabra)
				{
					palabraEnCasillas += casillas[i][j];
				}
				else if(indicePalabras[i][j][HORIZONTALES] == palabra + 1)
				{
					finalizado = true;
				}
			}
		}
		
		return palabrasH[PALABRAS][palabra-1].equals(palabraEnCasillas);
	}
	
	/**
	 * Valida una palabra vertical
	 * @param palabra número de la palabra a validar
	 * @return true si la palabra es correcta, false de lo contrario
	 */
	public boolean validarVertical(int palabra)
	{
		String palabraEnCasillas = "";
		boolean finalizado = false;
		
		for(int i = 0; i < columnas && !finalizado; i++)
		{
			for(int j = 0; j < filas && !finalizado; j++)
			{
				if(indicePalabras[j][i][VERTICALES] == palabra)
				{
					palabraEnCasillas += casillas[j][i];
				}
				else if(indicePalabras[j][i][VERTICALES] == palabra + 1)
				{
					finalizado = true;
				}
			}
		}
		
		return palabrasV[PALABRAS][palabra-1].equals(palabraEnCasillas);
	}
	
	/**
	 * Inserta un valor en las casillas del juego
	 * @param fila fila de la entrada
	 * @param columna columna de la entrada
	 * @param entrada valor a entrar
	 */
	public void jugar(int fila, int columna, String entrada)
	{
		char[] letra = entrada.toUpperCase().toCharArray();
		if(letra.length == 1) 
			casillas[fila][columna] = letra[0];
		else 
			casillas[fila][columna] = '!';
	}
	
	/**
	 * Retorna el numero de palabras verticales
	 * @return numero de palabras verticales
	 */
	public int darNumeroDePalabrasV()
	{
		return palabrasV[0].length;
	}
	
	/**
	 * Retorna el numero de palabras horizontales
	 * @return numero de palabras horizontales
	 */
	public int darNumeroDePalabrasH()
	{
		return palabrasH[0].length;
	}
	
	/**
	 * Retorna las casillas del juego
	 * @return casillas del juego
	 */
	public char[][] darCasillas()
	{
		return casillas;
	}
	
	/**
	 * Retorna una matriz con la solución del juego
	 * @return solución del juego
	 */
	public char[][] darSolucion()
	{
		return solucion;
	}
	
	/**
	 * Retorna una matriz con los números y posiciones de las palabras
	 * @return números y posiciones de las palabras
	 */
	public int[][][] darIndices()
	{
		return indicePalabras;
	}
	
	/**
	 * Retorna un arreglo con las descripciones de las palabras horizontales
	 * @return descripciones de las palabras horizontales
	 */
	public String[] darDescripcionesPalabrasH()
	{
		return palabrasH[DESCRIPCIONES];
	}
	
	/**
	 * Retorna un arreglo con las descripciones de las palabras verticales
	 * @return descripciones de las palabras verticales
	 */
	public String[] darDescripcionesPalabrasV()
	{
		return palabrasV[DESCRIPCIONES];
	}
	
	/**
	 * Carga las propiedades de un archivo, inicializa las propiedades y crea el crucigrama
	 * @param archivo archivo a cargar
	 * @throws Exception lanza excepción si el archivo no pudo ser cargado
	 */
	public void cargar(File archivo) throws Exception
	{
		propiedades = loadProperties(archivo);
		
		columnas = Integer.parseInt(propiedades.getProperty("crucigrama.columnas"));
		filas = Integer.parseInt(propiedades.getProperty("crucigrama.filas"));
		
		int palabrasHorizontales = Integer.parseInt(propiedades.getProperty("crucigrama.palabrasHorizontales"));
		int palabrasVerticales = Integer.parseInt(propiedades.getProperty("crucigrama.palabrasVerticales"));
		
		palabrasH = new String[2][palabrasHorizontales];
		palabrasV = new String[2][palabrasVerticales];
		
		casillas = new char[filas][columnas];
		solucion = new char[filas][columnas];
		indicePalabras = new int[filas][columnas][2];
		
		// CONFIGURAR LA SOLUCIÓN SEGÚN EL ARCHIVO
		for(int i = 0; i < filas; i++)
		{
			int numFila = i+1;
			String fila = propiedades.getProperty("crucigrama.fila" + numFila).trim();
			char[] letras = fila.toCharArray();
			
			for(int j = 0; j < columnas; j++)
			{
				char letra = letras[j];
				
				solucion[i][j] = letra;
				if(letra == '$') casillas[i][j] = letra;
			}
		}
		
		configurarPalabrasHorizontales();
		configurarPalabrasVerticales();
	}
	
	/**
	 * Carga las propiedades de un archivo
	 * @param archivo archivo a cargar
	 * @return propiedades cargadas 
	 * @throws Exception lanza excepción si el archivo no pudo ser cargado
	 */
	private Properties loadProperties(File archivo) throws Exception
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
	
	/**
	 * Configura las palabras horizontales
	 */
	private void configurarPalabrasHorizontales()
	{
		for(int i = 0; i < palabrasH[0].length; i++)
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
			
			String posicion = "" + coordenadaY + ":" + coordenadaX;
			
			palabrasH[PALABRAS][i] = palabra;
			palabrasH[DESCRIPCIONES][i] = posicion + " - " + propiedades.getProperty("crucigrama.Hdescripcion" + numPalabra);
		}
	}
	
	/**
	 * Configura las palabras verticales
	 */
	private void configurarPalabrasVerticales()
	{
		for(int i = 0; i < palabrasV[0].length; i++)
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
			
			String posicion = "" + coordenadaY + ":" + coordenadaX;
			
			palabrasV[PALABRAS][i] = palabra;
			palabrasV[DESCRIPCIONES][i] = posicion + " - " + propiedades.getProperty("crucigrama.Vdescripcion" + numPalabra);
		}
	}
	
	/**
	 * Crea una matriz con valores que indican si una posición es correcta o no
	 * @return valores que indican si una posición es correcta o no
	 */
	public boolean[][] compararLetraPorLetra()
	{
		boolean[][] letraPorLetra = new boolean[filas][columnas];
		
		for(int i = 0; i < filas; i++)
		{
			for(int j = 0; j < columnas; j++)
			{
				letraPorLetra[i][j] = false;
			}
		}
		
		for(int i = 0; i < filas; i++)
		{
			for(int j = 0; j < columnas; j++)
			{
				if(casillas[i][j] != '$')
				{
					if(solucion[i][j] == casillas[i][j])
					{
						letraPorLetra[i][j] = true;
					}
				}
			}
		}
		
		return letraPorLetra;
	}
}
