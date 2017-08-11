#include "MMapperPlotter.h"
#include "TCanvas.h"
#include "TGaxis.h"
#include "TH1.h"
#include "TLegend.h"
#include "TPaveText.h"
#include "TPie.h"

using namespace std;

MMapperPlotter::MMapperPlotter(vector <int> &NDoneImages, vector <int> &NExcessivelyDoneImages, vector <int> &NExcessivelyShouldBeDoneImages, vector <int> &NImages, vector <int> &NNotDoneImages, vector <int> &NNotTruelyDoneImages, vector <int> &NShouldBeDoneImages)
{
  /***************************/
  /* Initialize data vectors */
  /***************************/
  NDI = NDoneImages;
  NEDI = NExcessivelyDoneImages;
  NESBDI = NExcessivelyShouldBeDoneImages;
  NI = NImages;
  NNDI = NNotDoneImages;
  NNTDI = NNotTruelyDoneImages;
  NSBDI = NShouldBeDoneImages;
}

void MMapperPlotter::ActiveImagesBreakdownPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 5;
  const char *labels[nvals] = {"Mars", "Mercury", "Moon", "MeetsQuota", "ExceedsQuota"}; // Partition labels
  float ActiveImagesExceedsQuotaPercentage;
  float ActiveImagesMeetsQuotaPercentage;
  float ActiveMarsImagesPercentage;
  float ActiveMercuryImagesPercentage;
  float ActiveMoonImagesPercentage;
  int canheight;
  int canwidth;
  TCanvas *can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3, 4, 5, 6};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  ActiveImagesExceedsQuotaPercentage = (float)NESBDI[0]/NNDI[0];
  ActiveImagesMeetsQuotaPercentage = (float)NSBDI[0]/NNDI[0];
  ActiveMarsImagesPercentage = (float)NNDI[1]/NNDI[0];
  ActiveMercuryImagesPercentage = (float)NNDI[2]/NNDI[0];
  ActiveMoonImagesPercentage = (float)NNDI[3]/NNDI[0];
  float vals[nvals] = {ActiveMarsImagesPercentage, ActiveMercuryImagesPercentage, ActiveMoonImagesPercentage, ActiveImagesMeetsQuotaPercentage, ActiveImagesExceedsQuotaPercentage};

  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Active Images Breakdown", nvals, vals, colors);
  pie->SetAngularOffset(-45.0);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Active Images Breakdown", canwidth, canheight);
  
  /************************************/
  /* Draw pie chart in current canvas */
  /************************************/
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.73, 0.77, 0.98, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Statistic 5: %d total images", NNDI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Normal Active Mars Images: %f %%", ActiveMarsImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Normal Active Mercury Images: %f %%", ActiveMercuryImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Normal Active Moon Images: %f %%", ActiveMoonImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Active Images Under Quota: %f %%", ActiveImagesMeetsQuotaPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Active Images Exceeding Quota: %f %%", ActiveImagesExceedsQuotaPercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("ActiveImagesBreakdown_PieChart.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}

void MMapperPlotter::ActiveImagesMeetsQuotaBreakdownPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 3;
  const char *labels[nvals] = {"Mars", "Mercury", "Moon"}; // Partition labels
  float ActiveMeetsQuotaMarsImagesPercentage;
  float ActiveMeetsQuotaMercuryImagesPercentage;
  float ActiveMeetsQuotaMoonImagesPercentage;
  int canheight;
  int canwidth;
  TCanvas *can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3, 4};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  ActiveMeetsQuotaMarsImagesPercentage = (float)NSBDI[1]/NSBDI[0];
  ActiveMeetsQuotaMercuryImagesPercentage = (float)NSBDI[2]/NSBDI[0];
  ActiveMeetsQuotaMoonImagesPercentage = (float)NSBDI[3]/NSBDI[0];
  float vals[nvals] = {ActiveMeetsQuotaMarsImagesPercentage, ActiveMeetsQuotaMercuryImagesPercentage, ActiveMeetsQuotaMoonImagesPercentage};

  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Active Images Meets Quota Breakdown", nvals, vals, colors);
  pie->SetAngularOffset(-30.0);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Active Images Meets Quota Breakdown", canwidth, canheight);
  
  /************************************/
  /* Draw pie chart in current canvas */
  /************************************/
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.73, 0.77, 0.98, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Statistic 6: %d total images", NSBDI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Meets Quota Active Mars Images: %f %%", ActiveMeetsQuotaMarsImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Meets Quota Active Mercury Images: %f %%", ActiveMeetsQuotaMercuryImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Meets Quota Active Moon Images: %f %%", ActiveMeetsQuotaMoonImagesPercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("ActiveImagesMeetsQuotaBreakdown_PieChart.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}

void MMapperPlotter::ActiveImagesOverQuotaBreakdownPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 3;
  const char *labels[nvals] = {"Mars", "Mercury", "Moon"}; // Partition labels
  float ActiveOverQuotaMarsImagesPercentage;
  float ActiveOverQuotaMercuryImagesPercentage;
  float ActiveOverQuotaMoonImagesPercentage;
  int canheight;
  int canwidth;
  TCanvas *can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3, 4};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  ActiveOverQuotaMarsImagesPercentage = (float)NESBDI[1]/NESBDI[0];
  ActiveOverQuotaMercuryImagesPercentage = (float)NESBDI[2]/NESBDI[0];
  ActiveOverQuotaMoonImagesPercentage = (float)NESBDI[3]/NESBDI[0];
  float vals[nvals] = {ActiveOverQuotaMarsImagesPercentage, ActiveOverQuotaMercuryImagesPercentage, ActiveOverQuotaMoonImagesPercentage};

  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Active Images Over Quota Breakdown", nvals, vals, colors);
  pie->SetAngularOffset(-140.0);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Active Images Over Quota Breakdown", canwidth, canheight);
  
  /************************************/
  /* Draw pie chart in current canvas */
  /************************************/
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.73, 0.77, 0.98, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Statistic 7: %d total images", NESBDI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Over Quota Active Mars Images: %f %%", ActiveOverQuotaMarsImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Over Quota Active Mercury Images: %f %%", ActiveOverQuotaMercuryImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Over Quota Active Moon Images: %f %%", ActiveOverQuotaMoonImagesPercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("ActiveImagesOverQuotaBreakdown_PieChart.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}

void MMapperPlotter::ImageCountsBarGraph()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nlabels = 3;
  const char *labels[nlabels] = {"Moon", "Mars", "Mercury"}; // Axis labels
  int canheight;
  int canwidth;
  TCanvas* can = NULL;
  TH1F *hist = NULL;
  TPaveText *pt = NULL;

  /*********************/
  /* Initialize Canvas */
  /*********************/
  TGaxis::SetMaxDigits(3);
  canheight = 600;
  canwidth = 800;
  can = new TCanvas("Canvas", "Number of images per project", canwidth, canheight);
  can->SetGrid();
  can->SetRightMargin(0.25);
  can->SetLeftMargin(0.15);
  can->SetBottomMargin(0.1);
  can->SetTopMargin(0.1);
  
  /************************/
  /* Initialize histogram */
  /************************/
  hist = new TH1F("Histogram", "", 3, 0, 3);
  hist->SetTitle("Number of Images per Application");
  hist->GetXaxis()->SetTitle("Application");
  hist->GetXaxis()->CenterTitle();
  hist->GetYaxis()->SetTitle("Frequency");
  hist->GetYaxis()->CenterTitle();
  hist->SetStats(0);
  hist->SetFillColor(38);
  hist->SetBit(TH1::kCanRebin);
  
  /******************/
  /* Fill histogram */
  /******************/
  hist->Fill(labels[0], NI[3]);
  hist->Fill(labels[1], NI[1]);
  hist->Fill(labels[2], NI[2]);

  /******************/
  /* Draw histogram */
  /******************/
  hist->LabelsDeflate();
  hist->Draw();

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.6, 0.77, 0.88, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Application Image Counts (Total: %d)", NI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Total Number of Mars Images: %d", NI[1]);
  pt->AddText(buffer);
  sprintf(buffer, "Total Number of Mercury Images: %d", NI[2]);
  pt->AddText(buffer);
  sprintf(buffer, "Total Number of Moon Images: %d", NI[3]);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("ImageCounts_BarGraph.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete hist;
  delete pt;

  return;
}

void MMapperPlotter::ImageCountsPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 3;
  const char *labels[nvals] = {"Mars", "Mercury", "Moon"}; // Partition labels
  float MarsImagePercentage;
  float MercuryImagePercentage;
  float MoonImagePercentage;
  int canheight;
  int canwidth;
  TCanvas* can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3, 4};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  MarsImagePercentage = (float)NI[1]/NI[0];
  MercuryImagePercentage = (float)NI[2]/NI[0];
  MoonImagePercentage = (float)NI[3]/NI[0];
  float vals[nvals] = {MarsImagePercentage, MercuryImagePercentage, MoonImagePercentage};

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Percentage of Images per Application", canwidth, canheight);
  
  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Project Image Percentages", nvals, vals, colors);
  pie->SetAngularOffset(35.0);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.68, 0.72, 0.98, 0.9, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Application Image Percentages (Total: %d)", NI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Mars Images Percentage: %f %%", MarsImagePercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Mercury Images Percentage: %f %%", MercuryImagePercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Moon Images Percentage: %f %%", MoonImagePercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("ImageCounts_PieChart.ps");
  
  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}

void MMapperPlotter::RetiredImagesBreakdownPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 5;
  const char *labels[nvals] = {"Mars", "Mercury", "Moon", "UnderQuota", "ExceedsQuota"}; // Partition labels
  float RetiredImagesExceedsQuotaPercentage;
  float RetiredImagesUnderQuotaPercentage;
  float RetiredMarsImagesPercentage;
  float RetiredMercuryImagesPercentage;
  float RetiredMoonImagesPercentage;
  int canheight;
  int canwidth;
  TCanvas *can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3, 4, 5, 6};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  RetiredImagesExceedsQuotaPercentage = (float)NEDI[0]/NDI[0];
  RetiredImagesUnderQuotaPercentage = (float)NNTDI[0]/NDI[0];
  RetiredMarsImagesPercentage = (float)NDI[1]/NDI[0];
  RetiredMercuryImagesPercentage = (float)NDI[2]/NDI[0];
  RetiredMoonImagesPercentage = (float)NDI[3]/NDI[0];
  float vals[nvals] = {RetiredMarsImagesPercentage, RetiredMercuryImagesPercentage, RetiredMoonImagesPercentage, RetiredImagesUnderQuotaPercentage, RetiredImagesExceedsQuotaPercentage};

  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Retired Images Breakdown", nvals, vals, colors);
  pie->SetAngularOffset(30.0);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Retired Images Breakdown", canwidth, canheight);
  
  /************************************/
  /* Draw pie chart in current canvas */
  /************************************/
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.73, 0.77, 0.98, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Statistic 2: %d total images", NDI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Normal Retired Mars Images: %f %%", RetiredMarsImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Normal Retired Mercury Images: %f %%", RetiredMercuryImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Normal Retired Moon Images: %f %%", RetiredMoonImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Retired Images Under Quota: %f %%", RetiredImagesUnderQuotaPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Retired Images Exceeding Quota: %f %%", RetiredImagesExceedsQuotaPercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("RetiredImagesBreakdown_PieChart.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}

void MMapperPlotter::RetiredImagesOverQuotaBreakdownPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 3;
  const char *labels[nvals] = {"Mars", "Mercury", "Moon"}; // Partition labels
  float RetiredOverQuotaMarsImagesPercentage;
  float RetiredOverQuotaMercuryImagesPercentage;
  float RetiredOverQuotaMoonImagesPercentage;
  int canheight;
  int canwidth;
  TCanvas *can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3, 4};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  RetiredOverQuotaMarsImagesPercentage = (float)NEDI[1]/NEDI[0];
  RetiredOverQuotaMercuryImagesPercentage = (float)NEDI[2]/NEDI[0];
  RetiredOverQuotaMoonImagesPercentage = (float)NEDI[3]/NEDI[0];
  float vals[nvals] = {RetiredOverQuotaMarsImagesPercentage, RetiredOverQuotaMercuryImagesPercentage, RetiredOverQuotaMoonImagesPercentage};

  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Retired Images Over Quota Breakdown", nvals, vals, colors);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Retired Images Over Quota Breakdown", canwidth, canheight);
  
  /************************************/
  /* Draw pie chart in current canvas */
  /************************************/
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.73, 0.77, 0.98, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Statistic 4: %d total images", NEDI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Over Quota Retired Mars Images: %f %%", RetiredOverQuotaMarsImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Over Quota Retired Mercury Images: %f %%", RetiredOverQuotaMercuryImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Over Quota Retired Moon Images: %f %%", RetiredOverQuotaMoonImagesPercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("RetiredImagesOverQuotaBreakdown_PieChart.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}

void MMapperPlotter::RetiredImagesUnderQuotaBreakdownPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 3;
  const char *labels[nvals] = {"Mars", "Mercury", "Moon"}; // Partition labels
  float RetiredUnderQuotaMarsImagesPercentage;
  float RetiredUnderQuotaMercuryImagesPercentage;
  float RetiredUnderQuotaMoonImagesPercentage;
  int canheight;
  int canwidth;
  TCanvas *can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3, 4};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  RetiredUnderQuotaMarsImagesPercentage = (float)NNTDI[1]/NNTDI[0];
  RetiredUnderQuotaMercuryImagesPercentage = (float)NNTDI[2]/NNTDI[0];
  RetiredUnderQuotaMoonImagesPercentage = (float)NNTDI[3]/NNTDI[0];
  float vals[nvals] = {RetiredUnderQuotaMarsImagesPercentage, RetiredUnderQuotaMercuryImagesPercentage, RetiredUnderQuotaMoonImagesPercentage};

  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Retired Images Under Quota Breakdown", nvals, vals, colors);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Retired Images Under Quota Breakdown", canwidth, canheight);
  
  /************************************/
  /* Draw pie chart in current canvas */
  /************************************/
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.73, 0.77, 0.98, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Statistic 3: %d total images", NNTDI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Under Quota Retired Mars Images: %f %%", RetiredUnderQuotaMarsImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Under Quota Retired Mercury Images: %f %%", RetiredUnderQuotaMercuryImagesPercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Under Quota Retired Moon Images: %f %%", RetiredUnderQuotaMoonImagesPercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("RetiredImagesUnderQuotaBreakdown_PieChart.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}

void MMapperPlotter::RetiredvsActiveImagesPieChart()
{
  /*******************************************/
  /* Declaration/Initialization of variables */
  /*******************************************/
  const int nvals = 2;
  const char *labels[nvals] = {"Retired", "Active"}; // Partition labels
  float ActiveImagePercentage;
  float RetiredImagePercentage;
  int canheight;
  int canwidth;
  TCanvas *can = NULL;
  TPaveText *pt = NULL;
  TPie *pie = NULL;

  /*********************************/
  /* Color selection for pie chart */
  /*********************************/
  int colors[nvals] = {2, 3};

  /*************************/
  /* Pie Chart percentages */
  /*************************/
  ActiveImagePercentage = (float)NNDI[0]/NI[0];
  RetiredImagePercentage = (float)NDI[0]/NI[0];
  float vals[nvals] = {RetiredImagePercentage, ActiveImagePercentage};

  /************************/
  /* Initialize pie chart */
  /************************/
  pie = new TPie("PieChart", "Retired Images vs Active Images", nvals, vals, colors);
  pie->SetRadius(0.35);
  for(int i=0; i<nvals; i++)
    pie->SetEntryLabel(i, labels[i]);

  /*********************/
  /* Initialize Canvas */
  /*********************/
  canheight = 700;
  canwidth = 700;
  can = new TCanvas("Canvas", "Retired Images vs Active Images", canwidth, canheight);
  
  /************************************/
  /* Draw pie chart in current canvas */
  /************************************/
  pie->Draw("sc");

  /***************/
  /* Draw legend */
  /***************/
  pt = new TPaveText(0.73, 0.77, 0.98, 0.90, "brNDC");
  pt->SetFillColor(18);
  pt->SetTextAlign(12);
  sprintf(buffer, "Statistic 1: %d total images", NI[0]);
  pt->AddText(buffer);
  sprintf(buffer, "Retired Images: %f %%", RetiredImagePercentage*100.0);
  pt->AddText(buffer);
  sprintf(buffer, "Active Images: %f %%", ActiveImagePercentage*100.0);
  pt->AddText(buffer);
  pt->Draw();

  /*************/
  /* Save plot */
  /*************/
  can->SaveAs("RetiredvsActiveImages_PieChart.ps");

  /************************/
  /*Free allocated memory */
  /************************/
  delete can;
  delete pie;
  delete pt;

  return;
}
