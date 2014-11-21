package interfaz;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import mundo.Crucigrama;

/**
 * Panel central con el crucigrama y las descripciones de las palabras
 * @author jj.castro10
 *
 */
public class PanelPrincipal extends JPanel implements DocumentListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable para guardar la ventana principal
	 */
	private InterfazCrucigrama interfaz;
	
	/**
	 * Número de filas del crucigrama
	 */
	private int filas;
	/**
	 * Número de columnas del crucigrama
	 */
	private int columnas;
	
	/**
	 * Matriz de campos de texto del crucigrama
	 */
	private JTextField[][] campos;
	
	/**
	 * Matriz con los números de las palabras
	 */
	private int[][][] indicePalabras;
	
	/**
	 * Panel con el diagrama del crucigrama
	 */
	private JPanel pnlCrucigrama;
	
	/**
	 * Panel con las descripciones de las palabras
	 */
	private JPanel pnlDescripciones;
	
	/**
	 * Texto de las descripciones de palabras horizontales
	 */
	private JLabel txtDescripcionesH;
	/**
	 * Texto de las descripciones de palabras verticales
	 */
	private JLabel txtDescripcionesV;
	
	/**
	 * Crea una nuevo panel central
	 * @param pInterfaz ventana principal
	 */
	public PanelPrincipal(InterfazCrucigrama pInterfaz)
	{
		interfaz = pInterfaz;
		setLayout(new GridLayout(1,2));
	}

	/**
	 * Configura y agrega el crucigrama al panel
	 * @param solucion matriz con las soluciones del crucigrama
	 */
	private void configurarCrucigrama(char[][] solucion)
	{
		if(pnlCrucigrama != null) remove(pnlCrucigrama);
		
		pnlCrucigrama = new JPanel();
		
		filas = solucion.length;
		columnas = solucion[0].length;
		
		pnlCrucigrama.setLayout(new GridLayout(filas + 1, columnas + 1, 1, 1));
		pnlCrucigrama.add(new JLabel());
		
		// INICIALIZAR LA MATRIZ Y TODOS LOS CAMPOS
		campos = new JTextField[filas][columnas];
		
		// LLENAR LA PRIMERA FILA
		for(int i = 1; i < columnas + 1; i++)
		{
			pnlCrucigrama.add(new JLabel(""+i, SwingConstants.CENTER));
		}
		
		for(int i = 1; i < filas + 1; i++)
		{
			pnlCrucigrama.add(new JLabel(""+i, SwingConstants.CENTER));

			for(int j = 0; j < columnas; j++)
			{
				campos[i-1][j] = new JTextField();
				
				if(solucion[i-1][j] == '$')
				{
					campos[i-1][j].setEnabled(false);
					campos[i-1][j].setBackground(Color.BLACK);
				}
				
				pnlCrucigrama.add(campos[i-1][j]);
				campos[i-1][j].getDocument().putProperty("y", "" + (i-1));
				campos[i-1][j].getDocument().putProperty("x", "" + j);
				campos[i-1][j].getDocument().addDocumentListener(this);
			}
		}
		
		this.add(pnlCrucigrama);
	}
	
	/**
	 * Configura y agrega las descripciones al panel
	 * @param palabrasH matriz con las palabras horizontales y sus descripciones
	 * @param palabrasV matriz con las palabras verticales y sus descripciones
	 */
	private void configurarDescripciones(String[] palabrasH, String[] palabrasV)
	{
		if(pnlDescripciones != null) remove(pnlDescripciones);
		
		pnlDescripciones = new JPanel();
		pnlDescripciones.setLayout(new GridLayout(2,1));
		
		txtDescripcionesH = new JLabel();
		txtDescripcionesH.setBorder(new TitledBorder("Horizontales"));
		
		txtDescripcionesV = new JLabel();
		txtDescripcionesV.setBorder(new TitledBorder("Verticales"));
		
		pnlDescripciones.add(txtDescripcionesH);
		pnlDescripciones.add(txtDescripcionesV);
		
		String descripcionesH = "<html>";
		for(int i = 0; i < palabrasH.length; i++)
		{
			descripcionesH += palabrasH[i] + "<br>";
		}
		
		descripcionesH += "</html>";
		
		String descripcionesV = "<html>";
		for(int i = 0; i < palabrasV.length; i++)
		{
			descripcionesV += palabrasV[i] + "<br>";
		}
		
		descripcionesV += "</html>";
		
		txtDescripcionesH.setText(descripcionesH);
		txtDescripcionesV.setText(descripcionesV);
		
		this.add(pnlDescripciones);
	}
	
	/**
	 * Carga un nuevo crucigrama con descripciones y lo agrega al panel
	 * @param solucion matriz con las soluciones del crucigrama
	 * @param indices Matriz con los números de las palabras y sus posiciones
	 * @param palabrasH matriz con las palabras horizontales y sus descripciones
	 * @param palabrasV matriz con las palabras verticales y sus descripciones
	 */
	public void cargarNuevoCrucigrama(char[][] solucion, int[][][] indices, String[] palabrasH, String[] palabrasV)
	{
		indicePalabras = indices;
		
		configurarCrucigrama(solucion);
		configurarDescripciones(palabrasH, palabrasV);
	}
	
	/**
	 * Colorea de verde si la palabra horizontal es correcta, de blanco si no
	 * @param palabra palabra a colorear
	 * @param esCorrecta si la palabra es correcta o no
	 * @throws Exception lanza excepción si no se ha cargado un crucigrama
	 */
	public void colorearPalabraH(int palabra, boolean esCorrecta) throws Exception
	{
		boolean finalizado = false;
		
		for(int i = 0; i < filas && !finalizado; i++)
		{
			for(int j = 0; j < columnas && !finalizado; j++)
			{
				if(indicePalabras[i][j][Crucigrama.HORIZONTALES] == palabra)
				{
					if(esCorrecta) campos[i][j].setBackground(Color.GREEN);
					else campos[i][j].setBackground(Color.WHITE);
				}
				else if(indicePalabras[i][j][Crucigrama.HORIZONTALES] == palabra + 1)
				{
					finalizado = true;
				}
			}
		}
	}
	
	/**
	 * Colorea de verde si la palabra vertical es correcta, de blanco si no
	 * @param palabra palabra a colorear
	 * @param esCorrecta si la palabra es correcta o no
	 * @throws Exception lanza excepción si no se ha cargado un crucigrama
	 */
	public void colorearPalabraV(int palabra, boolean esCorrecta) throws Exception
	{
		boolean finalizado = false;
		
		for(int i = 0; i < columnas && !finalizado; i++)
		{
			for(int j = 0; j < filas && !finalizado; j++)
			{
				if(indicePalabras[j][i][Crucigrama.VERTICALES] == palabra)
				{
					if(esCorrecta) campos[j][i].setBackground(Color.GREEN);
					else campos[j][i].setBackground(Color.WHITE);
				}
				else if(indicePalabras[j][i][Crucigrama.VERTICALES] == palabra + 1)
				{
					finalizado = true;
				}
			}
		}
	}

	/**
	 * Ejecuta la acción cuando se cambia el texto de un recuadro
	 * @param e Documento del evento
	 */
	private void jugar(Document e)
	{
		int posicionX = Integer.parseInt((String) e.getProperty("x"));
		int posicionY = Integer.parseInt((String) e.getProperty("y"));
		
		interfaz.jugar(posicionX, posicionY, campos[posicionY][posicionX].getText());
		
		//System.out.println("Letra " + campos[posicionY][posicionX].getText() + " jugada en " + posicionX + ", " + posicionY);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		jugar(e.getDocument());
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		jugar(e.getDocument());
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		jugar(e.getDocument());
		
	}

	/**
	 * Soluciona todo el tablero <br>
	 * Pinta de verde
	 * @param letras matriz con valores que indican si un recuadro está acertado o no
	 * @param solucion matriz con la solución del crucigrama
	 * @throws Exception lanza excepción si no se ha cargado el crucigrama
	 */
	public void solucionar(boolean[][] letras, char[][] solucion) throws Exception
	{
		for(int i = 0; i < filas; i++)
		{
			for(int j = 0; j < columnas; j++)
			{
				if(letras[i][j])
				{
					campos[i][j].setBackground(Color.GREEN);
				}
				else
				{
					if(!campos[i][j].getText().equals(""))
					{
						campos[i][j].setBackground(Color.RED);
					}
					if(solucion[i][j] != '$') campos[i][j].setText("" + solucion[i][j]);
				}
			}
		}
	}
	
	/**
	 * Limpia el tablero
	 * @throws Exception lanza excepción si no se ha cargado el crucigrama
	 */
	public void limpiar() throws Exception
	{
		if(pnlCrucigrama != null)
		{
			for(int i = 0; i < filas; i++)
			{
				for(int j = 0; j < columnas; j++)
				{
					if(campos[i][j].getText() != "" && campos[i][j].isEnabled())
					{
						campos[i][j].setText("");
						campos[i][j].setBackground(Color.WHITE);
					}
				}
			}
		}
		else throw new NullPointerException();
	}
}
