package com.logistics.plan.utils;

import java.lang.reflect.Field;
import java.util.Map;

public class ImportUtil {

    public static void setValue(Object object, Class aClass, String data, Map<String, Integer> titleIndexMap){
        String[] split = data.split(",");
        Field[] fileds = aClass.getDeclaredFields();
        for (Field filed : fileds) {
            String filedName = filed.getName();
//            if(filedName.equals("serialVersionUID")) continue;
            String filedNameToUnderLine = StringUtils.humpToUnderline(filedName);
            //获取csv中的值
            if(ComUtil.isEmpty(titleIndexMap.get(filedNameToUnderLine))) continue;
            String value = split[titleIndexMap.get(filedNameToUnderLine)];

            //调用set方法赋值
            if (!ComUtil.isEmpty(value)) {
                ReflectUtil.setValue(object, aClass, filedName, filed.getType(), value);
            }
        }
    }

}
