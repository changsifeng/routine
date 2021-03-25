package com.gyf.daily.practice.service;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.gyf.daily.practice.dos.A;
import com.gyf.daily.practice.dos.B;
import com.gyf.daily.practice.dos.BeanExample;
import com.gyf.daily.practice.dos.C;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Gyf
 * @date 2021/3/22
 */
@Slf4j
public class QLService {
    @Test
    public void ql() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a+b*c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }

    @Test
    public void method() throws Exception {
        ExpressRunner runner = new ExpressRunner();

        runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs",
                new String[]{"double"}, null);
        runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(),
                "upper", new String[]{"String"}, null);

        runner.addFunctionOfServiceMethod("打印", System.out, "println", new String[]{"String"}, null);
        runner.addFunctionOfServiceMethod("包含", new BeanExample(), "contains", new Class[]{String.class, String.class}, null);
        runner.addFunctionOfServiceMethod("contains", new BeanExample(), "anyContains",
                new Class[]{String.class, String.class}, null);
//        String express = "转换为大写(\"hello world\")";
        String express = "包含(\"1,2,6,4\",\"1,2,3,4,5\")";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        context.put("double",9);
        Object execute = runner.execute(express, context, null, false, false);
        System.out.println(execute);

    }


    @Test
    public void init() throws InterruptedException {
        A a = new A();
        B b = new B();
        C c = new C();
        c.setAddress("hangzhou");

        b.setAge("25");
        b.setC(c);

        a.setName("gu");
        a.setB(b);
        a.setResult(false);
        for (int i = 0; i < 10; i++) {
            long l = System.currentTimeMillis();
            JSON parse = JSONUtil.parse(a);
            Object byPath = JSONUtil.getByPath(parse, "b.c.address");
            String address = a.getB().getC().getAddress();
            long l1 = System.currentTimeMillis() - l;
            System.out.println(l1);
            System.out.println(address);
        }


    }


}
