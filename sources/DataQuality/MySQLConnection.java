package DataQuality;

/**************************/
/* Built-in java packages */
/**************************/
import java.sql.*;
import com.mysql.jdbc.Driver;

public class MySQLConnection
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    private static Connection conn;
    private static String driver;
    private static String password;
    private static String url;
    private static String user;

    public MySQLConnection(String driver, String url, String user, String password)
    {
	/*******************************************************/
	/* Retrieve needed information for database connection */
	/*******************************************************/
	this.driver = driver;
	this.url = url;
	this.user = user;
	this.password = password;

	/**********************************************************************/
	/* Set connection to null, as no connection has been established, yet */
	/**********************************************************************/
	conn = null;
    }
    
    public void Connect()
    {
	/*****************************************************/
	/* Establish connection to CosmoQuest MySQL database */
	/*****************************************************/
	try
	{
	    Class.forName(driver);
	    conn = DriverManager.getConnection(url, user, password);
	}
	catch(Exception e)
	{
	    System.err.println("Connection could not be established!");
	    System.err.println(e.getMessage());
	    System.exit(1);
	}
    }

    public void Disconnect()
    {
	/***********************************************/
	/* End connection to CosmoQuest MySQL database */
	/***********************************************/
	if(conn != null)
	{
	    try
	    {
		conn.close();
		conn = null;
	    }
	    catch(Exception e)
	    {
		System.err.println("Connection could not be broken!");
		System.err.println(e.getMessage());
		System.exit(1);
	    }
	}
    }

    public Connection GetConnection()
    {
	return conn;
    }

    public void TestConnection()
    {
	/************************************************/
	/* Test connection to CosmoQuest MySQL database */
	/************************************************/
	if(conn != null)
	{
	    /*******************************************/
	    /* Declaration/Initialization of variables */
	    /*******************************************/
	    int columnsNumber;
	    ResultSet rs;
	    ResultSetMetaData rsmd;
	    PreparedStatement ps;
	    Statement st;
	    String database = null;
	    String hostname = null;
	    String query = null;
	   
	    try
	    {
		/********************/
		/* Define SQL query */
		/********************/
		query = "SELECT DATABASE()";

		/********************/
		/* Create Statement */
		/********************/
		st = conn.createStatement();

		/***************************/
		/* Execute selection query */
		/***************************/
		rs = st.executeQuery(query);
		rsmd = rs.getMetaData();
		columnsNumber = rsmd.getColumnCount();

		/*******************/
		/* Return database */
		/*******************/
		while (rs.next())
		{
		    for (int i=1; i<=columnsNumber; i++)
			database = rs.getString(i);
		}

		/********************/
		/* Define SQL query */
		/********************/
		query = "SHOW VARIABLES WHERE Variable_name = ?";

		/********************/
		/* Create Statement */
		/********************/
		ps = conn.prepareStatement(query);
		ps.setString(1, "hostname");

		/*********************/
		/* Execute SQL query */
		/*********************/
		rs = ps.executeQuery();
		rsmd = rs.getMetaData();
		columnsNumber = rsmd.getColumnCount();

		/*******************/
		/* Return hostname */
		/*******************/
		while (rs.next())
		{
		    for (int i=1; i<=columnsNumber; i++)
	       	    {
			if (i == 2)
			    hostname = rs.getString(i);
		    }
		    System.out.println("Connection to " + database + " (database) on " + hostname + " is active.");
		}
	    }
	    catch(Exception e)
	    {
		System.err.println(e.getMessage());
		System.exit(1);
	    }
	}
	else
	{
	    System.out.println("There are no active database connections.");
	}
    }
}
