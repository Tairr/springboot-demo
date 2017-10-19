package com.example.controller.app;

import com.example.domain.app.App;
import com.example.service.app.IAppService;
import com.example.util.TimeUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by dell on 2017/5/16.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@RestController
@RequestMapping("/app")
public class AppController {

    String filePath = "file/";
    @Autowired
    private IAppService appService;


    @RequestMapping(method = RequestMethod.GET)
    public String queryForList() {
        List<App> appList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        appList = this.appService.queryForList(new App());
        resultMap.put("code", "0000");
        resultMap.put("data", appList);

        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody App app) {
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        if (StringUtils.isEmpty(app)) {
            resultMap.put("code", "0002");
            resultMap.put("data", "authority insert failed");
            return gson.toJson(resultMap);
        }
        app.setId(UUID.randomUUID().toString());
        app.setCreateTime(TimeUtils.getCurrentTime());
        app.setUpdateTime(TimeUtils.getCurrentTime());
        app.setIfuse("y");
        int flag = this.appService.addObject(app);

        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0092");
            resultMap.put("data", "app insert failed");
        }

        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
        App app = new App();
        app.setId(id);
        int flag = this.appService.deleteObject(app);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0091");
            resultMap.put("data", "app delete failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

  /*  @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String codeUpload(HttpServletRequest request, HttpServletResponse res) throws Exception {

        List<Map<String, String>> fileContentList = new ArrayList<>();
        Gson gson = new Gson();
        System.out.println("in");
        String userExcel = "";


        //解析器解析request的上下文
        CommonsMultipartResolver muResolvers =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        //先判断request中是否包涵multipart类型的数据，
        if (muResolvers.isMultipart(request)) {
            //再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator itter = multiRequest.getFileNames();
            while (itter.hasNext()) {
                //这里的name为fileItem的alias属性值，相当于form表单中name
                String name = (String) itter.next();
                System.out.println("name:" + name);
                //根据name值拿取文件
                MultipartFile file = multiRequest.getFile(name);
                if (file != null) {
                    String fileName = file.getOriginalFilename();
                    String path = filePath + fileName;
                    userExcel = path;
                    File localFile = new File(path);
                    if (!localFile.getParentFile().exists()) {
                        //如果目标文件所在的目录不存在，则创建父目录
                        localFile.getParentFile().mkdirs();
                        System.out.println("parent:" + localFile.getParentFile().getPath());
                    }
                    //写文件到本地
                    try {
                        //file.transferTo(localFile);
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }
                        FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                }
            }
        }
        File file = new File(userExcel);
        if (file.exists()) {
            System.out.println("我是读到的EXCEL" + file.getAbsolutePath());
            try {
                fileContentList = ExcelUtils.readExcel(file.getAbsolutePath());


                for (int i = 0; i < fileContentList.size(); i++) {
if(fileContentList.get(i).get("0")==null||fileContentList.get(i).get("0").equals("")) {
                           break;
}else {
    Encoder cc = new Encoder();
    cc.setEncoder_name(fileContentList.get(i).get("0"));
    cc.setGroups(fileContentList.get(i).get("1"));
    cc.setEncoder_address(fileContentList.get(i).get("2"));
    cc.setEncoder_port(fileContentList.get(i).get("3"));
    cc.setEncoder_channel(fileContentList.get(i).get("4"));
    cc.setEncoder_stream_number(fileContentList.get(i).get("5"));
    cc.setDecod_channel(fileContentList.get(i).get("6"));
    cc.setLit_channel_number(fileContentList.get(i).get("7"));
    cc.setLoginname(fileContentList.get(i).get("8"));
    cc.setPassword(fileContentList.get(i).get("9"));
    this.iec.add(cc);
}
                    }

                file.delete();

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (FileFormatException e) {
                e.printStackTrace();

            }

            if (fileContentList.size() == 0) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("code", "10004");
                resultMap.put("data", "excel is null");
                return gson.toJson(resultMap);
            }
            ///到目前为止只是可以上传文件！//////

        }

        return null;
    }*/




}
