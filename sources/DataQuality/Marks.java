package DataQuality;

public class Marks
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    public int nfields = 6;
    private Double diameter;
    private Double x_position;
    private Double y_position;
    private Integer id;
    private Integer image_id;
    private Integer user_id;
        
    public Marks(int id, int user_id, int image_id, double x_position, double y_position, double diameter)
    {
	this.diameter = diameter;
	this.id = id;
	this.image_id = image_id;
	this.user_id = user_id;
	this.x_position = x_position;
	this.y_position = y_position;
    }

    public double doubleValue(int fieldNumber)
    {
	if(fieldNumber == 1)
	{
	    return id.doubleValue();
	}
	else if(fieldNumber == 2)
	{	    
	    return user_id.doubleValue();	    
	}
	else if(fieldNumber == 3)
	{
	    return image_id.doubleValue();
	}
	else if(fieldNumber == 4)
	{
	    return x_position.doubleValue();
	}
	else if(fieldNumber == 5)
	{
	    return y_position.doubleValue();
	}
	else if(fieldNumber == 6)
	{
	    return diameter.doubleValue();
	}
	else
	{
	    System.out.println("Invalid field value (" + fieldNumber + "): 1-id, 2-user_id, 3-image_id, 4-x_position, 5-y_position, and 6-diameter");
	    System.out.println("Terminating program ...");
	    System.exit(1);
	}
	
	return 0.0;
    }

    public Double GetDiameter()
    {
	return diameter;
    }

    public Double GetX_position()
    {
	return x_position;
    }

    public Double GetY_position()
    {
	return y_position;
    }

    public Object GetField(int fieldNumber)
    {
	if(fieldNumber == 1)
	{
	    return id;
	}
	else if(fieldNumber == 2)
	{	    
	    return user_id;	    
	}
	else if(fieldNumber == 3)
	{
	    return image_id;
	}
	else if(fieldNumber == 4)
	{
	    return x_position;
	}
	else if(fieldNumber == 5)
	{
	    return y_position;
	}
	else if(fieldNumber == 6)
	{
	    return diameter;
	}
	else
	{
	    System.out.println("Invalid field value (" + fieldNumber + "): 1-id, 2-user_id, 3-image_id, 4-x_position, 5-y_position, and 6-diameter");
	    System.out.println("Terminating program ...");
	    System.exit(1);
	}
	
	return 0;
    }

    public Integer GetId()
    {
	return id;
    }
    
    public Integer GetImage_id()
    {
	return image_id;
    }

    public Integer GetUser_id()
    {
	return user_id;
    }
    
    public String GetFieldname(int fieldNumber)
    {
	/*************************************************/
	/* Declaration/Initialization of class variables */
	/*************************************************/
	String name = null;

	/*******************/
	/* Find field name */
	/*******************/
	if(fieldNumber == 1)
	{
	    name = "id";
	}
	else if(fieldNumber == 2)
	{	    
	    name = "user_id";	    
	}
	else if(fieldNumber == 3)
	{
	    name = "image_id";
	}
	else if(fieldNumber == 4)
	{
	    name = "x";
	}
	else if(fieldNumber == 5)
	{
	    name = "y";
	}
	else if(fieldNumber == 6)
	{
	    name = "diameter";
	}
	else
	{
	    System.out.println("Invalid field value (" + fieldNumber + "): 1-id, 2-user_id, 3-image_id, 4-x, 5-y, and 6-diameter");
	    System.out.println("Terminating program ...");
	    System.exit(1);
	}
	
	return name;
    }
}
