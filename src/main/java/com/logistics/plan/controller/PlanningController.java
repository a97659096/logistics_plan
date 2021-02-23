package com.logistics.plan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.logistics.plan.domain.entity.PlanningParameters;
import com.logistics.plan.redis.RedisService;
import com.logistics.plan.service.IRealLineService;
import com.logistics.plan.service.NetworkRouteWayService;
import com.logistics.plan.service.PlaningService;
import com.logistics.plan.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/planning")
public class PlanningController {

//    @Autowired
//    private IRealLineService realLineService;
//
//    @Autowired
//    private NetworkRouteWayService networkRouteWayService;
//
//    @Autowired
//    private PlaningService planingService;
//
//    @Autowired
//    private RedisService redisService;
//
//    private static String PATH = "cityplan/UrbanRoutePlanning2021/in_data/";
////    private static String PATH = "wangluo_guihua/test/in_data/";
//
//    private static String MAIN_PATH = "cityplan/UrbanRoutePlanning2021/main.pyc";
////    private static String MAIN_PATH = "wangluo_guihua/test/main.pyc";
//
//    private static String OUTPUT_PATH = "cityplan/UrbanRoutePlanning2021/out_data/市趟计划表_进1.csv";
////    private static String OUTPUT_PATH = "wangluo_guihua/test/out_data/市趟计划表_进1.csv";
//
//    private static String PARAM_PATH = "cityplan/UrbanRoutePlanning2021/";
////    private static String PARAM_PATH = "wangluo_guihua/test/";
//
//    private static String LINE_PATH = "cityplan/UrbanRoutePlanning2021/out_data/";
////    private static String LINE_PATH = "wangluo_guihua/test/out_data/";
//
//    private static String RESULT_PATH = "cityplan/UrbanRoutePlanning2021/out_data/求解结果_进1.txt";
////    private static String RESULT_PATH = "wangluo_guihua/test/out_data/求解结果_进1.txt";
//
//
//
//    @GetMapping("test-lock")
//    public String testLock(){
//        redisService.tryLock("file-lock", "lock",60);
//        return "success";
//    }
//
//    /**
//     * 检查是否完成，lock为空时表示已经处理完成
//     * @return
//     */
//    @GetMapping("check-complete")
//    public JSONObject readLock(){
//        String lock = redisService.get("file-lock");
//        BigDecimal schedule = planingService.getSchedule();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("data", ComUtil.isEmpty(lock));
//        //进度
//        jsonObject.put("schedule", schedule);
//        return jsonObject;
//    }
//
//    /**
//     * 保存输入文件
//     *
//     * @param file 文件
//     * @return 结果
//     */
//    @RequestMapping("/updateFile")
//    @ResponseBody
//    public JSONObject updateFile(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
//
//        String lock = redisService.get("file-lock");
//        JSONObject jsonObject = new JSONObject();
//        if(!ComUtil.isEmpty(lock)){
//            jsonObject.put("status", "error");
//            jsonObject.put("msg", "导入任务正在计算中，请稍后再试");
//            return jsonObject;
//        }
//
//        //删除原有文件信息
////        deleteFile();
//
//        try {
//            String path = FileAddressConstants.getCityPlanParentPath() + PATH;
////            String path = "C:\\Users\\IronMan\\Desktop\\中邮仿真\\网络规划生成模板\\";
//            if (!file.isEmpty()) {
//                FileUploadUtils.uploadText(path, file);
//                //node表时区生成 route.csv和dist.csv
//                if (type.equals("node")){
//                    planingService.routeAndDistCreate(path);
//                }
//            }
//            jsonObject.put("status", "success");
//            return jsonObject;
//        } catch (Exception e) {
//            jsonObject.put("status", "error");
//            jsonObject.put("msg", e.getMessage());
//            return jsonObject;
//        }
//    }
//
//    @RequestMapping("/calculate")
//    @ResponseBody
//    public Map<String,String> calculate(PlanningParameters parameters) throws IOException {
//        String path = FileAddressConstants.getCityPlanParentPath() + PARAM_PATH;
//        List<String> list = new ArrayList<>();
//        list.add("# 参数设置");
//        list.add("# part1-业务要求相关参数");
//        list.add("# 装载邮件种类(1-标快|快包,2-标快,3-快包)");
//        list.add("load_mail_type = '" + parameters.getLoad_mail_type()+"'");
//        list.add("# 处理中心出发时间");
//        list.add("center_tw1 = '" + parameters.getCenter_tw1()+"'");
//        list.add("#投递部最早访问时间");
//        list.add("node_tw1 = '" + parameters.getNode_tw1()+"'");
//        list.add("# 最晚从投递部出发的时间（最晚卸车完毕时间）");
//        list.add("node_tw2 = '" + parameters.getNode_tw2()+"'");
//        list.add("# 燃油价格(分钱/毫升）");
//        list.add("fuel_price = " + new BigDecimal(parameters.getFuel_price()).divide(BigDecimal.valueOf(10)).setScale(2, RoundingMode.HALF_UP));
//        list.add("# 每辆车最多串行投递部的数量");
//        list.add("max_customer_num = " + parameters.getMax_customer_num());
//        list.add("# 是否要求车辆返回处理中心: 1-返回, 0-可不返回");
//        list.add("is_return = " + parameters.getIs_return());
//        list.add("# 单个邮件卸车时长(秒)");
//        list.add("time_per_mail = " + parameters.getTime_per_mail());
//        list.add("# 允许车辆在投递部的最大停留时长(秒)");
//        list.add("# 添加时间窗约束时会用到这个参数");
//        list.add("allow_waiting_time = " + new BigDecimal(parameters.getAllow_waiting_time()).multiply(BigDecimal.valueOf(60)));
//        list.add("# 车辆最大工作时长(秒)");
//        list.add("total_working_time = " + new BigDecimal(parameters.getTotal_working_time()).multiply(BigDecimal.valueOf(60)));
//        list.add("# part2-算法相关参数");
//        list.add("# 指定改进速度");
//        list.add("_decrease_rate = " + new BigDecimal(parameters.getDecrease_rate()).divide(BigDecimal.valueOf(100)).setScale(3, RoundingMode.HALF_UP));
//        list.add("# 连续counter_limiter次解的改进小于指定的改进速度(decrease_rate)");
//        list.add("_counter_limiter = " + parameters.getCounter_limiter());
//        list.add("# 最大求解时长");
//        list.add("_time_limit_seconds = " + parameters.getTime_limit_seconds());
//        FileUploadUtils.writePyFile(path + "parameters.py", list);
//        Map<String,String> map = new HashMap<>();
//        try {
////            String[] arguments = new String[] {"python3 ", FileAddressConstants.getCityPlanParentPath() + MAIN_PATH};
//            System.out.println(FileAddressConstants.getCityPlanParentPath() + MAIN_PATH);
//            Process process = Runtime.getRuntime().exec("python3 " + FileAddressConstants.getCityPlanParentPath() + MAIN_PATH);
//            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),
//                    "UTF-8"));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//                if(line.indexOf("10.")==0){
//                    if(line.indexOf("10.3")==0 ){
//                        map.put("message","算法调用成功，结果已生成，两秒后跳转展示页面");
//                        map.put("static","success");
//                        return map;
//                    }else{
//                        map.put("message","算法调用成功，未生成结果，算法返回："+line);
//                        map.put("static","error");
//                        return map;
//                    }
//                }
//            }
//            in.close();
//            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
//            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
//            int re = process.waitFor();
//            if(re == 1){
//                map.put("message","算法计算失败");
//                map.put("static","error");
//                return map;
//            }
//            map.put("message", "算法调用成功，结果已生成");
//            map.put("static","success");
//        } catch (Exception e) {
//            map.put("message","算法调用失败");
//            map.put("static","error");
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//    /**
//     * 导入前删除历史输入文件
//     * @return
//     */
////    @RequestMapping("/deleteFile")
////    @ResponseBody
//    private void deleteFile() {
//        try {
//            String path = FileAddressConstants.getCityPlanParentPath() + PATH;
////            String path = "C:\\Users\\IronMan\\Desktop\\中邮仿真\\网络规划生成模板\\";
//            File files  = new File(path);
//            FileUploadUtils.deleteFiles(files);
////            return "success";
//        }catch (Exception e){
////            return "error";
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 访问轨迹文件
//     * @return
//     */
//    @RequestMapping("/findLine")
//    @ResponseBody
//    public Object findLine(@RequestParam("fileName") String fileName) {
//        Map<String,Object> map = new HashMap<>();
//        try {
//            String path = FileAddressConstants.getCityPlanParentPath() + LINE_PATH + fileName;
//            List<String> list = FileUploadUtils.readTxtFile(path,"UTF-8");
//            String json = "";
//            for (String str:list) {
//                json+=str;
//            }
//            map = JSON.parseObject(json,Map.class);
//        }catch (Exception e){
//        }
//        return map;
//    }
//
//
//    /**
//     * 将计算结果展示到页面上
//     * @param isLine 是否轨迹 0 路线  1 轨迹
//     * @return
//     */
//    @RequestMapping("/showResult")
//    @ResponseBody
//    public Object showResult(@RequestParam("isLine") String isLine){
//        Map<String,Object> result = new HashMap<>();
//        try {
//            //页面上表格
//            List<Map<String,Object>> tableList = new ArrayList<>();
//            File file = new File(FileAddressConstants.getCityPlanParentPath() + OUTPUT_PATH);
//            if(!file.exists()){
//                return "";
//            }
//            //页面上显示的点和线
//            List<Map<String,Object>> routingMapList = new ArrayList<>();
//            List<Map<String,Object>> nodeMapList =getLat();
//            result.put("node",nodeMapList);
//            //点的经纬度
//            Map<String,Double[]> latMap = new HashMap<>();
//            for (Map<String,Object> tempMap:nodeMapList) {
//                latMap.put(tempMap.get("code").toString(),(Double[])tempMap.get("value"));
//            }
//            //读取cityplan
//            List<String> dataList= CSVUtils.importCsv(file);
//            String[] strs = null;
//            String[] titles = dataList.get(0).split(",");
//            Map<String,Integer> columnIndex = new HashMap<>();
//            boolean temp = true;
//            int index = 0;
//            for (int i = 0; i < titles.length; i++) {
//                columnIndex.put(titles[i],index);
//                if(titles[i].indexOf("2-标快")<0 && titles[i].indexOf("3-快包")<0){
//                    index++;
//                }
//            }
//            Map<String,Object> map = null;
//            Map<String,Object> detailMap = new HashMap<>();
//            detailMap.put("inOut","进口");
//            detailMap.put("emailNum",0);
//            detailMap.put("sumDis",0.0);
//            detailMap.put("target","最低成本");
//            List<String> list = FileUploadUtils.readTxtFile(FileAddressConstants.getCityPlanParentPath() + PARAM_PATH + "parameters.py","GBK");
//            for (String str:list) {
//                String[] sts = str.split("=");
//                if(sts[0].startsWith("time_per_mail")){
//                    detailMap.put("time_per_mail",sts[1]);
//                }else if(sts[0].startsWith("allow_waiting_time")){
//                    detailMap.put("allow_waiting_time",sts[1]);
//                }else if(sts[0].startsWith("total_working_time")){
//                    detailMap.put("total_working_time",sts[1]);
//                }
//            }
//            list = FileUploadUtilscarNum.readTxtFile(FileAddressConstants.getCityPlanParentPath() + RESULT_PATH,"GBK");
//            for (String str:list) {
//                String[] sts = str.split(":");
//                if(sts[0].equals("Total fuel_cost of all routes")){
//                    Double d = Double.parseDouble(sts[1].trim().replace("L",""));
//                    d = (int)((d*5.13)*100)/100.0;
//                    detailMap.put("cost",d);
//                    break;
//                }
//            }
//            Map<String,Object> carNumMap = new HashMap<>();
//            for (int i = 1; i < dataList.size(); i++) {
//                strs = dataList.get(i).split(",");
//                if(strs.length==0){
//                    continue;
//                }
//                map = new HashMap<>();
//                map.put("id",i);
//                map.put("carCode",strs[columnIndex.get("车牌号")]);
//                carNumMap.put(strs[columnIndex.get("车牌号")],"");
//                map.put("ACode",strs[columnIndex.get("A点代码")]);
//                map.put("AName",strs[columnIndex.get("A点名称")]);
//                map.put("BCode",strs[columnIndex.get("B点代码")]);
//                map.put("BName",strs[columnIndex.get("B点名称")]);
//                map.put("AStart",strs[columnIndex.get("A点出发时间")]);
//                map.put("ABDis",strs[columnIndex.get("A到B的距离(千米)")]);
//                Double sumDis = Double.parseDouble(detailMap.get("sumDis").toString())+Double.parseDouble(map.get("ABDis").toString());
//                detailMap.put("sumDis",sumDis);
//                map.put("loadingNum",strs[columnIndex.get("装载量(件数)")]);
//                Integer emailNum = Integer.parseInt(detailMap.get("emailNum").toString())+Integer.parseInt(map.get("loadingNum").toString());
//                detailMap.put("emailNum",emailNum);
//                map.put("loadingSpecies",strs[columnIndex.get("装载邮件种类")]);
//                if(map.get("loadingSpecies").toString().equals("1")){
//                    detailMap.put("loadingSpecies","标快|快包");
//                    map.put("loadingSpecies","标快|快包");
//                }else if(map.get("loadingSpecies").toString().equals("2")){
//                    detailMap.put("loadingSpecies","标快");
//                    map.put("loadingSpecies","标快");
//                }else if(map.get("loadingSpecies").toString().equals("2")){
//                    detailMap.put("loadingSpecies","快包");
//                    map.put("loadingSpecies","快包");
//                }
//                map.put("nextCode",strs[columnIndex.get("下一站代码")]);
//                map.put("nextName",strs[columnIndex.get("下一站名称")]);
//                map.put("BStop",strs[columnIndex.get("B的到达时间")]);
//                map.put("drivingLength",strs[columnIndex.get("驾车时长(分钟)")]);
//                map.put("carHour",strs[columnIndex.get("车辆速度(km/h)")]);
//
//                Map<String,Object> routingMap = new HashMap<>();
//                routingMap.put("id",i);
//                routingMap.put("toName",map.get("AName"));
//                routingMap.put("formName",map.get("BName"));
//                routingMap.put("toCode",map.get("ACode"));
//                routingMap.put("formCode",map.get("BCode"));
//                routingMap.put("name",map.get("AName")+"-"+map.get("BName"));
//                Double[][] lat = new Double[2][2];
//                Double[] aLat = latMap.get(map.get("ACode"));
//                Double[] bLat = latMap.get(map.get("BCode"));
//                lat[0][0] = aLat[0];
//                lat[0][1] = aLat[1];
//                lat[1][0] = bLat[0];
//                lat[1][1] = bLat[1];
//                routingMap.put("coords",lat);
//                routingMapList.add(routingMap);
//                tableList.add(map);
//            }
//            detailMap.put("carNum",carNumMap.size());
//            Double dis = Double.parseDouble(detailMap.get("sumDis").toString());
//            dis = dis/1000.0;
//            dis = ((int)(dis*100))/100.0;
//            detailMap.put("sumDis",dis);
//            //页面上地图显示（路线或者轨迹）
//            if(isLine.equals("0")){
//                result.put("line",routingMapList);
//            }else if(isLine.equals("1")){
//                File lineFile = new File(FileAddressConstants.getCityPlanParentPath() + LINE_PATH + "line.json");
//                if(lineFile.exists()){
//                    lineFile.delete();
//                }
//                generateTrajectory(routingMapList);
//                result.put("lineFileName","line.json");
//            }
//            result.put("detail",detailMap);
//            result.put("table",tableList);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 获取总距离
//     * @return
//     */
////    private Double getSumDis()throws Exception{
////        String fileName = FileUploadUtils.readTxtFile(FileAddressConstants.getCityPlanParentPath() + "cityplan/UrbanRoutePlanning2021/in_data/dist##.txt","GBK").get(0);
////        File file = new File(FileAddressConstants.getCityPlanParentPath() + PATH+fileName);
////        List<String> dataList= CSVUtils.importCsv(file);
////        String[] titles = dataList.get(0).split(",");
////        Map<String,Integer> columnIndex = new HashMap<>();
////        for (int i = 0; i < titles.length; i++) {
////            columnIndex.put(titles[i],i);
////        }
////        Double d = 0.0;
////        for (int i = 1; i < dataList.size(); i++) {
////            String[] sts = dataList.get(i).split(",");
////            if(sts.length==0){
////                continue;
////            }
////            d+=Double.parseDouble(sts[columnIndex.get("driving_dist")]);
////        }
////        d = ((int)(d*1000))/1000.0;
////        return d;
////    }
//
//    /**
//     * zip打包下载方案
//     */
//    @RequestMapping(value = "/downloadFile")
//    @ResponseBody
//    public void downloadFile(HttpServletResponse response) throws Exception{
//        String path = FileAddressConstants.getCityPlanParentPath() + OUTPUT_PATH;
//        FileUploadUtils.download(path,response);
//    }
//
//    /**
//     * 生成轨迹
//     * @param routingMapList
//     * @return
//     */
//    public boolean generateTrajectory(List<Map<String,Object>> routingMapList)throws Exception{
//        List<Map<String,Object>> lineJson = new ArrayList<Map<String,Object>>();
//        List<RealLine> realLines = realLineService.findByCode();
//        List<RealLine> realList = new ArrayList<>();
//        Map<String,Object> reMap = new HashMap<>();
//        Map<String,Object> hupMap = new HashMap<>();
//        for (RealLine re:realLines) {
//            reMap.put(re.getPostingCityCode()+","+re.getReachCityCode(),re.getLineData());
//        }
//        int i = 0;
//        for (Map<String,Object> routingMap:routingMapList) {
//            if (routingMap.get("toName").equals(routingMap.get("formName"))) {
//                continue;
//            }
//            Double[][] lat = ( Double[][])routingMap.get("coords");
//            if(reMap.get(routingMap.get("toCode").toString()+","+routingMap.get("formCode"))!=null){
//                List<Double[]> jwd = JSON.parseObject(reMap.get(routingMap.get("toCode").toString()+","+routingMap.get("formCode")).toString(),List.class);
//                Map<String,Object> linemap = new HashMap<String,Object>();
//                linemap.put("srcCode",routingMap.get("toCode"));
//                linemap.put("id",routingMap.get("id"));
//                linemap.put("dstCode",routingMap.get("formCode"));
//                linemap.put("toName",routingMap.get("toName"));
//                linemap.put("formName",routingMap.get("formName"));
//                linemap.put("name",routingMap.get("toName").toString()+"-"+routingMap.get("formName").toString());
//                jwd.add(0,lat[0]);
//                jwd.add(lat[1]);
//                linemap.put("coords",jwd);
//                lineJson.add(linemap);
//                continue;
//            }
//            String posting = lat[0][0].toString()+","+lat[0][1].toString();
//            String reach = lat[1][0].toString()+","+lat[1][1].toString();
//            if(posting.length()>0 && reach.length()>0){
//                //查轨迹
//                double[][] jwd = Utils.trajectoryProduction(posting,reach,"市趟");
//
//                double[][] lines = new double[jwd.length][2];
//                for (int j = 0; j < jwd.length; j++) {
//                    lines[j][0] = jwd[j][0];
//                    lines[j][1] = jwd[j][1];
//                }
//                Map<String,Object> linemap = new HashMap<String,Object>();
//                linemap.put("srcCode",routingMap.get("toCode"));
//                linemap.put("dstCode",routingMap.get("formCode"));
//                linemap.put("toName",routingMap.get("toName"));
//                linemap.put("id",routingMap.get("id"));
//                linemap.put("formName",routingMap.get("formName"));
//                linemap.put("name",routingMap.get("toName").toString()+"-"+routingMap.get("formName").toString());
//                linemap.put("coords",lines);
//                lineJson.add(linemap);
//                RealLine re = new RealLine();
//                re.setPostingCity(routingMap.get("toName").toString());
//                re.setReachCity(routingMap.get("formName").toString());
//                re.setPostingCityCode(routingMap.get("toCode").toString());
//                re.setReachCityCode(routingMap.get("formCode").toString());
//                re.setLineData(JSON.toJSONString(lines));
//                realList.add(re);
//            }
//        }
//        Map<String,Object> resultMap = new HashMap<>();
//        resultMap.put("line",lineJson);
//        String path = FileAddressConstants.getCityPlanParentPath() + LINE_PATH + "line.json";
//        FileUploadUtils.FileWriteList1(path,resultMap);
//        if(realList.size()>0){
//            realLineService.doCreateList(realList);
//        }
//        return true;
//    }
//
//    private List<Map<String,Object>> getLat() throws  Exception{
////        String fileName = FileUploadUtils.readTxtFile(FileAddressConstants.getCityPlanParentPath() + "cityplan/UrbanRoutePlanning2021/__pycache__/in_data/node##.txt","GBK").get(0);
//        File file = new File(FileAddressConstants.getCityPlanParentPath() + PATH+"node_进1.csv");
//        List<String> dataList= CSVUtils.importCsv(file);
//        String[] titles = dataList.get(0).split(",");
//        Map<String,Integer> columnIndex = new HashMap<>();
//        boolean temp = true;
//        for (int i = 0; i < titles.length; i++) {
//            if(temp){
//                columnIndex.put(titles[i],i);
//            }else{
//                columnIndex.put(titles[i],i+1);
//            }
//            if(titles[i].equals("ll")){
//                temp = false;
//            }
//        }
//        String[] strs = null;
//        List<Map<String,Object>> nodeMapList = new ArrayList<>();
//        Map<String,Object> map = null;
//        for (int i = 1; i < dataList.size(); i++) {
//            map = new HashMap<>();
//            strs = dataList.get(i).split(",");
//            if(strs.length==0){
//                continue;
//            }
//            map.put("code",strs[columnIndex.get("code")]);
//            map.put("name",strs[columnIndex.get("name")]);
//            Double[] lat = new Double[2];
//            lat[0] = Double.parseDouble(strs[columnIndex.get("lng")]);
//            lat[1] = Double.parseDouble(strs[columnIndex.get("lat")]);
//            map.put("value",lat);
//            nodeMapList.add(map);
//        }
//        return nodeMapList;
//    }

}
