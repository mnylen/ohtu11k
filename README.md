# Mini project for Software Engineering course

##Prerequisites
1. JDK 6
2. git

## Downloading
1. `cd` to your source directory
2. `git clone https://github.com/mnylen/ohtu11k.git`

## Compiling
At commandline in project directory:
`javac -d class -cp lib/javacsv.jar src/fi/helsinki/cs/oato/*java src/fi/helsinki/cs/oato/gui/*java`

## Running
`java -cp lib/javacsv.jar:lib/joda-time-1.6.2.jar:lib/json_simple-1.1.jar:class fi.helsinki.cs.oato.gui.MainGUI`

## Commiting
Setup your commiter name:
1. `git config --global user.name "Your Name"` and
   `git config --global user.email you@example.com`
 
Commit your changes:
1. (edit files)
2. `git commit -a`
3. `git push`

Learn more: [Git Tutorial](http://www.kernel.org/pub/software/scm/git/docs/gittutorial.html)
