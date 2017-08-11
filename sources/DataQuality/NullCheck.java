package DataQuality;

/**************************/
/* Built-in java packages */
/**************************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

/*************************/
/* User-defined packages */
/*************************/
import DataQuality.Images;
import DataQuality.ImageSets;
import DataQuality.Marks;
import DataQuality.NullCheckStatus;

public class NullCheck
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    private Images images;
    private ImageSets imagesets;
    private int index;
    private int nfields;
    private Marks marks;
    private String table;

    NullCheck(Images images, int index)
    {
	this.images = images;
	this.index = index;
	nfields = images.nfields;
	table = "images";
    }

    NullCheck(ImageSets imagesets, int index)
    {
	this.imagesets = imagesets;
	this.index = index;
	nfields = imagesets.nfields;
	table = "image_sets";
    }
    
    NullCheck(Marks marks, int index)
    {
	this.marks = marks;
	this.index = index;
	nfields = marks.nfields;
	table = "marks";
    }

    NullCheckStatus status()
    {
	/*******************************************/
	/* Declaration/Initialization of variables */
	/*******************************************/
	NullCheckStatus s;
	String note;

	/**********************************/
	/* Check nullity of current entry */
	/**********************************/
	note = "NONE";
	if(table == "images")
	{
	    for(int i=1; i<=nfields; i++)
	    {
		if(images.GetField(i) == null)
		{
		    note = (note.equals("NONE")) 
			 ? images.GetFieldname(i) : note+", "+images.GetFieldname(i);
		}
	    }
	}
	else if(table == "image_sets")
	{
	    for(int i=1; i<=nfields; i++)
	    {
		if(imagesets.GetField(i) == null)
		{
		    note = (note.equals("NONE")) 
			 ? imagesets.GetFieldname(i) : note+", "+imagesets.GetFieldname(i);
		}
	    }
	}
	else
	{
	    for(int i=1; i<=nfields; i++)
	    {
		if(marks.GetField(i) == null)
		{
		    note = (note.equals("NONE")) 
			 ? marks.GetFieldname(i) : note+", "+marks.GetFieldname(i);
		}
	    }
	}
	s = new NullCheckStatus(table, index, note);

	return s;
    }

    public static void display(ArrayList <NullCheckStatus> list, String showall)
    {
	/*******************************************/
	/* Declaration/Initialization of variables */
	/*******************************************/
	int nelements;

	nelements = list.size();
	if(showall.toUpperCase().equals("NONE"))
	{
	    for(int i=0; i<nelements; i++)
	    {
		if(!list.get(i).GetNote().equals("NONE"))
		{
		    System.out.println("Table: "+list.get(i).GetTable()+" ID: "+list.get(i).GetIndex()+" Null_Field(s): "+list.get(i).GetNote());
		}
	    }
	}
	else
	{
	    for(int i=0; i<nelements; i++)
	    {
		System.out.println("Table: "+list.get(i).GetTable()+" ID: "+list.get(i).GetIndex()+" Null_Field(s): "+list.get(i).GetNote());
	    }
	}
	
	return;
    }

    public static void writetofile(ArrayList <NullCheckStatus> list, String filename)
    {
	/*******************************************/
	/* Declaration/Initialization of variables */
	/*******************************************/
	int nelements;

	/**********************/
	/* Write data to file */
	/**********************/
	nelements = list.size();
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
	{
	    String content = "Null check results for %s table. The following fields were checked for nullity: %s\n\n";
	    /********************************************/
	    /* Indicate which fields are being examined */
	    /********************************************/
	    if(list.get(0).GetTable() == "images")
	    {
		bw.write(String.format(content, "images", "id, image_set_id, application_id, name, done, sun_angle, and detail"));
	    }
	    else if(list.get(0).GetTable() == "image_sets")
	    {
		bw.write(String.format(content, "image_sets", "id, name, and detail"));
	    }
	    else if(list.get(0).GetTable() == "marks")
	    {
		bw.write(String.format(content, "marks", "id, user_id, image_id, x, y, and diameter"));
	    }
	    else
	    {
		System.out.println("Unable to write data to file due to invalid table name!");
		System.out.println("Now terminating program ...");
		System.exit(0);
	    }

	    content = "%-15s %-10s %-15s\n";
	    bw.write(String.format(content, "Table_Name", "ID", "Null_Field(s)"));
	    for(int i=0; i<nelements; i++)
	    {
		content = "%-15s %-10d %-15s\n";
		bw.write(String.format(content, list.get(i).GetTable(), list.get(i).GetIndex(), list.get(i).GetNote()));
	    }
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}

	return;
    }
}
