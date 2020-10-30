```java
javap -verbose TestASM.class
Classfile /Users/Aaron/Documents/code/study/opensourcestudy/jdktest/src/main/java/com/study/asm/TestASM.class
  Last modified 2020-10-2; size 950 bytes
  MD5 checksum fdfdfc5add936ca6f9127aa919aa5c64
  Compiled from "TestASM.java"
public class com.study.asm.TestASM
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #9.#33         // java/lang/Object."<init>":()V
   #2 = Fieldref           #6.#34         // com/study/asm/TestASM.num1:I
   #3 = Methodref          #6.#35         // com/study/asm/TestASM.add:(II)I
   #4 = Fieldref           #6.#36         // com/study/asm/TestASM.NUM1:I
   #5 = Fieldref           #37.#38        // java/lang/System.out:Ljava/io/PrintStream;
   #6 = Class              #39            // com/study/asm/TestASM
   #7 = Methodref          #6.#33         // com/study/asm/TestASM."<init>":()V
   #8 = Methodref          #40.#41        // java/io/PrintStream.println:(I)V
   #9 = Class              #42            // java/lang/Object
  #10 = Utf8               num1
  #11 = Utf8               I
  #12 = Utf8               NUM1
  #13 = Utf8               <init>
  #14 = Utf8               ()V
  #15 = Utf8               Code
  #16 = Utf8               LineNumberTable
  #17 = Utf8               LocalVariableTable
  #18 = Utf8               this
  #19 = Utf8               Lcom/study/asm/TestASM;
  #20 = Utf8               func
  #21 = Utf8               (II)I
  #22 = Utf8               a
  #23 = Utf8               b
  #24 = Utf8               add
  #25 = Utf8               sub
  #26 = Utf8               main
  #27 = Utf8               ([Ljava/lang/String;)V
  #28 = Utf8               args
  #29 = Utf8               [Ljava/lang/String;
  #30 = Utf8               <clinit>
  #31 = Utf8               SourceFile
  #32 = Utf8               TestASM.java
  #33 = NameAndType        #13:#14        // "<init>":()V
  #34 = NameAndType        #10:#11        // num1:I
  #35 = NameAndType        #24:#21        // add:(II)I
  #36 = NameAndType        #12:#11        // NUM1:I
  #37 = Class              #43            // java/lang/System
  #38 = NameAndType        #44:#45        // out:Ljava/io/PrintStream;
  #39 = Utf8               com/study/asm/TestASM
  #40 = Class              #46            // java/io/PrintStream
  #41 = NameAndType        #47:#48        // println:(I)V
  #42 = Utf8               java/lang/Object
  #43 = Utf8               java/lang/System
  #44 = Utf8               out
  #45 = Utf8               Ljava/io/PrintStream;
  #46 = Utf8               java/io/PrintStream
  #47 = Utf8               println
  #48 = Utf8               (I)V
{
  public static int NUM1;
    descriptor: I
    flags: ACC_PUBLIC, ACC_STATIC

  public com.study.asm.TestASM();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iconst_1
         6: putfield      #2                  // Field num1:I
         9: return
      LineNumberTable:
        line 6: 0
        line 7: 4
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      10     0  this   Lcom/study/asm/TestASM;

  public int func(int, int);
    descriptor: (II)I
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=3, args_size=3
         0: aload_0
         1: iload_1
         2: iload_2
         3: invokevirtual #3                  // Method add:(II)I
         6: ireturn
      LineNumberTable:
        line 10: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  this   Lcom/study/asm/TestASM;
            0       7     1     a   I
            0       7     2     b   I

  public int add(int, int);
    descriptor: (II)I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=3
         0: iload_1
         1: iload_2
         2: iadd
         3: aload_0
         4: getfield      #2                  // Field num1:I
         7: iadd
         8: ireturn
      LineNumberTable:
        line 13: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcom/study/asm/TestASM;
            0       9     1     a   I
            0       9     2     b   I

  public int sub(int, int);
    descriptor: (II)I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=3
         0: iload_1
         1: iload_2
         2: isub
         3: getstatic     #4                  // Field NUM1:I
         6: isub
         7: ireturn
      LineNumberTable:
        line 16: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       8     0  this   Lcom/study/asm/TestASM;
            0       8     1     a   I
            0       8     2     b   I

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=1, args_size=1
         0: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: new           #6                  // class com/study/asm/TestASM
         6: dup
         7: invokespecial #7                  // Method "<init>":()V
        10: bipush        20
        12: bipush        30
        14: invokevirtual #3                  // Method add:(II)I
        17: invokevirtual #8                  // Method java/io/PrintStream.println:(I)V
        20: return
      LineNumberTable:
        line 20: 0
        line 21: 20
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      21     0  args   [Ljava/lang/String;

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=1, locals=0, args_size=0
         0: bipush        100
         2: putstatic     #4                  // Field NUM1:I
         5: return
      LineNumberTable:
        line 8: 0
}
SourceFile: "TestASM.java"

``` 
