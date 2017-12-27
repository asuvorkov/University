# simple script for compilation of sources, to check, if there are
# no errors in the project.
# will not create any resources.

rm -r classes
mkdir classes
javac -d classes src/name/panitz/game/framework/*.java src/name/panitz/game/example/simple/*.java
