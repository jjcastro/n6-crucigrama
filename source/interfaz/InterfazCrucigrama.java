package interfaz;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.*;

import mundo.Crucigrama;

/**
 * Ventana principal del juego
 * @author jj.castro10
 *
 */
public class InterfazCrucigrama extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Panel con acciones
	 */
	private PanelAcciones pnlAcciones;
	
	/**
	 * Panel con los elementos principales
	 */
	private PanelPrincipal pnlPrincipal;
	
	/**
	 * Variable para guardar el mundo
	 */
	private Crucigrama crucigrama;
	
	/**
	 * Crea una nueva instancia de la interfaz
	 */
	public InterfazCrucigrama()
	{
		setSize(780, 730);
		setLayout(new BorderLayout());
		
		ImageIcon imagen = new ImageIcon("./data/imagenes/banner.jpg");
		JLabel banner = new JLabel(imagen);
		add(banner, BorderLayout.NORTH);
		
		pnlAcciones = new PanelAcciones(this);
		add(pnlAcciones, BorderLayout.SOUTH);
		
		pnlPrincipal = new PanelPrincipal(this);
		add(pnlPrincipal, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Entra valores en las casillas del crucigrama
	 * @param posicionX la posición en x de la entrada
	 * @param posicionY la posición en y de la entrada
	 * @param entrada el valor a entrar
	 */
	public void jugar(int posicionX, int posicionY, String entrada)
	{
		crucigrama.jugar(posicionY, posicionX, entrada);
	}
	
	/**
	 * Carga un crucigrama desde un archivo
	 * @param archivo el archivo a cargar
	 */
	public void cargar(File archivo)
	{
		try
		{
			crucigrama = new Crucigrama(archivo);
			
			String[] palabrasH = crucigrama.darDescripcionesPalabrasH();
			String[] palabrasV = crucigrama.darDescripcionesPalabrasV();
			
			pnlPrincipal.cargarNuevoCrucigrama(crucigrama.darSolucion(),
					crucigrama.darIndices(), palabrasH, palabrasV);
			
			setVisible(true);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "No se ha podido cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Valida si las palabras horizontales son correctas <br>
	 * Las palabras correctas se colorean de verde
	 */
	public void validarPalabrasHorizontales()
	{
		try
		{
			for(int i = 1; i < crucigrama.darNumeroDePalabrasH() + 1; i++)
			{
				pnlPrincipal.colorearPalabraH(i, crucigrama.validarHorizontal(i));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Debe cargar el juego antes de validar", "Error al validar", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Valida si las palabras verticales son correctas <br>
	 * Las palabras correctas se colorean de verde
	 */
	public void validarPalabrasVerticales()
	{
		try
		{
			for(int i = 1; i < crucigrama.darNumeroDePalabrasV() + 1; i++)
			{
				pnlPrincipal.colorearPalabraV(i, crucigrama.validarVertical(i));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Debe cargar el juego antes de validar", "Error al validar", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Soluciona el crucigrama <br>
	 * Pinta de verde las letras correctas, rojas las equivocadas
	 */
	public void solucionar()
	{
		try
		{
			pnlPrincipal.solucionar(crucigrama.compararLetraPorLetra(), crucigrama.darSolucion());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Debe cargar el juego antes de validar", "Error al validar", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Limpia el crucigrama
	 */
	public void limpiar()
	{
		try
		{
			pnlPrincipal.limpiar();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Debe cargar el juego antes de limpiar", "Error al limpiar", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método para la extensión 1
	 */
	public void opcion1()
	{
		String mensaje = "Respuesta 1";
		
		JOptionPane.showMessageDialog(this, mensaje, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Método para la extensión 2
	 */
	public void opcion2()
	{
		String mensaje = "Respuesta 1";
		
		JOptionPane.showMessageDialog(this, mensaje, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Método que corre la interfaz
	 * @param args sin argumentos
	 */
	public static void main(String[] args)
	{
		new InterfazCrucigrama();
	}
}
