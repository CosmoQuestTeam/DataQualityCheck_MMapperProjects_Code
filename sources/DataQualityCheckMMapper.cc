#include <iostream>
#include <stdlib.h>
#include <string>
#include <vector>

#include "DataQualityAnalysis.h"
#include "ImageRetirement.h"
#include "MMapperPlotter.h"
#include "MMapperReader.h"

using namespace std;

int main(int argc, char* argv[])
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  string DataFile;
  vector <ImageRetirement> data;
  vector <int> arr1;
  vector <int> arr2;
  vector <int> arr3;
  vector <int> arr4;
  vector <int> arr5;
  vector <int> arr6;
  vector <int> arr7;
  
  /*********************/
  /* Verify Parameters */
  /*********************/
  if(argc < 2)
  {
    printf("USAGE: %s [Data File]\n", argv[0]);
    return -1;
  }

  /***********************/
  /* Retrieve arguements */
  /***********************/
  DataFile.assign(argv[1]);

  /*****************/
  /* Retrieve data */
  /*****************/
  data = MMapperReader::GetData(DataFile);
  //MMapperReader::display(data); exit(0);

  /***************************/
  /* Analyze quality of data */
  /***************************/
  DataQualityAnalysis myDataQualityAnalysis(data); 
  myDataQualityAnalysis.Calculate();
  arr1 = myDataQualityAnalysis.GetNDoneImages();
  arr2 = myDataQualityAnalysis.GetNExcessivelyDoneImages();
  arr3 = myDataQualityAnalysis.GetNExcessivelyShouldBeDoneImages();
  arr4 = myDataQualityAnalysis.GetNImages();
  arr5 = myDataQualityAnalysis.GetNNotDoneImages();
  arr6 = myDataQualityAnalysis.GetNNotTruelyDoneImages();
  arr7 = myDataQualityAnalysis.GetNShouldBeDoneImages();

  /**************************/
  /* Inialize plotter class */
  /**************************/
  MMapperPlotter myPlotter(arr1, arr2, arr3, arr4, arr5, arr6, arr7);

  /*******************************/
  /* Plot image counts bar graph */
  /*******************************/
  myPlotter.ImageCountsBarGraph();

  /*******************************/
  /* Plot image counts pie chart */
  /*******************************/
  myPlotter.ImageCountsPieChart();

  /***********************************************/
  /* Plot active versus retired images pie chart */
  /***********************************************/
  myPlotter.RetiredvsActiveImagesPieChart();

  /*******************************************/
  /* Plot retired images breakdown pie chart */
  /*******************************************/
  myPlotter.RetiredImagesBreakdownPieChart();
  
  /*******************************************************/
  /* Plot retired images under quota breakdown pie chart */
  /*******************************************************/
  myPlotter.RetiredImagesUnderQuotaBreakdownPieChart();

  /******************************************************/
  /* Plot retired images over quota breakdown pie chart */
  /******************************************************/
  myPlotter.RetiredImagesOverQuotaBreakdownPieChart();

  /******************************************/
  /* Plot active images breakdown pie chart */
  /******************************************/
  myPlotter.ActiveImagesBreakdownPieChart();

  /***********************************************************/
  /* Plot active images that meets quota breakdown pie chart */
  /***********************************************************/
  myPlotter.ActiveImagesMeetsQuotaBreakdownPieChart();

  /*****************************************************/
  /* Plot active images over quota breakdown pie chart */
  /*****************************************************/
  myPlotter.ActiveImagesOverQuotaBreakdownPieChart();

  return 0;
}


