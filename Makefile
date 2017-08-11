#Makefile

TOPDIR = $(dir $(abspath $(lastword $(MAKEFILE_LIST))))
CLASSDIR = $(TOPDIR)classes
SOURCEDIR = $(TOPDIR)sources
SOURCES = $(SOURCEDIR)/DataQuality

JC = javac
JFLAGS = -sourcepath $(SOURCEDIR) -d $(CLASSDIR) -Xlint:unchecked -g

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = $(SOURCES)/DataQualityCheck.java \
	  $(SOURCES)/ImageRetirementCheck.java \
	  $(SOURCES)/ImageRetirementCheckStatus.java \
	  $(SOURCES)/Images.java \
	  $(SOURCES)/ImageSets.java \
	  $(SOURCES)/Marks.java \
	  $(SOURCES)/MySQLConnection.java \
	  $(SOURCES)/NullCheck.java \
	  $(SOURCES)/NullCheckStatus.java \
	  $(SOURCES)/RetrieveImages.java \
	  $(SOURCES)/RetrieveImageSets.java \
	  $(SOURCES)/RetrieveMarks.java \
	  $(SOURCES)/SortMarks.java 

classes: $(CLASSES:.java=.class)

clean:
	$(RM) $(CLASSDIR)/DataQuality/*.class 
