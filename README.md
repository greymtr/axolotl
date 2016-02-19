<p align = "center">
<h1> Axolotl </h1>

<h4> One Syntax to rule them all ! </h4>



<img src = "logo.png" align = "center" style = "border-radius:5px" > 
</p>

---
 __Axolotl__ is a _pseudo -_ programming langauge.
 
### Wait.... So What does that mean ?
 
Well, most languages interact with the machine directly, you write your code and you get it translated to machine-understandable spaghetti which does stuff ! 
Axolotl doesn't do this directly.

Axolotl's user input consists of a modifiable tag-based syntax. Axolotl converts this into actual source code of another language, which can then be converted into the machine understandable code.

### Okay, but what can this be used for ? 

Axolotl was originally written to provide writers with a really easy way to make interactive fiction. It was meant to be an IF engine where you just type in your story, add the tags at the right places and you'll g fully-functioning IF, but early on in the development I realized that it could be used as a programming aid so we decided to pursue that idea.

Axolotl can be used in multiple ways such as, but not limited to :

-     A Prototyping Tool
-     An educational tool for beginners
-     Generating the same program in multiple languages
-     A way to write programs in any language really quickly.

You can also download and use plugins that provide additional functionality.

### A.... plugin system ?

Yeah ! Just like a browser or an editor. Programming languages exist as "language packs" that can be downloaded as and when required.

### So how am I supposed to use it ?
 
Axolotl can be run on any unix-like system, including OSX and all flavours of linux.

An example of usage where the output language is java : 

 
```sh
$ cat input
<d] Hello World ! [d>
$ touch output
$ cat output
$ axolotl input output java
Enter name of class   : HelloWorld!                
$ cat output 
```
```Java
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
- [ ]   Support for languages.
- [ ]   Immediate Execution
- [ ]   Menu Interfaced Guidance
- [ ]   Language-dependent data structures
- [ ]   Methods and Objects usage and generation

