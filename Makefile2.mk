#Makefile2

TOPDIR = $(dir $(abspath $(lastword $(MAKEFILE_LIST))))
BINDIR = $(TOPDIR)bin
INCDIR = $(TOPDIR)include
OBJDIR = $(TOPDIR)obj
SRCDIR = $(TOPDIR)sources
SOURCES := $(wildcard $(SRCDIR)/*.cc)
OBJECTS := $(patsubst $(SRCDIR)/%.cc,$(OBJDIR)/%.o,$(SOURCES))
EXECUTABLE1 := $(BINDIR)/DataQualityCheckMMapper
EXE1_OBJS := $(OBJDIR)/DataQualityAnalysis.o $(OBJDIR)/DataQualityCheckMMapper.o $(OBJDIR)/ImageRetirement.o $(OBJDIR)/MMapperPlotter.o $(OBJDIR)/MMapperReader.o 

CC = g++
CFLAGS = -g -Wshadow -Wall -Wextra -O3 -march=native -mavx
LDFLAGS = -lm
ROOT = $(ROOTSYS)
ROOTINCDIR = $(ROOT)/include/root
ROOTLIBDIR = $(ROOT)/lib/root
ROOTCONFIG = -L$(ROOTLIBDIR) -lCore -lCint -lRIO -lNet -lHist -lGraf -lGraf3d -lGpad -lTree -lRint -lPostscript -lMatrix -lPhysics -lMathCore -lThread

all : $(OBJECTS) $(EXECUTABLE1)

$(EXECUTABLE1) : $(EXE1_OBJS)
	$(CC) $(CFLAGS) $(LDFLAGS) $(ROOTCONFIG) $(EXE1_OBJS) -o $@

$(OBJDIR)/%.o : $(SRCDIR)/%.cc
	$(CC) -c $(CFLAGS) -I$(INCDIR) -I$(ROOTINCDIR) -MMD $< -o $@

.PHONY : clean

clean:
	rm -rf $(OBJECTS) $(EXECUTABLE1) $(OBJDIR)/*.d $(OBJDIR)/*.o
