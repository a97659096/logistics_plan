package com.logistics.plan.factory;

import com.logistics.plan.domain.entity.Node;
import com.logistics.plan.domain.entity.Vehicle;
import com.logistics.plan.service.VehicleService;
import com.logistics.plan.utils.ComUtil;
import com.logistics.plan.utils.ImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VehicleImport implements Import {

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void importCsv(List<String> list, Map<String, Integer> titleIndexMap) {
        //循环数据
        Class vehicleClass = Vehicle.class;
        List<Vehicle> vehicleList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            Vehicle vehicle = new Vehicle();
            ImportUtil.setValue(vehicle, vehicleClass, list.get(i), titleIndexMap);
            vehicleList.add(vehicle);
        }
        if(!ComUtil.isEmpty(vehicleList)){
            vehicleService.saveBatch(vehicleList);
        }

    }
}
