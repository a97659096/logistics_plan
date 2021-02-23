package com.logistics.plan.factory;


import java.util.List;
import java.util.Map;

public interface Import {

    void importCsv(List<String> list, Map<String, Integer> titleIndexMap) throws Exception;

}
