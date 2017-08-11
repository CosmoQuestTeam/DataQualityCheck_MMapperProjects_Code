/* HEADER FILE: ImageRetirement.h */

#ifndef IMAGERETIREMENT_H
#define IMAGERETIREMENT_H
#include <string>
#include <vector>

class ImageRetirement
{
    /**********************************/
    /* Declaration of class variables */
    /**********************************/
    private: 
      int ApplicationID;
      int Done;
      int ImageID;
      int UnderQuota;
      int Quota;
      int OverQuota;

    /**********************************/
    /* Declaration of class functions */
    /**********************************/
    public:
      ImageRetirement(int, int, int, int, int, int);    
      int GetApplicationID();
      int GetDone();
      int GetImageID();
      int GetOverQuota();
      int GetQuota();
      int GetUnderQuota();
      std::string GetDoneString();
      std::string GetOverQuotaString();
      std::string GetQuotaString();
      std::string GetUnderQuotaString();
};

#endif
