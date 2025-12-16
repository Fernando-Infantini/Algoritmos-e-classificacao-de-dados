JAVAC = javac
JAR = jar
JAVA = java

MAIN_CLASS = Main
JAR_NAME = Jakara.jar
CLASSPATH = flatlaf.jar

SOURCES = Main.java
CLASSES = Main.class
RESOURCES = quill-pen-fill.png
MANIFEST = manifest.txt

all: $(JAR_NAME)

$(CLASSES): $(SOURCES)
	$(JAVAC) -cp $(CLASSPATH) $(SOURCES)

$(JAR_NAME): $(CLASSES) $(RESOURCES) $(MANIFEST)
	$(JAR) cfm $(JAR_NAME) $(MANIFEST) $(CLASSES) $(RESOURCES)

run: $(JAR_NAME)
	$(JAVA) -jar $(JAR_NAME)

clean:
	rm -f $(CLASSES) $(JAR_NAME)
