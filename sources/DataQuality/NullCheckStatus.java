package DataQuality;

public class NullCheckStatus
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    private int index;
    private String note;
    private String table;

    NullCheckStatus(String table, int index, String note)
    {
	this.index = index;
	this.note = note;
	this.table = table;
    }
    
    int GetIndex()
    {
	return index;
    }

    String GetNote()
    {
	return note;
    }

    String GetTable()
    {
	return table;
    }
}
