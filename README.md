# CYK_Algorithm

CYK Algorithm
To run CYK.jar make sure you have Java 1.8.+ installed.
Use “java –jar path-to-file/CYK.jar” command to run test.
This jar file run CYK algorithm for two languages:
1. a+a*a(a*) -> not accepted
2. (a-(a+a)*(a-a))+a -> accepted
• Digits are replaced by a terminal variable ‘a’
• The Grammar used for arithmetic languages is: S->SAS|SBS|SCS|SDS|ESF|a$A->+$B->-$C->*$D->/$E->($F->)
• For modifying code read comments to apply correct structure.
