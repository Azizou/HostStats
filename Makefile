#Simple makefile to allows eclipse independent compilation of the source code
SOURCES := src/amazon/Host.java src/amazon/HostsManager.java src/amazon/InstanceStatisticDriver.java
PROGRAM := InstanceStatisticDriver

##ONLY TESTED On Linux
JAVAC := javac
JAVA := java
JAVAFLAGS = -g

all: $(PROGRAM)

$(PROGRAM):	$(SOURCES)
	$(JAVAC) $(JAVAFLAGS) $<
	
.java.class:
	$(JAVAC) $(JAVAFLAGS) $<
	
clean:
	rm -rf *.class