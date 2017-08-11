package DataQuality;

public class ImageRetirementCheckStatus
{
    /*************************************************/
    /* Declaration/Initialization of class variables */
    /*************************************************/
    private int ApplicationID;
    private int done;
    private int ImageID;
    private int UnderQuota;
    private int Quota;
    private int OverQuota;

    ImageRetirementCheckStatus(int ApplicationID, int ImageID, int done, int UnderQuota, int Quota, int OverQuota)
    {
	this.ApplicationID = ApplicationID;
	this.ImageID = ImageID;
	this.done = done;
	this.UnderQuota = UnderQuota;
	this.Quota = Quota;
	this.OverQuota= OverQuota;
    }
    
    int GetApplicationID()
    {
	return ApplicationID;
    }

    int GetDone()
    {
	return done;
    }

    int GetImageID()
    {
	return ImageID;
    }

    int GetOverQuota()
    {
	return OverQuota;
    }

    int GetQuota()
    {
	return Quota;
    }

    int GetUnderQuota()
    {
	return UnderQuota;
    }

    String GetDoneString()
    {
	if(done == 1)
	{
	    return "YES";
	}
	
	return "NO";
    }

    String GetOverQuotaString()
    {
	if(OverQuota == 1)
	{
	    return "YES";
	}
	
	return "NO";
    }

    String GetQuotaString()
    {
	if(Quota == 1)
	{
	    return "YES";
	}
	
	return "NO";
    }

    String GetUnderQuotaString()
    {
	if(UnderQuota == 1)
	{
	    return "YES";
	}
	
	return "NO";
    }
}
