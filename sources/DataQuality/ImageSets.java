package DataQuality;

public class ImageSets
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    public int nfields = 3;
    private Integer application_id;
    private Integer id;
    private String details;
    private String name;

    public ImageSets(int id, String name, String details)
    {
	this.details = details;
	this.id = id;
	this.name = name;
    }

    public Integer GetId()
    {
	return id;
    }

    public Object GetField(int FieldNumber)
    {
	if(FieldNumber == 1)
	{
	    return id;
	}
	else if(FieldNumber == 2)
	{	    
	    return name;	    
	}
	else if(FieldNumber == 3)
	{
	    return details;
	}
	else
	{
	    System.out.println("Invalid field value (" + FieldNumber + "): 1-id, 2-name, 3-details");
	    System.out.println("Terminating program ...");
	    System.exit(1);
	}
	
	return 0;
    }

    public String GetDetails()
    {
	return details;
    }

    public String GetFieldname(int FieldNumber)
    {
	/*************************************************/
	/* Declaration/Initialization of class variables */
	/*************************************************/
	String name = null;

	/*******************/
	/* Find field name */
	/*******************/
	if(FieldNumber == 1)
	{
	    name = "id";
	}
	else if(FieldNumber == 2)
	{	    
	    name = "name =";	    
	}
	else if(FieldNumber == 3)
	{
	    name = "details";
	}
	else
	{
	    System.out.println("Invalid field value (" + FieldNumber + "): 1-id, 2-name, 3-details");
	    System.out.println("Terminating program ...");
	    System.exit(1);
	}
	
	return name;
    }

    public String GetName()
    {
	return name;
    }
}
