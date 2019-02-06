package cn.java.ckEc.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

public class FilesUpload {
    
    public static Map<String, String> uploadFiles(HttpServletRequest request, MultipartRequest files) {
        Map<String, String> filePathMap = new HashMap<String, String>();
        try {
            Map<String, MultipartFile> filesMap = files.getFileMap();
            Set<String> keySet = filesMap.keySet();
            for (String key : keySet) {
                MultipartFile file = filesMap.get(key);
                String originalFileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String strTime = sdf.format(new Date());
                
                String contextPath = request.getServletContext().getRealPath("/");
                System.out.println("contextPath:"+contextPath);
                String positivePath = contextPath.split("webapp")[0]+"resources/static/upload/"+strTime+"/"+uuid;
                System.out.println("positivePath:"+positivePath);
                
                File uploadMkdir = new File(positivePath);
                if(!uploadMkdir.exists())
                {
                	uploadMkdir.mkdirs();
                }
                
                String filePath = positivePath+"/"+originalFileName;
                file.transferTo( new File(filePath));
                // 开始将路劲保存到map集合中
                String path = filePath.split("static")[1];
                System.out.println(path);
                filePathMap.put(uuid, path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePathMap;
    }
}
