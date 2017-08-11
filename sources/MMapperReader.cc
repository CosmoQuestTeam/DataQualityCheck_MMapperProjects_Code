#include <cstdio>
#include <fstream>
#include "MMapperReader.h"

using namespace std;

bool MMapperReader::FileExists(string Filename)
{ 
  /*******************************************/
  /* Declaration/Initialization of Variables */
  /*******************************************/
  ifstream InFile;

  /***************************/
  /* Check existance of file */
  /***************************/
  InFile.open(Filename.c_str());
  if(InFile)
  { 
    InFile.close();
    return true;
  }
  InFile.close();

  return false;
}

vector <ImageRetirement> MMapperReader::GetData(string Filename)
{
  /*******************************************/
  /* Declaration/Initialization of Variables */
  /*******************************************/
  ifstream InFile;
  string buffer;
  string format;
  vector <ImageRetirement> data;

  /******************/
  /* Open data file */
  /******************/
  if(FileExists(Filename))
    InFile.open(Filename.c_str());
  else
  {
    printf("Error: File %s does not exist! Now terminating simulation ...\n", Filename.c_str());
    exit(EXIT_FAILURE);
  }

  /*********************/
  /* Read-in data file */
  /*********************/
  format = "%d %ld %d %d %d %d";
  while(getline(InFile, buffer))
  { 
    int i1, i2, i3, i4, i5, l1;
    int nelements = sscanf(buffer.c_str(), format.c_str(), &i1, &l1, &i2, &i3, &i4, &i5);
    if((nelements == 6) && ((i1 == MarsAppID) || (i1 == MercuryAppID) || (i1 == MoonAppID)))
    { 
      ImageRetirement myDatapoint(i1, l1, i2, i3, i4, i5);
      data.push_back(myDatapoint);
    }
  }
  InFile.close();
  
  return data;
}

void MMapperReader::display(vector <ImageRetirement> list)
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  int nelements;
  
  nelements = list.size();
  for(int i=0; i<nelements; i++)
  {
    printf("Application_ID: %d Image_ID: %d Done: %d Under_Quota: %d Meets_Quota: %d Exceeds_Quota: %d\n", list[i].GetApplicationID(), list[i].GetImageID(), list[i].GetDone(), list[i].GetUnderQuota(), list[i].GetQuota(), list[i].GetOverQuota());
  }

  return;
}
