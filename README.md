# tsp-euchre
Automatically exported from code.google.com/p/tsp-euchre - which is now archived by Google.

All code prior to import Licensed: Eclipse Public License 1.0

# Status

Note: The original December 2010 jar file has been preserved as Euchre_original_compile.jar for sake of testing the last-known public release.

I was unable to locate any build instructions for the jar file. I created a build.xml for ANT that can be used to compile, jar, and run the application.

   $ ant compile
   $ ant jar
   $ ant run
   
When playing 1 human card player against 3 AI players, the game seems to stall and get confused on whose turn it is.  This seems to happen in the original public compiled jar of the game.  I have been unable to determine with certainy if this was a bug in the software (perhaps incompmplete) - or some other factor such as newer java runtimes are not executing the older year 2010 code with the same behavior.

Please open an issue if you want to help contribute and compare notes.  Thank you.
