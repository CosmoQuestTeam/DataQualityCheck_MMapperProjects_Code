#include "DataQualityAnalysis.h"

using namespace std;

DataQualityAnalysis::DataQualityAnalysis(vector <ImageRetirement> &list)
{
  /***************************/
  /* Initialize data vectors */
  /***************************/
  nDoneImages.assign(5, 0);
  nExcessivelyDoneImages.assign(5, 0);
  nExcessivelyShouldBeDoneImages.assign(5, 0);
  nImages.assign(5, 0);
  nNotDoneImages.assign(5, 0);
  nNotTruelyDoneImages.assign(5, 0);
  nShouldBeDoneImages.assign(5, 0);
  data = list;
}

vector <int> DataQualityAnalysis::GetNDoneImages()
{
  return nDoneImages;
}

vector <int> DataQualityAnalysis::GetNExcessivelyDoneImages()
{
  return nExcessivelyDoneImages;
}

vector <int> DataQualityAnalysis::GetNExcessivelyShouldBeDoneImages()
{
  return nExcessivelyShouldBeDoneImages;
}

vector <int> DataQualityAnalysis::GetNImages()
{
  return nImages;
}

vector <int> DataQualityAnalysis::GetNNotDoneImages()
{
  return nNotDoneImages;
}

vector <int> DataQualityAnalysis::GetNNotTruelyDoneImages()
{
  return nNotTruelyDoneImages;
}

vector <int> DataQualityAnalysis::GetNShouldBeDoneImages()
{
  return nShouldBeDoneImages;
}

void DataQualityAnalysis::Calculate()
{
  /************************************/
  /* Determine total number of images */
  /************************************/
  nImages[0] = (int)data.size();

  /*******************************************************************************/
  /* Iterate through image data to ascertain quality of image data from database */
  /*******************************************************************************/
  for(int i=0; i<nImages[0]; i++)
  {
    int application_id = data[i].GetApplicationID();
    int done = data[i].GetDone();
    int index;
    
    /********************************************************/
    /* Determine which app current image is associated with */
    /********************************************************/
    if(application_id == MarsAppID)
    {
      index = 1;
    }
    else if(application_id == MercuryAppID)
    {
      index = 2;
    }
    else if(application_id == MoonAppID)
    {
      index = 3;
    }
    else
    {
      index = 4;
    }

    nImages[index]++;
    if(done == 1)
    {
      nDoneImages[0]++;
      if(data[i].GetUnderQuota() == 1)
      {
  	nNotTruelyDoneImages[0]++;
  	nNotTruelyDoneImages[index]++;
      }
      else if(data[i].GetQuota() == 1)
      {
  	nDoneImages[index]++;
      }
      else
      {
  	nExcessivelyDoneImages[0]++;
  	nExcessivelyDoneImages[index]++;
      }
    }
    else
    {
      nNotDoneImages[0]++;
      if(data[i].GetUnderQuota() == 1)
      {
  	nNotDoneImages[index]++;
      }
      else if(data[i].GetQuota() == 1)
      {
  	nShouldBeDoneImages[0]++;
  	nShouldBeDoneImages[index]++;
      }
      else
      {
  	nExcessivelyShouldBeDoneImages[0]++;
  	nExcessivelyShouldBeDoneImages[index]++;
      }
    }
  }
}
