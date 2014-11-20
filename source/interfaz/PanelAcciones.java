package interfaz;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class PanelAcciones extends JPanel implements ActionListener
{
	private InterfazCrucigrama interfaz;
	
	private JButton btnValidarHorizontales;
	private JButton btnValidarVerticales;
	private JButton btnSolucionar;
	private JButton btnLimpiar;
	private JButton btnCargar;
	private JButton btnOpcion1;
	private JButton btnOpcion2;
	
	public PanelAcciones(InterfazCrucigrama pInterfaz)
	{
		interfaz = pInterfaz;
		
		setLayout(new GridLayout(2, 4));
		
		btnValidarHorizontales = new JButton("Validar horizontales");
		btnValidarVerticales = new JButton("Validar verticales");
		btnSolucionar = new JButton("Solucionar");
		btnLimpiar = new JButton("Limpiar");
		btnCargar = new JButton("Cargar");
		btnOpcion1 = new JButton("Opcion 1");
		btnOpcion2 = new JButton("Opcion 2");
		
		add(btnValidarHorizontales);
		add(btnValidarVerticales);
		add(btnSolucionar);
		add(btnLimpiar);
		add(btnCargar);
		add(btnOpcion1);
		add(btnOpcion2);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
	}
}
