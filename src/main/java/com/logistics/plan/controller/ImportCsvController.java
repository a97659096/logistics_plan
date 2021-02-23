package com.logistics.plan.controller;


import com.logistics.plan.factory.Import;
import com.logistics.plan.factory.MailImport;
import com.logistics.plan.factory.NodeImport;
import com.logistics.plan.factory.VehicleImport;
import com.logistics.plan.utils.*;
import com.logistics.plan.utils.spring.SpringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("import")
public class ImportCsvController {

    @PostMapping
    public R importCsv(@RequestParam MultipartFile file,
                       @RequestParam String type) throws Exception {
        List<String> list = CSVUtils.importCsv(FileUtil.multipartFileToFile(file));
        String[] title = list.get(0).split(",");
        //获取标题索引map
        Map<String, Integer> titleIndexMap = new HashMap<>();
        for (int i = 0; i < title.length; i++) {
            titleIndexMap.put(title[i], i);
        }
        Import aImport;
        switch (type){
             case "mail":
                 aImport = SpringUtils.getBean(MailImport.class);
                 break;
            case "node":
                aImport = SpringUtils.getBean(NodeImport.class);
                break;
            case "vehicle":
                aImport = SpringUtils.getBean(VehicleImport.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        aImport.importCsv(list, titleIndexMap);
        return R.ok();
    }


}
