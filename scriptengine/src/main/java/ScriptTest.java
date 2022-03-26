import javax.script.*;

/**
 * Created by Aaron on 09/08/2017.
 */
public class ScriptTest {
    public static void main(String[] args) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("AppleScriptEngine");
            Compilable compilable = (Compilable) engine;
            Bindings bindings = engine.createBindings(); //Local级别的Binding
            String script = "function add(op1,op2){return op1+op2} add(a$$b, b)"; //定义函数并调用
            CompiledScript JSFunction = compilable.compile(script); //解析编译脚本函数
            bindings.put("a$$b", 1);
            bindings.put("b", 2); //通过Bindings加入参数
            Object result = JSFunction.eval(bindings);
            System.out.println(result); //调用缓存着的脚本函数对象，Bindings作为参数容器传入
        }
        catch (ScriptException e) {
            e.printStackTrace();
        }
    }


}
