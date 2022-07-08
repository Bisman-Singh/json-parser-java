# JSON Parser Makefile
# Author: Bisman Singh <bismanmadaan1@gmail.com>

JAVAC = javac
JAVA = java
JAVAFLAGS = -source 17 -target 17
SRCDIR = src
OUTDIR = out

.PHONY: all clean run

all:
	mkdir -p $(OUTDIR)
	$(JAVAC) $(JAVAFLAGS) -d $(OUTDIR) $(SRCDIR)/*.java

run: all
	$(JAVA) -cp $(OUTDIR) Main '{"name":"test"}'

clean:
	rm -rf $(OUTDIR)
