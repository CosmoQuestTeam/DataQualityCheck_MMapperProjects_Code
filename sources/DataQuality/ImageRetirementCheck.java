package DataQuality;

/**************************/
/* Built-in java packages */
/**************************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.ArrayList;
import java.util.List;

/*************************/
/* User-defined packages */
/*************************/
import DataQuality.ImageRetirementCheckStatus;
import DataQuality.Images;
import DataQuality.Marks;
import DataQuality.SearchMarks;

public class ImageRetirementCheck
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    private int quota = 15;
    private ArrayList <Marks> marks;
    private Images image;

    ImageRetirementCheck(Images image, ArrayList <Marks> marks)
    {
	this.image = image;
	this.marks = marks;
    }

    ArrayList <Marks> GetMarks()
    {
	return marks;
    }

    ImageRetirementCheckStatus status()
    {
	/*******************************************/
	/* Declaration/Initialization of variables */
	/*******************************************/
	ImageRetirementCheckStatus s;
	ArrayList <Integer> userID = new ArrayList<Integer>();
	int ApplicationID = 0;
	int done;
	int i = 0;
	int ImageID = 0;
	int startingIndex = -1;
	int nelements = 0;
	int Response1 = 0;
	int Response2 = 0;
	int Response3 = 0;

	/****************************/
	/* Get image application id */
	/****************************/
	ApplicationID = image.GetApplication_id();

	/****************/
	/* Get image id */
	/****************/
	ImageID = image.GetId();

	/********************************/
	/* Get declared status of image */
	/********************************/
	done = image.GetDone();

	/**********************************/
	/* Find first instance of current */
	/* image_id in marks dataset.     */
	/* Note that marks dataset should */
	/* already be order based on      */
	/* image_id                       */
	/**********************************/

	/*******************************************************/
	/* Determine number of users that marked current image */
	/*******************************************************/
	startingIndex = SearchMarks.firstOccurence(marks, 3, ImageID);
	if(startingIndex >= 0)
	{
	    while((startingIndex+i < marks.size()) && (marks.get(startingIndex+i).GetImage_id() == ImageID))
	    {
		boolean skip = false;
		for(int j=userID.size()-1; j>=0; j--)
		{
		    if(marks.get(startingIndex+i).GetUser_id() == userID.get(j))
		    {
			skip = true;
			break;
		    }
		}
		if(!skip)
		{
		    userID.add(marks.get(startingIndex+i).GetUser_id());
		}
		i++;
	    }
	}

	/****************************/
	/* Record completion status */
	/****************************/
	nelements = userID.size();
       	if(nelements < quota)
	{
	    Response1 = 1;
	}
	else if(nelements == quota)
	{
	    Response2 = 1;   
	}
	else
	{
	    Response3 = 1;
	}
	s = new ImageRetirementCheckStatus(ApplicationID, ImageID, done, Response1, Response2, Response3);

	return s;
    }

    public static void display(ArrayList <ImageRetirementCheckStatus> list, String show)
    {
	/*******************************************/
	/* Declaration/Initialization of variables */
	/*******************************************/
	int nelements;

	nelements = list.size();
	if(show.toUpperCase().equals("UNDER"))
	{
	    for(int i=0; i<nelements; i++)
	    {
		if(list.get(i).GetUnderQuotaString().equals("YES"))
		{
		    System.out.println("Application_ID: "+list.get(i).GetApplicationID()+" Image_ID: "+list.get(i).GetImageID()+" Done: "+list.get(i).GetDoneString()+" Under_Quota: "+list.get(i).GetUnderQuotaString()+" Meets_Quota: "+list.get(i).GetQuotaString()+" Exceeds_Quota: "+list.get(i).GetOverQuotaString());
		}
	    }
	}
	else if(show.toUpperCase().equals("MEETS"))
	{
	    for(int i=0; i<nelements; i++)
	    {
		if(list.get(i).GetQuotaString().equals("YES"))
		{
		    System.out.println("Application_ID: "+list.get(i).GetApplicationID()+" Image_ID: "+list.get(i).GetImageID()+" Done: "+list.get(i).GetDoneString()+" Under_Quota: "+list.get(i).GetUnderQuotaString()+" Meets_Quota: "+list.get(i).GetQuotaString()+" Exceeds_Quota: "+list.get(i).GetOverQuotaString());
		}
	    }
	}
	else if(show.toUpperCase().equals("EXCEEDS"))
	{
	    for(int i=0; i<nelements; i++)
	    {
		if(list.get(i).GetOverQuotaString().equals("YES"))
		{
		    System.out.println("Application_ID: "+list.get(i).GetApplicationID()+" Image_ID: "+list.get(i).GetImageID()+" Done: "+list.get(i).GetDoneString()+" Under_Quota: "+list.get(i).GetUnderQuotaString()+" Meets_Quota: "+list.get(i).GetQuotaString()+" Exceeds_Quota: "+list.get(i).GetOverQuotaString());
		}
	    }
	}
	else if(show.toUpperCase().equals("NUMERICAL"))
	{
	    for(int i=0; i<nelements; i++)
	    {
		System.out.println("Application_ID: "+list.get(i).GetApplicationID()+" Image_ID: "+list.get(i).GetImageID()+" Done: "+list.get(i).GetDone()+" Under_Quota: "+list.get(i).GetUnderQuota()+" Meets_Quota: "+list.get(i).GetQuota()+" Exceeds_Quota: "+list.get(i).GetOverQuota());
	    }
	}
	else
	{
	    for(int i=0; i<nelements; i++)
	    {
		System.out.println("Application_ID: "+list.get(i).GetApplicationID()+" Image_ID: "+list.get(i).GetImageID()+" Done: "+list.get(i).GetDoneString()+" Under_Quota: "+list.get(i).GetUnderQuotaString()+" Meets_Quota: "+list.get(i).GetQuotaString()+" Exceeds_Quota: "+list.get(i).GetOverQuotaString());
	    }
	}
	
	return;
    }

    public static void writetofile(ArrayList <ImageRetirementCheckStatus> list, String filename)
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
	    String content = "The contents of the following table are the results of the image retirement check. The done field indicates if an image has been taken out of rotation, where a value of 0 means that the image can still be selected by a user and a value of 1 means that the image can no longer be selected by a user. Images that have been analyzed by less than 15 people will have a value of 1 for the under_quota field and 0 for all other quota related fields. Images that have been analyzed by exactly 15 people will have a value of 1 for the meets_quota field and 0 for all other quota related fields. Lastly, images that have been analyzed by more than 15 people will have a value of 1 for the over_quota field and 0 for all other quota related fields. \n\n";
	    bw.write(String.format(content));
	    content = "%-14s %-8s %-4s %-11s %-11s %-10s\n";
	    bw.write(String.format(content, "Application_ID", "Image_ID", "Done", "Under_Quota", "Meets_Quota", "Over_Quota"));
	    for(int i=0; i<nelements; i++)
	    {
		content = "%-14d %-8d %-4d %-11d %-11d %-10d\n";
		bw.write(String.format(content, list.get(i).GetApplicationID(), list.get(i).GetImageID(), list.get(i).GetDone(), list.get(i).GetUnderQuota(), list.get(i).GetQuota(), list.get(i).GetOverQuota()));
	    }
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}

	return;
    }
}
