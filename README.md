# Axolotl

#### One Syntax to rule them all !
 
---
 __Axolotl__ is a _pseudo -_ programming langauge.
 
 Axolotl can be used as a programming language, or it can be used to emulate most programming languages, or it can be used to generate source code in multiple programs doing the same thing.
 
 But this is getting confusing. So let's get practical !
 
 
 
```sh
$ cat input
<d] Hello World ! [d>
$ touch output
$ cat output
$ axolotl input output
Enter name of class   : HelloWorld!                
$ cat output 
import java.util.*;
import java.io.*;


class HelloWorld!
{
public static void main(String args[]) throws Exception
{

Scanner sc=new Scanner(System.in);


System.out.println(" Hello World ! ");

}
}
$ 
```
 
## Features' Implementation status :
- [X]   Core "Compiler"
- [ ]   Plugin Support
- [ ]   Other Language Support
- [ ]   Immediate Execution
- [ ]   Menu Interfaced Guidance
