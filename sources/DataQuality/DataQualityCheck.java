package DataQuality;

/**************************/
/* Built-in java packages */
/**************************/
import java.awt.*;
import java.awt.event.*;
import java.lang.Object;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.util.Timer;

/*************************/
/* User-defined packages */
/*************************/
import DataQuality.ImageRetirementCheck;
import DataQuality.ImageRetirementCheckStatus;
import DataQuality.Images;
import DataQuality.ImageSets;
import DataQuality.Marks;
import DataQuality.MySQLConnection;
import DataQuality.NullCheck;
import DataQuality.NullCheckStatus;
import DataQuality.RetrieveImages;
import DataQuality.RetrieveImageSets;
import DataQuality.RetrieveMarks;
import DataQuality.SortMarks;

public class DataQualityCheck
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    public static ArrayList <Images> images = new ArrayList<Images>();
    public static ArrayList <ImageSets> imagesets = new ArrayList<ImageSets>();
    public static ArrayList <Marks> marks = new ArrayList<Marks>();
    public static ArrayList <NullCheckStatus> imagesnullstatus = new ArrayList<NullCheckStatus>();
    public static ArrayList <NullCheckStatus> imagesetsnullstatus = new ArrayList<NullCheckStatus>();
    public static ArrayList <NullCheckStatus> marksnullstatus = new ArrayList<NullCheckStatus>();
    public static ArrayList <ImageRetirementCheckStatus> imageretirementstatus = new ArrayList<ImageRetirementCheckStatus>();
    public static Connection conn;    
    public static int ncheckpoints = 0;
    public static long l_time_start;
    public static long l_time_intermediate1;
    public static long l_time_intermediate2;
    public static String driver;
    public static String field;
    public static String password;
    public static String table;
    public static String url;
    public static String user;

    public static void main(String args[])
    {
	/*******************************************/
	/* Declaration/Initialization of variables */
	/*******************************************/
	double TimeConverter = 1.0E-9;
	int MarsMapID = 18;
	int MercuryMapID = 5;
	int MoonMapID = 1;
	int nimages = 0;
	int nimagesets = 0;
	int nmarks = 0;
	String fileName;
	String resultsDirectory;
	String statement;

	/*****************************/
	/* Record program start time */
	/*****************************/
	l_time_start = System.nanoTime();

	/******************************************/
	/* Retrieve arguements. If no arguements  */
	/* are given, default arguements are used */
	/******************************************/
	driver = "com.mysql.jdbc.Driver";
	url = "jdbc:mysql://localhost:3306/cosmoquest?autoReconnect=true&useSSL=false";
	user = "root";
	password = "00000000";

	/*****************************************************/
	/* Establish connection to CosmoQuest MySQL database */
	/*****************************************************/
	MySQLConnection myConnection = new MySQLConnection(driver, url, user, password);
	myConnection.Connect();
	conn = myConnection.GetConnection();

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to establish CosmoQuest MySQL connection: ";
	Checkpoint(statement);

	/******************************************************/
	/* Retrieve marks data from CosmoQuest MySQL database */
	/******************************************************/
	table = "marks";
	field = "id, user_id, image_id, x, y, diameter";
	RetrieveMarks myMarks = new RetrieveMarks(conn, table, field);
	myMarks.run();
	marks = myMarks.GetData();

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to retrieve user marks from CosmoQuest MySQL database: ";
	Checkpoint(statement);

	/**********************************************************/
	/* Retrieve imagesets data from CosmoQuest MySQL database */
	/**********************************************************/
	table = "image_sets";
	field = "id, name, details";
	RetrieveImageSets myImageSets = new RetrieveImageSets(conn, table, field);
	myImageSets.run();
	imagesets = myImageSets.GetData();

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to retrieve image_sets metadata from CosmoQuest MySQL database: ";
	Checkpoint(statement);

	/*******************************************************/
	/* Retrieve images data from CosmoQuest MySQL database */
	/*******************************************************/
	table = "images";
	field = "id, image_set_id, application_id, name, done, sun_angle, details";
	RetrieveImages myImages = new RetrieveImages(conn, table, field);
	myImages.run();
	images = myImages.GetData();

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to retrieve image metadata from CosmoQuest MySQL database: ";
	Checkpoint(statement);

	/***********************************************/
	/* End connection to CosmoQuest MySQL database */
	/***********************************************/
	myConnection.Disconnect();

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to end CosmoQuest MySQL connection: ";
	Checkpoint(statement);

	/***************************************/
	/* Determine total number of imagesets */
	/***************************************/
	nimagesets = imagesets.size();

	/************************************/
	/* Determine total number of images */
	/************************************/
	nimages = images.size();

	/***********************************/
	/* Determine total number of marks */
	/***********************************/
	nmarks = marks.size();

	/********************************************/
	/* Check for null entries in imagesets data */
	/********************************************/
	for(int i=0; i<nimagesets; i++)
      	{
	    NullCheck myCheck = new NullCheck(imagesets.get(i), imagesets.get(i).GetId());
	    imagesetsnullstatus.add(myCheck.status());
	}
	//NullCheck.display(imagesetsnullstatus, "none"); System.exit(0);

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to check nullity of image_sets data array: ";
	Checkpoint(statement);
	
	/*****************************************/
	/* Check for null entries in images data */
	/*****************************************/
	for(int i=0; i<nimages; i++)
      	{
	    NullCheck myCheck = new NullCheck(images.get(i), images.get(i).GetId());
	    imagesnullstatus.add(myCheck.status());
	}
	//NullCheck.display(imagesnullstatus, "none"); System.exit(0);
	
	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to check nullity of images data array: ";
	Checkpoint(statement);
	
	/****************************************/
	/* Check for null entries in marks data */
	/****************************************/
	for(int i=0; i<nmarks; i++)
      	{
	    NullCheck myCheck = new NullCheck(marks.get(i), marks.get(i).GetId());
	    marksnullstatus.add(myCheck.status());
	}
	//NullCheck.display(marksnullstatus, "none"); System.exit(0);

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to check nullity of marks data array: ";
	Checkpoint(statement);

	/********************************/
	/* Sort marks based on image id */
	/********************************/
	SortMarks mySort = new SortMarks(marks, "image_id");
	mySort.quicksort(0 , nmarks);
	marks = mySort.GetMarks();
	//SortMarks.display(marks); System.exit(0);

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to sort marks data array: ";
	Checkpoint(statement);

	/**********************************************/
	/* Determine number of images fully analyzed  */
	/**********************************************/
	for(int i=0; i<nimages; i++)
      	{
	    ImageRetirementCheck myCheck = new ImageRetirementCheck(images.get(i), marks);
	    imageretirementstatus.add(myCheck.status());
	}
	//ImageRetirementCheck.display(imageretirementstatus, "NUMERICAL"); System.exit(0);

	/**************/
	/* Checkpoint */
	/**************/
	statement = "Elapsed time to check image analysis completion: ";
	Checkpoint(statement);

	/*************************/
	/* Write results to file */
	/*************************/
	resultsDirectory = "/Users/MRichardson/Desktop/Planetary_Science_Institute/CosmoQuest/CraterProjects/Data_Quality_Check_Code/results/";
	fileName = resultsDirectory+"NullCheck_images.txt";
	NullCheck.writetofile(imagesnullstatus, fileName);
	fileName = resultsDirectory+"NullCheck_image_sets.txt";
	NullCheck.writetofile(imagesetsnullstatus, fileName);
	fileName = resultsDirectory+"NullCheck_marks.txt";
	NullCheck.writetofile(marksnullstatus, fileName);
	fileName = resultsDirectory+"ImageRetirementCheck.txt";
	ImageRetirementCheck.writetofile(imageretirementstatus, fileName);
    }

    public static void Checkpoint(String statement)
    {
	/*******************************************/
	/* Declaration/Initialization of variables */
	/*******************************************/
	double TimeConverter = 1.0E-9;

	if(ncheckpoints == 0)
	{
	    l_time_intermediate1 = System.nanoTime();
	    System.out.println(statement + (l_time_intermediate1-l_time_start)*TimeConverter + " seconds.");
	    ncheckpoints++;
	}
	else
	{
	    l_time_intermediate2 = System.nanoTime();
	    System.out.println(statement + (l_time_intermediate2-l_time_intermediate1)*TimeConverter + " seconds.");
	    l_time_intermediate1 = l_time_intermediate2;
	    ncheckpoints++;
	}
    }
}	
