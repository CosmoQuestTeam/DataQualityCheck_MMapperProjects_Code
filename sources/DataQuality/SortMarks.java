package DataQuality;

/**************************/
/* Built-in java packages */
/**************************/
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

/*************************/
/* User-defined packages */
/*************************/
import DataQuality.Marks;

public class SortMarks
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    ArrayList <Marks> marks;
    int field;

    SortMarks(ArrayList <Marks> marks, String fieldname)
    {
	this.field = selectfield(fieldname);
	this.marks = marks;
    }

    ArrayList <Marks> GetMarks()
    {
	return marks;
    }

    int choose_pivot(int i, int j)
    {
	return ((i+j)/2);
    }

    public static void display(ArrayList <Marks> list)
    {
	for(int i=0; i<list.size(); i++)
	{
	    System.out.format("%7d | %7d | %7d | %7.2f | %7.2f | %7.2f\n",list.get(i).GetId(), list.get(i).GetUser_id(), list.get(i).GetImage_id() , list.get(i).GetX_position(), list.get(i).GetY_position(), list.get(i).GetDiameter());
	}
    }

    int selectfield(String fieldname)
    {
	/*************************************************/
	/* Declaration/Initialization of class variables */
	/*************************************************/
	int fieldnumber;
	
	/***************************/
	/* Select field to sort on */
	/***************************/
	if(fieldname.equals("id"))
	{
	    fieldnumber = 1;
	}
	else if(fieldname.equals("user_id"))
	{	    
	    fieldnumber = 2;	    
	}
	else if(fieldname.equals("image_id"))
	{
	    fieldnumber = 3;
	}
	else if(fieldname.equals("x"))
	{
	    fieldnumber = 4;
	}
	else if(fieldname.equals("y"))
	{
	    fieldnumber = 5;
	}
	else if(fieldname.equals("diameter"))
	{
	    fieldnumber = 6;
	}
	else
	{
	    fieldnumber = 0;
	}

	return fieldnumber;
    }

    void quicksort(int m , int n)
    {
	double key;
	int i, j, k;
	if(m < n)
	{
	    k = choose_pivot(m, n);
	    Collections.swap(marks, m, k);
	    key = marks.get(m).doubleValue(field);
	    i = m+1;
	    j = n-1;
	    while(i <= j)
	    {
		while((i < n) && (marks.get(i).doubleValue(field) <= key))
		{
		    i++;
		}
		while((j >= m) && (marks.get(j).doubleValue(field) > key))
		{    
		    j--;
		}
		if(i < j)
		{    
		    Collections.swap(marks, i, j);
		}
	    }

	    /*****************/
	    /* Swap elements */
	    /*****************/
	    Collections.swap(marks, m, j);

	    /************************************/
	    /* Recursively sort the lesser list */
	    /************************************/
	    quicksort(m, j);
	    quicksort(j+1, n);
	}
    }
}
