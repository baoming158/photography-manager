package com.dmwys.photography.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmwys.photography.domain.UserImpl;
@SuppressWarnings("unchecked")
public class UIAUtil {
    public static Logger logger = LoggerFactory.getLogger(UIAUtil.class);
    /** UIA 系统分配的 app_id **/ 
    private static String app_id = ResourceHelper.get("uia.app_id");
    /** UIA 系统分配的 secret **/
    private static String secret = ResourceHelper.get("uia.secret");//"34Ov5fZ7ko";
    private static String uia_domain = ResourceHelper.get("uia.domain");//"http://uiatest.in.kuyun.com";
    
    private static String login_url = ResourceHelper.get("uia.login_url");//"/uia/api/remote_login";
    private static String list_user_url = ResourceHelper.get("uia.list_user");//"/uia/api/remote_login";
    private static String testuser = ResourceHelper.get("uia.testuser");///uia/api/getUserIdsByResourceId
    private static String resource_list = ResourceHelper.get("uia.resource_list");///uia/api/getUserIdsByResourceId
    private static String user_resource_list = ResourceHelper.get("uia.user_resource_list");///uia/api/user_resource_list
    
    /**
     * 发送接口校验请求
     * 
     * @param url
     * @param params
     * @param timeout
     * @return
     */
    public static Response post(String url, Map<String, Object> params, int timeout) {
        params.put("app_id", app_id);
        
        // 接口拼装排序
        String[] keyVals = new String[params.size()];
        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key).toString();
            keyVals[i++] = key + value;
        }
        Arrays.sort(keyVals);
        
        // 连接参数名与参数值,并在首尾加上secret
        StringBuilder sb = new StringBuilder();
        sb.append(secret);
        for (String keyVal : keyVals) {
            sb.append(keyVal);
        }
        sb.append(secret);
        
        // 参数进行sign签名。MD5加密必须指定编码格式位UTF-8
        String md5 = MD5Util.getMd5(sb.toString().getBytes()).toUpperCase();

        // 加上 sign 参数
        params.put("sign", md5);
        
        // 提交http请求
//        NameValuePair[] param = new NameValuePair[params.size()];
//        for(String key : params.keySet()){
//            NameValuePair p = new NameValuePair();
//            p.setName(key);
//            p.setValue(params.get(key).toString());
//        }
        Response response = Http.post2(url, params, 0);
        return response;
    }
    
    public static int LoginWithOriginPwd(String user_name,String password){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("username", user_name);
        params.put("password", MD5Util.getMd5(password.getBytes()));
        Response resp = post(uia_domain + login_url,params,60000);
        if(resp.getStatus() == 200){
            final String content = resp.getContent();
            Map<String,Object> record = (Map<String, Object>) Json.fromJson(content);
            int code = (Integer) record.get("code");
            if(code == 0){
                return Constants.ERROR_0;
            }
        }
        return Constants.ERROR_1008;
    }
    public static UserImpl getUserImpl(String user_name,String password){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("username", user_name);
        System.out.println("password -------------"+password);
        logger.info("password -------------"+password);
        params.put("password", MD5Util.getMd5(password.getBytes()));
        Response resp = post(uia_domain + login_url,params,60000);
        if(resp.getStatus() == 200){
            final String content = resp.getContent();
            Map<String,Object> record = (Map<String, Object>) Json.fromJson(content);
            int code = (Integer) record.get("code");
            if(code == 0){
                Map<String,Object> result = (Map<String, Object>) record.get("result");
                UserImpl user = convertToUserImpl(result);
                return user;    
            }
        }
        return null;
    }
    
    public static String getUserNames(String resourceId){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("resourceId", resourceId);
        Response resp = post(uia_domain + testuser,params,60000);
        if(resp.getStatus() == 200){
            final String content = resp.getContent();
            Map<String,Object> record = (Map<String, Object>) Json.fromJson(content);
            int code = (Integer) record.get("code");
            if(code == 0){
                String result = (String) record.get("result");
                return  result;    
            }
        }
        return null;
    }
    
    public static List<String> loadAccessAuth(Long userId,String appId){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("app_id", appId);
        params.put("user_id", userId);
        Response resp = post(uia_domain + user_resource_list,params,60000);
        if(resp.getStatus() == 200){
            final String content = resp.getContent();
            Map<String,Object> record = (Map<String, Object>) Json.fromJson(content);
            int code = (Integer) record.get("code");
            if(code == 0){
                List<String> result = (List<String>) record.get("result");
                return  result;    
            }
        }
        return null;
    }
    
    public static List<String> loadAllAccessAuth(String appId){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("appId", appId);
        Response resp = post(uia_domain + resource_list,params,60000);
        if(resp.getStatus() == 200){
            final String content = resp.getContent();
            Map<String,Object> record = (Map<String, Object>) Json.fromJson(content);
            int code = (Integer) record.get("code");
            if(code == 0){
                List<String> result =  (List<String>) record.get("result");
                return  result;    
            }
        }
        return null;
    }
    
    public static List<UserImpl> listUser(Integer page_number,Integer page_size){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("deleted", 0);
        params.put("type", 1);
        params.put("page_number", page_number);
        params.put("page_size", page_size);
        Response resp = post(uia_domain + list_user_url,params,60000);
        if(resp.getStatus() == 200){
            final String content = resp.getContent();
            try {
                String new_content = new String(content.getBytes(),"UTF-8");
                System.err.println(new_content);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Map<String,Object> record = (Map<String, Object>) Json.fromJson(content);
            int code = (Integer) record.get("code");
            if(code == 0){
                Map<String,Object> result = (Map<String, Object>) record.get("result");
                System.out.println(result);
                List<Map<String,Object>> list = (List<Map<String, Object>>) result.get("list");
                List<UserImpl> user_list = convert2UserImplList(list);
                return user_list;
            }
        }
        return null;
    }
    
    private static List<UserImpl> convert2UserImplList(List<Map<String, Object>> list) {
        if(list != null && list.size() > 0){
            List<UserImpl> user_list = new ArrayList<UserImpl>();
            for(Map<String,Object> map : list){
                UserImpl user = convertToUserImpl(map);
                user_list.add(user);
            }
            return user_list;
        }
        return null;
    }

    private static UserImpl convertToUserImpl(Map<String, Object> map) {
        UserImpl user = new UserImpl();
        user.setChName((String) map.get("chname"));
        user.setName((String) map.get("username"));
        user.setPhoneNum((String) map.get("phonenum"));
        user.setEmail((String) map.get("email"));
        user.setFlag((Integer) map.get("deleted"));
        user.setId(Long.valueOf(map.get("id").toString()));
        return user;
    }

    public static void main(String[] a){
        UIAUtil.listUser(1, 10);
    }
}
