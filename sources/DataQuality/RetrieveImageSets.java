package DataQuality;

/**************************/
/* Built-in java packages */
/**************************/
import java.sql.*;
import java.util.*;

/*************************/
/* User-defined packages */
/*************************/
import DataQuality.ImageSets;

public class RetrieveImageSets implements Runnable
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    private static Connection conn;
    private static int columnsNumber;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static Statement st;
    private static String field;
    private static String query;
    private static String table;
    private static ArrayList <ImageSets> data;

    public RetrieveImageSets(Connection conn, String table, String field)
    {
	/*******************************************************************/
	/* Retrieve needed information for database information extraction */
	/*******************************************************************/
	this.conn = conn;
	this.table = table;
	this.field = field;
	
	/***************************/
	/* Reserve memory for data */
	/***************************/
	data = new ArrayList<ImageSets>();

	/*******************************************************************/
	/* Set query settings to null, as no query has been requested, yet */
	/*******************************************************************/
	rs = null;
	st = null;
    }
    
    public ArrayList <ImageSets> GetData()
    {
	return data;
    }
    
    public int GetDataArraySize()
    {
	return data.size();
    }

    public void run()
    {
	try
	{
	    /************************/
	    /* Define SQL selection */
	    /************************/
	    query = "SELECT " + field + " FROM " + table;

	    /********************/
	    /* Create Statement */
	    /********************/
	    st = conn.createStatement();

	    /***************************/
	    /* Execute selection query */
	    /***************************/
	    rs = st.executeQuery(query);

      	    /*****************/
	    /* Retrieve data */
	    /*****************/
	    rsmd = rs.getMetaData();
	    columnsNumber = rsmd.getColumnCount();
	    while (rs.next())
	    {
		ImageSets myImageSets = new ImageSets(rs.getInt("id"), rs.getString("name"), rs.getString("details")); 
		data.add(myImageSets);
	    }
	}
	catch(Exception e)
	{
	    System.err.println(e.getMessage());
	    System.exit(1);
	}
	finally
	{
	    /*********************/
	    /* Deallocate memory */
	    /*********************/
	    if(rs != null)
	    {
		try
		{
		    rs.close();
		    rs = null;
		}
		catch(Exception e)
		{
		    System.err.println("Could not free memory reserved for resultset!");
		    System.err.println(e.getMessage());
		    System.exit(1);
		}
	    }
	    if(st != null)
	    {
		try
		{
		    st.close();
		    st = null;
		}
		catch(Exception e)
		{
		    System.err.println("Could not free memory reserved for statement!");
		    System.err.println(e.getMessage());
		    System.exit(1);
		}
	    }
	}
    }
}
