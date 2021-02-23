package com.logistics.plan.factory;

import com.logistics.plan.domain.entity.Mail;
import com.logistics.plan.service.MailService;
import com.logistics.plan.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MailImport implements Import {

    @Autowired
    private MailService mailService;

    @Override
    public void importCsv(List<String> list, Map<String, Integer> titleIndexMap) throws Exception {
        //循环数据
        Class mailClass = Mail.class;
        List<Mail> mailList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            Mail mail = new Mail();
            ImportUtil.setValue(mail, mailClass, list.get(i), titleIndexMap);
            mailList.add(mail);
        }
        if(!ComUtil.isEmpty(mailList)){
            mailService.saveBatch(mailList);
        }
    }


}
