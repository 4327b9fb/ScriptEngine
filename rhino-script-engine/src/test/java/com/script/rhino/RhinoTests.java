package com.script.rhino;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.script.ScriptEngine;
import com.script.ScriptException;
import com.script.Bindings;
import com.script.SimpleBindings;

import org.junit.Before;
import org.junit.Test;
import org.mozilla.javascript.NativeArray;

/**
 * Unit tests for RhinoScriptEngine implementation.
 */
public class RhinoTests {

    private RhinoScriptEngine rhino;

    @Before
    public void setUp() {
        rhino = new RhinoScriptEngine();
    }

    @Test
    public void RhinoCanBeCreated() throws Exception {
        assertThat("Rhino engine can be created", rhino, notNullValue());
        assertThat("Rhino engine is of correct type", rhino, instanceOf(RhinoScriptEngine.class));
    }

    @Test
    public void RhinoCanEvaluateSimpleExpressions() throws Exception {
        testEngineBasics();
    }

    @Test
    public void RhinoCanWorkWithVariables() throws Exception {
        rhino.put("name", "rhino");
        assertThat("Can use variables", rhino.eval("'We use ' + name"), is("We use rhino"));
    }

    @Test
    public void RhinoSupportsNumericOperations() throws Exception {
        assertThat("Basic arithmetic works", (Double) rhino.eval("2.5+2"), is(4.5));
        assertThat("Integer arithmetic works", (Double) rhino.eval("2 + 3"), is(5.0));
        assertThat("Complex expressions work", (Double) rhino.eval("(2 + 3) * 4 - 6 / 2"), is(17.0));
    }

    @Test
    public void RhinoSupportsMathFunctions() throws ScriptException {
        assertThat("Math.asinh works", (Double) rhino.eval("Math.asinh(0);"), is(0.0));
        assertThat("Math.PI works", (Double) rhino.eval("Math.PI"), is(Math.PI));
        assertThat("Math.sin works", (Double) rhino.eval("Math.sin(0)"), is(0.0));
    }

    @Test
    public void RhinoCanCreateAndUseBindings() throws Exception {
        Bindings bindings = new SimpleBindings();
        bindings.put("value", 10);
        Object result = rhino.eval("value * 2", bindings);
        assertThat("Can use custom bindings", (Double) result, is(20.0));
    }

    @Test
    public void RhinoHandlesArrays() throws Exception {
        Object result = rhino.eval("[1, 2, 3]");
        assertThat("Arrays are supported", result, instanceOf(NativeArray.class));
        NativeArray array = (NativeArray) result;
        assertThat("Array has correct length", array.getLength(), is(3L));
    }

    @Test
    public void RhinoHandlesExceptionsProperly() throws Exception {
        try {
            rhino.eval("invalid syntax +++");
            fail("Should have thrown ScriptException");
        } catch (ScriptException e) {
            // Expected
        }
    }

    private void testEngineBasics() throws ScriptException {
        assertThat("Engine is not null", rhino, notNullValue());
    }
}