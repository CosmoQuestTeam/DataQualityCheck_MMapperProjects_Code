#include "ImageRetirement.h"

using namespace std;

ImageRetirement::ImageRetirement(int applicationID, int imageID, int done, int underquota, int quota, int overquota)
{
  ApplicationID = applicationID;
  ImageID = imageID;
  Done = done;
  UnderQuota = underquota;
  Quota = quota;
  OverQuota= overquota;
}
    
int ImageRetirement::GetApplicationID()
{
  return ApplicationID;
}

int ImageRetirement::GetDone()
{
  return Done;
}

int ImageRetirement::GetImageID()
{
  return ImageID;
}

int ImageRetirement::GetOverQuota()
{
  return OverQuota;
}

int ImageRetirement::GetQuota()
{
  return Quota;
}

int ImageRetirement::GetUnderQuota()
{
  return UnderQuota;
}

string ImageRetirement::GetDoneString()
{
  if(Done == 1)
  {
    return "YES";
  }
  
  return "NO";
}

string ImageRetirement::GetOverQuotaString()
{
  if(OverQuota == 1)
  {
    return "YES";
  }
	
  return "NO";
}

string ImageRetirement::GetQuotaString()
{
  if(Quota == 1)
  {
    return "YES";
  }
  
  return "NO";
}

string ImageRetirement::GetUnderQuotaString()
{
  if(UnderQuota == 1)
  {
    return "YES";
  }
  
  return "NO";
}
