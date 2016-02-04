import java.util.*;
import java.nio.file.*;
import java.io.*;

//----------------------------------

/*

    Official Documentation : http://ctrl-c.club/~greymtr/projects/Axolotl.html
                         http://ctrl-c.club/~greymtr/Documentation.html

*/

//--------------

/*

    Axolotl. A pseudo-programming languages used to write programs in multiple languages with a really simplified syntax, in order to facilitate easy learning of programming languages to novice programmers.
    Copyright (C) 2016  Greymtr Smith.

    License : GNU GPL v3

    Refer to LICENSE.txt inside the folder.

*/

//--------------

/*

    Please support us ! Recommend our software to programming beginners...
    Spread the word ! Distribute the software.
    As a user you are legally invested with the power to distribute, modify and share modified work provided it is licensed under the terms of the GNY GPLv3 or any other compatible copyleft software.

*/

//----------------------------------



class Axolotl
{

        public static void main(String args[]) throws Exception
        {

            String input = "", output = "";
            String x = "";

            switch( args.length )
            {
                case 1:
                    switch( args[0] )
                    {
                        case "--help":
                        case "--h":
                        case "h":
                            System.out.println("\n\nMake sure your files are present within the same directory as the .java and .class files of these programs...");
                            System.out.println("\n\n\n\n for help try giving --help as the first argument.\n\nFor license related issues Please give --license as an argument.\n\n\n\n\n\n VALID OPTIONS : \n\n\njava Axolotl --help\tjava Axolotl --h\n\njava Axolotl --license\tjava Axolotl -l\n\n\n\n\n For Example, Supposing you have axolotl-readable content in file \"file1\" and you want it's output to go into \"file2\" you need to type in :\n\n\tjava Axolotl file1 file2\n\n\n\n Alternatively on linux machines, due to the presence of the bash script you can do :\n\n\t./Axolotl.sh file1 file2\n\n\nBy adding your current directory at the end of your env $PATH , you can execute it like :\n\n\n\tAxolotl file1 file2 ");
                            System.exit(0);
                            break;

                        case "--license":
                        case "--l":
                        case "l":

                            try
                            {
                                System.out.println( new String( Files.readAllBytes( Paths.get("LICENSE.txt") ) ) );
                            }
                            catch( Exception E)
                            {
                                System.out.println("License File not found....\nPlease refer to the GNU GPLv3 online at \t:\thttp://www.gnu.org/licenses/gpl-3.0.html");
                            }

                            break;

                        default:
                                System.out.println("INVALID.\nFor help give argument --help. For license give argument --license");

                    }
                System.exit(0);
                break;

                case 0:
                    System.out.println("INVALID.\nFor help give argument --help. For license give argument --license");
                    System.exit(0);
                    break;

                case 2:
                    try
                    {
                        input = args[0];
                        output = args[1];
                        x = new String( Files.readAllBytes( Paths.get(input) ) );
                    }
                    catch(Exception E)
                    {
                        System.out.println("Invalid Input.\n\n Your input file's name should be the first argument when executing the program\n");
                        System.out.println("\n\nAlso make sure your files are present within the same directory as the .java and .class files of these programs...");
                        System.exit(0);
                    }
                    break;
            }

            if(args.length >= 3)
            {
                System.out.println("INVALID.\nFor help give argument --help. For license give argument --license");
                System.exit(0);
            }


            Scanner sc = new Scanner(System.in);
            System.out.print("Enter name of class \t:\t");
            String class_name = sc.nextLine();
            x = x.replace("\n","");
            x =  sep(x);
            x = "import java.util.*;\nimport java.io.*;\n\n\nclass " + class_name + "\n{\npublic static void main(String args[]) throws Exception\n{\n\nScanner sc=new Scanner(System.in);\n\n\n" + x + "\n}\n}";
            try
            {
                Files.write(Paths.get(output), x.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            }
            catch(Exception E)
            {
                System.out.println("Your output file's name should be the second file ");
                System.out.println("\n\nAlso make sure your files are present within the same directory as the .java and .class files of these programs...");
                System.exit(0);
            }

            System.exit(0);

        }


    //--------------------------------------------------------------------------

    public static String repFirst(String whatToReplace, String toBeReplacedIn)
    {
        int len_repwith = whatToReplace.length();
        int len_mainString = toBeReplacedIn.length();
        String tmp, out;
        String b = toBeReplacedIn.trim();
        for(int i = 0; i < len_mainString; i++)
        {
            tmp = b.substring(i, len_repwith + i);
            if (tmp.equals(whatToReplace))
            {
                out = b.substring(0, b.indexOf(tmp));
                out += b.substring(b.indexOf(tmp) + len_repwith);
                return out;
            }
        }
        return toBeReplacedIn;
    }

    //--------------------------------------------------------------------------

    public static String display(String text)throws Exception
    {
        if(text.indexOf("{") != -1)
        {
            text = text.replace(text.substring(text.indexOf("{"),text.indexOf("}") +1 ) , "\" + " + text.substring(text.indexOf("{") + 1 , text.indexOf("}") ) + " + \"" );
        }
      String out = "System.out.println(\"" + text + "\");\n";
      return out;
    }

    //--------------------------------------------------------------------------

    public static String input(String s) throws Exception
    {
        String a1=s.substring(s.indexOf("{")+1,s.indexOf("}")).trim();
        String a2 =s.substring(s.indexOf("}")+1);
        String str="String "+a1+"=sc.nextLine();\n";
        if(s.indexOf("(")!=-1 && s.indexOf(")")!=-1)

        str+="\n switch("+a1+")\n{\n";
        while(s.indexOf(')')!=-1)
        {
            str+="case "+ (s.substring(0,s.indexOf("(")).equals("#")? "default" : (" \""+s.substring(0,s.indexOf("("))+"\"") )+ ": \n" + (s.substring(s.indexOf("(")+1,s.indexOf(")")))+"\n";
            if(s.charAt(s.indexOf(")")+1)=='!'){str+="break";};
            s=s.replace(str,"");
        }
        return str;
    }

    //--------------------------------------------------------------------------

    public static String genFor(String str) throws Exception
    {
        String out = "";

        out += "for(";

        String tmp = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
        String tmp2[] = tmp.split(",");

        for(int i = 0; i < tmp2.length; i++)
        {
            switch(tmp2[i].trim())
            {
                case "i":
                    out += "int ";
                    break;
                case "d":
                    out += "double ";
                    break;
                case "f":
                    out += "float ";
                    break;
                default:
                    tmp2[i].trim();
                    out += tmp2[i].substring(0, tmp2[i].indexOf("(")) + " = " + tmp2[i].substring(tmp2[i].indexOf("(") + 1, tmp2[i].indexOf(")")) + "; ";
            }
         }

         String con = str.substring(str.indexOf("}") + 1, str.indexOf("_"));
         con = con.trim();
         out += con + "; ";
         String upd = str.substring(str.indexOf("_") + 1, str.lastIndexOf("_"));
         String varName = str.substring(str.indexOf(",") + 1, str.indexOf("("));
         out +=  upd + ")\n{\n\t";
         String content = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
         content = check(content) ? sep(content) : content;
         out += content;
         out += "\n}\n\n";
         return out;


    }

    //--------------------------------------------------------------------------

    public static String whi(String inp)throws Exception
    {
        String code = inp.substring(inp.indexOf(")") + 1);
        String cond = inp.substring(inp.indexOf("(")+1,inp.indexOf(")"));
        code = sep(code);
        String out = "while("+cond+")\n{\n\n\t"+code+"\n}\n";
        return out;
    }

    //--------------------------------------------------------------------------

    public static String dowhi(String inpu) throws Exception
    {
        String codes = inpu.substring(0,inpu.indexOf("("));
        String condi = inpu.substring(inpu.indexOf("(")+1,inpu.indexOf(")"));
        codes = sep(codes);
        String outp = "do\n{\n\n\t"+codes+"\n}while("+condi+");\n";
        return outp;
    }

    //--------------------------------------------------------------------------

    public static String else_(String str)
    {
        String elseCode = "";
        elseCode += "else\n{\n\t" + str + "\n}";
        return elseCode;
    }

    //--------------------------------------------------------------------------

    public static String if_(String str)
    {
        String cond = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        String code = str.substring(str.indexOf(",") + 1, str.length());
        String out  = "if(" + cond + ")\n{\n\t" + code + "\n}";
        return out;
    }

    //--------------------------------------------------------------------------

    public static String sep(String x) throws Exception
    {

      String tag = x.substring(x.indexOf('<')+1,x.indexOf(']') );
      int f_checker = x.indexOf("["+tag+">") + 2 +tag.length();
      int end = x.indexOf("["+tag+">");
      String content = x.substring(x.indexOf('<'+ tag + ']') + tag.length()+2, x.indexOf('['+tag+'>') );
      String new1 = ("<"+tag+"]"+content+"["+tag+">");
      x = repFirst(new1, x);
      String out = "";
      String y = process(content,tag);
      x = check(x) ? sep(x) : x;
      out = y + x;
      return out;

    }

    //--------------------------------------------------------------------------

    public static String process(String content,String tag) throws Exception
    {
        String out = "";
        switch( tag.trim().toLowerCase() )
        {
            case "d":
                out = display(content);
                break;
            case "w":
                out = w(content);
                break;
            case "fo":
                out = genFor(content);
                break;
            case "dw":
                out = dowhi(content);
                break;
            case "wh":
                out = whi(content);
                break;
            case "i":
                out = input(content);
                break;
            case "var":
                out = Var.var(content);
                break;
            case "sw":
                out = Switch.sw(content);
                break;
            case "if":
                out = if_(content);
                break;
            case "el":
                out = else_(content);
                break;
        }

        return out;
    }

    //--------------------------------------------------------------------------

    public static boolean check(String x)throws Exception
    {

      boolean b = x.indexOf('<') != -1 && x.lastIndexOf('[') != -1;
      return b;

    }

    //--------------------------------------------------------------------------

    public static String w(String x)throws Exception
    {
        return "Thread.sleep(" + x.trim() + "000"+");\n";
    }

    //--------------------------------------------------------------------------

}


//------------------------------------------------------------------------------

class Switch
{

    static int index = 0;

    public static String sw(String str) throws Exception
    {


        String out = "";
        String case_;
        String var = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
        out += "switch(" + var + ")\n{\n";
        index = str.indexOf("}");
        case_ = "(";
        while(str.indexOf("(", index) != -1)
        {
            case_ = str.substring(index + 1, str.indexOf("(")).trim();
            out += "case \"" + case_ + "\" :\n";
            index = str.indexOf("(") + 1;
            String code = str.substring(index, str.indexOf(")"));
            out += Axolotl.sep(code) + "\n";
            index = str.indexOf(")");

            if(str.charAt(index + 1) == '!')
            {
                out += "\nbreak;";
                index ++;
            }

            out += "\n}y";

            if(str.indexOf("(", index) == -1)
            {
                break;
            }
        }

    return out;

    }
}

//------------------------------------------------------------------------------

class Var
{
    public static String var_list[];
    public static int index = 0;
    public static int tmpInd = 0;
    private static String storeForTheTimeBeing[];


    public static String var(String a)
    {

        String name = "";
        String dtype = "";
        String var_val = "";
        String value = "";
        String out = "";
        boolean declared = false;
        boolean not_initialised = false;
        boolean not_declared = false;

        for( int i = 0 ; i < var_list.length ; i++ )
        {
            declared = var_list[i].equals(name);
            not_declared = !( var_list[i].equals(name) );
        }

        if(not_declared && a.indexOf(',') == -1 && a.indexOf("(") == -1)
        {
            out = "String " + name + " = " + "\"\";" ;

            if(var_list.length == 0)
            {
                var_list= new String[1];
                var_list[0] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list= new String[index];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }
        }
        else if(not_declared && a.indexOf('(') == -1 && a.indexOf(',') != -1)
        {
            dtype = a.substring(0,a.indexOf(","));
            dtype = typedet( dtype );
            name = a.substring(a.indexOf(","));
            out = dtype + " " + name + ";";

            if(var_list.length == 0)
            {
                var_list= new String[1];
                var_list[0] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list= new String[index];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }

        }

        else if(not_declared && a.indexOf(',') == -1)
        {
            out = "String " + name + " = " + value ;

            if(var_list.length == 0)
            {
                var_list= new String[1];
                var_list[0] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list= new String[index];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }

        }

        else if(not_declared)
        {
            String tmp = a;
            String tmp2[] = tmp.split(",");
            dtype = typedet(tmp2[0]);
            name = tmp2[1].substring(0,tmp2[1].indexOf("("));
            value = tmp2[1].replace(name,"").replace("(","").replace(")","");
            out = dtype + " " + name + " = " + (dtype.equalsIgnoreCase("String") ? "\"" + value + "\"" : value);

            if(var_list.length == 0)
            {
                var_list= new String[1];
                var_list[0] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list = new String[index];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = index;
                index++;
                storeForTheTimeBeing= new String[tmpInd];
            }

        }

        if(declared)
        {
            name = a.substring(0,a.indexOf("("));
            value = a.substring(a.indexOf("(") + 1,a.indexOf(")"));
            out = name + " = " + value + ";";
        }

        return out;
    }

    //--------------------------------------------------------------------------

    public static String typedet(String a)
    {

        String out = "";
        switch(a.toLowerCase())
        {
            case "i" :
                out = "int";
                break;

            case "d" :
                out = "double";
                break;

            case "b" :
                out = "boolean";
                break;

            case "s" :
                out = "String";
                break;
        }
        return out;
    }
}

//------------------------------------------------------------------------------
