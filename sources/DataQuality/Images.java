package DataQuality;

public class Images
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    public int nfields = 7;
    private Double sun_angle;
    private Integer application_id;
    private Integer done;
    private Integer id;
    private Integer image_set_id;
    private String details;
    private String name;

    public Images(int id, int image_set_id, int application_id, String name, int done, double sun_angle, String details)
    {
	this.application_id = application_id;
	this.details = details;
	this.done = done;
	this.id = id;
	this.image_set_id = image_set_id;
	this.name = name;
	this.sun_angle = sun_angle;
    }
    
    public Double GetSun_angle()
    {
	return sun_angle;
    }

    public Integer GetApplication_id()
    {
	return application_id;
    }

    public Integer GetDone()
    {
	return done;
    }

    public Integer GetId()
    {
	return id;
    }

    public Integer GetImage_set_id()
    {
	return image_set_id;
    }

    public Object GetField(int FieldNumber)
    {
	if(FieldNumber == 1)
	{
	    return id;
	}
	else if(FieldNumber == 2)
	{	    
	    return image_set_id;	    
	}
	else if(FieldNumber == 3)
	{
	    return application_id;
	}
	else if(FieldNumber == 4)
	{
	    return name;
	}
	else if(FieldNumber == 5)
	{
	    return done;
	}
	else if(FieldNumber == 6)
	{
	    return sun_angle;
	}
	else if(FieldNumber == 7)
	{
	    return details;
	}
	else
	{
	    System.out.println("Invalid field value (" + FieldNumber + "): 1-id, 2-image_set_id, 3-application_id, 4-name, 5-done, 6-sun_angle, and 7-detail");
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
	    name = "image_set_id";	    
	}
	else if(FieldNumber == 3)
	{
	    name = "application_id";
	}
	else if(FieldNumber == 4)
	{
	    name = "name";
	}
	else if(FieldNumber == 5)
	{
	    name = "done";
	}
	else if(FieldNumber == 6)
	{
	    name = "sun_angle";
	}
	else if(FieldNumber == 7)
	{
	    name = "details";
	}
	else
	{
	    System.out.println("Invalid field value (" + FieldNumber + "): 1-id, 2-image_set_id, 3-application_id, 4-name, 5-done, 6-sun_angle, and 7-detail");
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
