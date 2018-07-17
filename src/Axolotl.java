import java.util.*;
import java.nio.file.*;
import java.io.*;

//----------------------------------


/*

    Axolotl. A pseudo-programming languages used to write programs in multiple languages with a really simplified syntax, in order to facilitate easy learning of programming languages to novice programmers.
    License : GNU GPL v3
    Refer to LICENSE.txt inside the folder.

*/


//----------------------------------



class Axolotl
{

        public static void main(String args[]) throws Exception
        {

            String input = "", output = "";
            String x = "";
            String help = "USAGE: axolotl [INPUT FILE] [OUTPUT FILE]";

            switch( args.length )
            {
                case 1:
                    switch( args[0] )
                    {
                        case "--help":
                        case "--h":
                        case "-h":
                        case "h":
                            System.out.println(help);
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
                                System.out.println(help);

                    }
                System.exit(0);
                break;

                default:
                    System.out.println(help);
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
                        System.out.println(help);
                    }
                    break;
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

    public static String sep(String in_data) throws Exception
    {
      StringBuffer input = new StringBuffer(in_data);
    String label = "";
    
    char op1 = '<';
    char op2 = '[';
    char cl1 = ']';
    char cl2 = '>';
    char opl = '(';
    char cll = ')';
    int off = 0;

    String tag = input.substring( input.indexOf("" + op1) + 1 , input.indexOf("" + cl1) );

    String opt = op1 + tag + cl1 ;
    int i_opt = input.indexOf(opt);

    String clt = op2 + tag + cl2 ;
    int i_clt = input.indexOf(clt);

    int t_l = opt.length();

    String content = input.substring( i_opt + t_l , i_clt );

    if( content.charAt(0) == opl )
    {

      label = content.substring( 1 , content.indexOf(cll) );    
      content = input.substring( input.indexOf( opt + label ) + t_l  + label.length() , input.indexOf( label + clt )  );
      
    }
    
    String processed = process( content , tag );
    input.replace( input.indexOf(opt + label) , input.indexOf(label + clt) + t_l + label.length() , processed   );

    
    String output = new String(input);

    output = check(output) ? sep(output) : output;


    return output;


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
                out = var(content);
                break;
            case "sw":
                out = sw(content);
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



//------------------------------------------------------------------------------


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
    public static String var_list[];
    public static int vindex = 0;
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
                tmpInd = vindex;
                vindex++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list= new String[vindex];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = vindex;
                vindex++;
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
                tmpInd = vindex;
                vindex++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list= new String[index];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = vindex;
                vindex++;
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
                tmpInd = vindex;
                vindex++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list= new String[index];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = vindex;
                vindex++;
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
                tmpInd = vindex;
                vindex++;
                storeForTheTimeBeing= new String[tmpInd];
            }
            else
            {
                System.arraycopy(var_list, 0, storeForTheTimeBeing, 0, var_list.length);
                var_list = new String[index];
                System.arraycopy(storeForTheTimeBeing, 0, var_list, 0, storeForTheTimeBeing.length);
                var_list[(var_list.length - 1)] = name;
                tmpInd = vindex;
                vindex++;
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
