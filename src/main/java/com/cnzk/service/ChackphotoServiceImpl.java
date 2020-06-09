package com.cnzk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cnzk.utils.ChangeBase64;
import com.cnzk.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;


@Service
public class ChackphotoServiceImpl implements ChackphotoService
{

	@Override
	public String file(MultipartFile file)
	{

		String change64=ChangeBase64.multipartFileToBASE64(file);
		System.out.println("change64==============================================================");
		System.out.println("change64:"+change64);
		String host = "https://ocrcp.market.alicloudapi.com";
		String path = "/rest/160601/ocr/ocr_vehicle_plate.json";
		String appcode = "d6e1bf69d34f4891b0e5ec5ff91089c5";
//		String imgFile = "E:\\Desktop\\u0.jpg";
		Boolean is_old_format = false;//如果文档的输入中含有inputs字段，设置为True， 否则设置为False
		//请根据线上文档修改configure字段
		JSONObject configObj = new JSONObject();
		configObj.put("multi_crop", false);
		String config_str = configObj.toString();
		//            configObj.put("min_size", 5);
		//String config_str = "";

		String method = "POST";
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);

		Map<String, String> querys = new HashMap<String, String>();

		// 对图像进行base64编码
		String imgBase64 = change64;
//		try {
//			File file1 = new File(imgFile);
//			byte[] content = new byte[(int) file1.length()];
//			FileInputStream finputstream = new FileInputStream(file1);
//			finputstream.read(content);
//			finputstream.close();
//			imgBase64 = new String(encodeBase64(content));
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
		// 拼装请求body的json字符串
		JSONObject requestObj = new JSONObject();
		try {
			if(is_old_format) {
				JSONObject obj = new JSONObject();
				obj.put("image", getParam(50, imgBase64));
				if(config_str.length() > 0) {
					obj.put("configure", getParam(50, config_str));
				}
				JSONArray inputArray = new JSONArray();
				inputArray.add(obj);
				requestObj.put("inputs", inputArray);
			}else{
				requestObj.put("image", imgBase64);
				if(config_str.length() > 0) {
					requestObj.put("configure", config_str);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String bodys = requestObj.toString();

		try {
			/**
			 * 重要提示如下:
			 * HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			int stat = response.getStatusLine().getStatusCode();
			if(stat != 200){
				System.out.println("Http code: " + stat);
				System.out.println("http header error msg: "+ response.getFirstHeader("X-Ca-Error-Message"));
				System.out.println("Http body error msg:" + EntityUtils.toString(response.getEntity()));
				return null;
			}

			String res = EntityUtils.toString(response.getEntity());
//			String[] arr=res.split("txt");
			String[] arr=res.split("txt＇:＇");
			String[] arr2=arr[1].split("＇,＇cls_prob＇");
			System.out.println(arr[1]);
			System.out.println(arr2[0]);
			System.out.println(arr);
			JSONObject res_obj = JSON.parseObject(res);
			if(is_old_format) {
				JSONArray outputArray = res_obj.getJSONArray("outputs");
				String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue");
				JSONObject out = JSON.parseObject(output);
				System.out.println(out.toJSONString());
			}else{
				System.out.println(res_obj.toJSONString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 获取参数的json对象
	 */
	public static JSONObject getParam(int type, String dataValue) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("dataType", type);
			obj.put("dataValue", dataValue);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static void main(String[] args){
String res = "{＇config_str＇:＇{\\＇multi_crop\\＇:false}＇,＇plates＇:[{＇cls_name＇:＇小型汽车＇,＇prob＇:0.99838340282440186,＇txt＇:＇苏DS0000＇,＇cls_prob＇:1,＇detail＇:＇＇,＇roi＇:{＇w＇:201,＇h＇:64,＇x＇:185,＇y＇:76}}],＇success＇:true,＇request_id＇:＇20200608235402_350234ed7be5aeb18de012f31539fce7＇}";
		String[] arr=res.split("txt＇:＇");
		String[] arr2=arr[1].split("＇,＇cls_prob＇");
	    System.out.println(arr[1]);
		System.out.println(arr2[0]);
	}
}
