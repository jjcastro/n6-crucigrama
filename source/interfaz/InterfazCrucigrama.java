package interfaz;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.*;

import mundo.Crucigrama;

public class InterfazCrucigrama extends JFrame
{
	private PanelAcciones pnlAcciones;
	private PanelPrincipal pnlPrincipal;
	
	private Crucigrama crucigrama;
	
	public InterfazCrucigrama() throws Exception
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
	
	public void jugar(int posicionX, int posicionY, String entrada)
	{
		crucigrama.jugar(posicionY, posicionX, entrada);
	}
	
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
	
	public boolean validarVertical(int palabra)
	{
		return crucigrama.validarVertical(palabra);
	}
	
	public void validarPalabrasHorizontales()
	{
		try
		{
			for(int i = 1; i < crucigrama.darNumeroDePalabrasH() + 1; i++)
			{
				pnlPrincipal.colorearPalabraH(i, crucigrama.validarHorizontal(i));
			}
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "Debe cargar el juego antes de validar", "Error al validar", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void validarPalabrasVerticales()
	{
		try
		{
			for(int i = 1; i < crucigrama.darNumeroDePalabrasV() + 1; i++)
			{
				pnlPrincipal.colorearPalabraV(i, crucigrama.validarVertical(i));
			}
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "Debe cargar el juego antes de validar", "Error al validar", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		new InterfazCrucigrama();
	}
}
