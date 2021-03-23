package com.gyf.daily.practice.service;

import com.alibaba.fastjson.JSONArray;
import com.gyf.daily.practice.dos.BeanExample;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

import java.util.List;

/**
 * @author Gyf
 * @date 2021/3/22
 */
public class QLService {
    @Test
    public void ql() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a",1);
        context.put("b",2);
        context.put("c",3);
        String express = "a+b*c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }

    @Test
    public void method() throws Exception {
        ExpressRunner runner = new ExpressRunner();

        runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs",
                new String[] { "double" }, null);
        runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(),
                "upper", new String[] { "String" }, null);

        runner.addFunctionOfServiceMethod("打印", System.out, "println",new String[] { "String" }, null);
        runner.addFunctionOfServiceMethod("包含", new BeanExample(), "contains",new Class[] { String.class, String.class }, null);
        runner.addFunctionOfServiceMethod("contains", new BeanExample(), "anyContains",
                new Class[] { String.class, String.class }, null);
//        String express = "转换为大写(\"hello world\")";
        String express = "包含(\"1,2,6,4\",\"1,2,3,4,5\")";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        context.put("double",9);
        Object execute = runner.execute(express, context, null, false, false);
        System.out.println(execute);

    }
    
    
    @Test
    public void list(){
        String s = "[1,3,33,45,6,6]";
        List<String> strings1 = JSONArray.parseArray(s, String.class);
        System.out.println(strings1);

    }

}
