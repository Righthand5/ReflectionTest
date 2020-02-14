package ReflectionTest;
import javax.swing.*;
import java.io.FileDescriptor;
import java.sql.SQLOutput;
/**
 * 这个程序可以用来分析java解释器能够加载任何类，而不仅仅是编译这个程序时可以使用的类。
 */
import java.util.*;
import java.lang.reflect.*;
public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        if(args.length>0)
            name=args[0];//args，MAIN函数中args参数，args[]在命令运行的时候输入的参数，因为参数可以多个，所以要用数组来存放
        //比如dos的copy命令：copy c:\*.* d:\*.*，这就是两个参数c:\*.*和d:\*.*。
        //java在命令行运行的时候要用到java命令：
            //java Test value1 value2
            //后面就是两个参数，在main里面args[]就是两个长度的数组value1存在args[0]中，value2存在args[1]中。
            //args[0]存储的是命令，其后才是参数！
        else
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g.java.util.Date):");
            name = in.next();
        }
        try{
            Class c1=Class.forName(name);//返回类名为name的Class对象
            Class superc1=c1.getSuperclass();
            System.out.println("class"+name);
            if(superc1!=null && superc1 !=Object.class)
                System.out.print("extends"+superc1.getName());
            System.out.print("\n{\n");
            printConstructors(c1);
            System.out.println();
            printMethods(c1);
            System.out.println();
            printFields(c1);
            System.out.println("}");
        }
        catch (ClassNotFoundException e){e.printStackTrace();}
        System.exit(0);
    }
    public static void printConstructors(Class c1)
    {
        Constructor[] constructors=c1.getConstructors();
        for(Constructor c:constructors)
        {
            String name=c.getName();
            System.out.print("  "+Modifier.toString(c.getModifiers()));
            System.out.print(" "+name+"(");
            Class[] paramTypes=c.getParameterTypes();
            for (int j = 0; j <paramTypes.length ; j++) {
                if(j>0)System.out.print(",");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }
    public static void printMethods(Class c1)
    {
        Method[] methods=c1.getDeclaredMethods();
        for(Method m:methods)
        {
            Class retType=m.getReturnType();
            String name=m.getName();
            System.out.println(" "+Modifier.toString(m.getModifiers()));
            System.out.println(" "+retType.getName()+" "+name+")");
            Class[] paramTypes = m.getParameterTypes();
            for(int j=0;j<paramTypes.length;j++)
            {
                if(j>0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }
    public  static void printFields(Class c1)
    {
        Field[] fields =c1.getDeclaredFields();
        for(Field f:fields)
        {
            Class type=f.getType();
            String name=f.getName();
            System.out.print("  "+Modifier.toString(f.getModifiers()));
            System.out.println(" "+type.getName()+" "+name+",");

        }
    }
}
