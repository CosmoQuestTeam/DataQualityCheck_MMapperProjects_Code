package DataQuality;

/**************************/
/* Built-in java packages */
/**************************/
import java.util.ArrayList;

/*************************/
/* User-defined packages */
/*************************/
import DataQuality.Marks;

public class SearchMarks
{
    public static int binarySearch(ArrayList <Marks> marks, int m, int n, int fieldnumber, double value)
    {
        if(n >= m)
        {
	    /***********************************/
	    /* Calculate value of middle index */
	    /***********************************/
            int midpoint = m+(n-m)/2;

	    /****************************************************/
            /* Compare wanted value to value of middle element. */
	    /* If equal, return index of middle element.        */
	    /****************************************************/
	    if(marks.get(midpoint).doubleValue(fieldnumber) == value)
	    {
               return midpoint;
	    }

	    /**************************************************/
            /* If value of the middle element is smaller than */
	    /* the wanted value, then the wanted value must   */
            /* reside in the right portion of the array.      */
	    /**************************************************/
            if(marks.get(midpoint).doubleValue(fieldnumber) < value)
	    {
		return binarySearch(marks, midpoint+1, n, fieldnumber, value);
	    }

	    /*************************************************/
            /* If value of the middle element is larger than */
	    /* the wanted value, then the wanted value must  */
            /* reside in the left portion of the array.      */
	    /*************************************************/
            if(marks.get(midpoint).doubleValue(fieldnumber) > value)
	    {
		return binarySearch(marks, m, midpoint-1, fieldnumber, value);
	    }
        }

	/***********************************************************/
        /* If value is not present in array, return negative index */
	/***********************************************************/
        return -1;
    }

    public static int firstOccurence(ArrayList <Marks> marks, int fieldnumber, double value)
    {
	/*************************************************/
	/* Declaration/Initialization of class variables */
	/*************************************************/
	boolean notFound = true;
	int n;
	int lowerbounding_index;
	int upperbounding_index;

	/*********************************************************************/
	/* Get a ball park estimate of where value first occurs in the array */
	/*********************************************************************/
	n = marks.size();
	lowerbounding_index = 0;
	upperbounding_index = binarySearch(marks, 0, n-1, fieldnumber, value);

	/************************************************************/
	/* If upperbounding index is equal to -1, terminate program */
	/************************************************************/
	if(upperbounding_index == -1)
	{
	    return upperbounding_index;
	}

	/*******************************************************/
	/* Compare values at lowerbounding and upperbounding   */
	/* indicies. If they are the same, return lower index. */
	/*******************************************************/
	if(marks.get(lowerbounding_index).doubleValue(fieldnumber) == value)
	{
	    return lowerbounding_index;
	}

	/****************************************************************/
	/* Converge on first occurrence. Note: This is probably not the */
	/* fastest, but it is the best I can come up with at the moment */
	/****************************************************************/
	while((upperbounding_index-lowerbounding_index) != 1)
	{
	    /***********************************************************/
	    /* Find midpoint between upper and lower bounding indicies */
	    /***********************************************************/
	    int midpoint = lowerbounding_index+(upperbounding_index-lowerbounding_index)/2;

	    /********************************************************/
	    /* Compare value of midpoint element to searched value. */
	    /* If less than searched value, move lowerbounding      */
	    /* index to midpoint position. If equal to searched     */
	    /* value, move upperbounding index to midpoint position */
	    /********************************************************/
	    if(marks.get(midpoint).doubleValue(fieldnumber) < value)
	    {
		lowerbounding_index = midpoint;
	    }
	    else
	    {
		upperbounding_index = midpoint;
	    }
	}

	return upperbounding_index;
    }
}
