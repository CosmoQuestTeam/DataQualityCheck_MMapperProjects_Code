/* HEADER FILE: DataQualityAnalysis.h */

#ifndef DATAQUALITYANALYSIS_H
#define DATAQUALITYANALYSIS_H
#include <string>
#include <vector>
#include "ImageRetirement.h"

class DataQualityAnalysis
{
  /*************************************************/
  /* Declaration/Initialization of class variables */
  /*************************************************/
  private:
    static const int MercuryAppID = 5;
    static const int MoonAppID = 1;
    static const int MarsAppID = 18;
    std::vector <ImageRetirement> data;
    std::vector <int> nDoneImages;
    std::vector <int> nExcessivelyDoneImages;
    std::vector <int> nExcessivelyShouldBeDoneImages;
    std::vector <int> nImages;
    std::vector <int> nNotDoneImages;
    std::vector <int> nNotTruelyDoneImages;
    std::vector <int> nShouldBeDoneImages;
    
    /**********************************/
    /* Declaration of class functions */
    /**********************************/
    public:
      DataQualityAnalysis(std::vector <ImageRetirement> &);
      std::vector <int> GetNDoneImages();
      std::vector <int> GetNExcessivelyDoneImages();
      std::vector <int> GetNExcessivelyShouldBeDoneImages();
      std::vector <int> GetNImages();
      std::vector <int> GetNNotDoneImages();
      std::vector <int> GetNNotTruelyDoneImages();
      std::vector <int> GetNShouldBeDoneImages();
      void Calculate();
};

#endif
