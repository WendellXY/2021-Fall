FILENAME = Playground
DATE=`date +'%y%m%d'`
NEW_FILENAME = $(FILENAME)$(DATE)

ret:
	@javac Playground.java
	@java Playground
	@rm *.class

archive:
	@sed -i '' s/Playground/$(NEW_FILENAME)/g Playground.java
	@mv Playground.java $(NEW_FILENAME).java
	@cp Playgroud.java.template Playground.java

undo_archive:
	@echo 'This only works for the file that is archive today.'
	@mv $(NEW_FILENAME).java Playground.java
	@sed -i '' s/$(NEW_FILENAME)/Playground/g Playground.java