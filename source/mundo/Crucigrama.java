package mundo;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

public class Crucigrama {

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
