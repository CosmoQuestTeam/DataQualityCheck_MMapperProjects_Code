/* HEADER FILE: MMapperPlotter.h */

#ifndef MMAPPERPLOTTER_H
#define MMAPPERPLOTTER_H
#include <vector>

class MMapperPlotter
{
  /*************************************************/
  /* Declaration/Initialization of class variables */
  /*************************************************/
  private:
    static const int MAXBUFFERSIZE = 250;
    char buffer [MAXBUFFERSIZE];
    std::vector <int> NDI;
    std::vector <int> NEDI;
    std::vector <int> NESBDI;
    std::vector <int> NI;
    std::vector <int> NNDI;
    std::vector <int> NNTDI;
    std::vector <int> NSBDI;

  /**********************************/
  /* Declaration of class functions */
  /**********************************/
  public:
    MMapperPlotter(std::vector <int> &, std::vector <int> &, std::vector <int> &, std::vector <int> &, std::vector <int> &, std::vector <int> &, std::vector <int> &);
    void ActiveImagesBreakdownPieChart();
    void ActiveImagesMeetsQuotaBreakdownPieChart();
    void ActiveImagesOverQuotaBreakdownPieChart();
    void ImageCountsBarGraph();
    void ImageCountsPieChart();
    void RetiredImagesBreakdownPieChart();
    void RetiredImagesOverQuotaBreakdownPieChart();
    void RetiredImagesUnderQuotaBreakdownPieChart();
    void RetiredvsActiveImagesPieChart();
};

#endif
