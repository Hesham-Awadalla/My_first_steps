import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class tools
{

	static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    
    static ArrayList<File> result = new ArrayList<File>();
    static ArrayList<String> resultInfo = new ArrayList<String>();
    
    //Eine neue Funktion listDir
	public static void listDir(String path, String filetype, int FileAge) throws IOException
	{
			
	    File folder = new File(path); 
	    File[] listOfFiles = folder.listFiles();
	    
		String filesname;
		double fileLastMod, diff;
	    
		if (listOfFiles != null)
		//Wichtig ist zu �berpr�fen ob das File-Array nichtgleich null ist, da z. B. bei nicht ausreichenden
		//Berechtigungen um auf einen Ordnern zuzugreifen listFiles() null zur�ckliefert.
				
		{
			for (int i = 0; i < listOfFiles.length; i++)
			{
				
				if (listOfFiles[i].isFile())
				{
						
		            filesname = listOfFiles[i].getName();
		            fileLastMod = listOfFiles[i].lastModified();
		            
		            diff = (new Date().getTime() - fileLastMod)/2.628E9;  //Diff in Months!
		            
		            //Suche nach Kritieren "Dateityp" und "Dateialter"
		            
		            if (filetype == "Alle" && diff > FileAge)
	            	{
		                resultInfo.add(sdf.format(fileLastMod)+"       ");
		            	result.add(listOfFiles[i]);
	            	}
		            else if (filetype != "Alle" && filesname.endsWith(filetype) && diff > FileAge || filetype != "Alle" && filesname.endsWith(filetype.toUpperCase()) && diff > FileAge)
            		{       
		                resultInfo.add(sdf.format(fileLastMod)+"       ");
		            	result.add(listOfFiles[i]);					    
            		}
				}
	            else if (listOfFiles[i].isDirectory())
	        	{
	        		// ruft sich selbst mit dem Unterverzeichnis als Parameter auf  "rekursive Suche"
	        		listDir(listOfFiles[i].getAbsolutePath(), filetype, FileAge);	            		
	        	}
			}
		}
			
	}
}