import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Aaron on 25/07/2017.
 */
public class GroovyScriptTest {
    @Test
    public void testGroovyScript() throws IOException, ResourceException, ScriptException {
        // @todo refactor this path
        File currentDir = new File("./src/test/groovy/bugs");
        String file = "bug1567_script.groovy";

        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        String[] test = null;
        Object result = shell.run(new File(currentDir, file), test);

        String[] roots = new String[]{currentDir.getAbsolutePath()};
        GroovyScriptEngine gse = new GroovyScriptEngine(roots);
        binding = new Binding();
        // a MME was ensued here stating no 't.start()' was available
        // in the script
        gse.run(file, binding);
    }
}
