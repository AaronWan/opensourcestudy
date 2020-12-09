package com.aita.oop.workflow.handler;

/**
 * Created by Aita on 17/8/27.
 */
public class AnClassTest {
    public static void main(String[] args) {
        ValidateHandler a=new ValidateHandler() {
            @Override
            public boolean validate(Workflow workflow) {
                return false;
            }
        };

        A.B b= new A.B();
        new A();
        AnClassTest t = new AnClassTest();

        C kk = t.new C();
        ValidateHandler v=(workflow)-> false;

        validate((workflow) -> true);
    }

    public static void validate(ValidateHandler validateHandler){

    }

    public class C{

    }

    public static class A{

        public static class  B{

        }
    }

}
