/* HEADER FILE: MMapperReader.h */

#ifndef MMAPPERREADER_H
#define MMAPPERREADER_H
#include <string>
#include <vector>
#include "ImageRetirement.h"

class MMapperReader
{
  /*************************************************/
  /* Declaration/Initialization of class variables */
  /*************************************************/
  private:
    static const int MarsAppID = 18;
    static const int MercuryAppID = 5;
    static const int MoonAppID = 1;

  /**********************************/
  /* Declaration of class functions */
  /**********************************/
  public:
    static bool FileExists(std::string);
    static std::vector <ImageRetirement> GetData(std::string);
    static void display(std::vector <ImageRetirement>);
};

#endif
