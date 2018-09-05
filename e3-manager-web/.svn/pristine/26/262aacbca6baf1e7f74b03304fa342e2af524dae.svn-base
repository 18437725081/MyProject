package nyist.e3.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import nyist.e3.utils.FastDFSClient;
import nyist.e3.utils.JsonUtils;

/**
 * 完成图片的上传操作
 * 
 * @author Administrator
 *
 */
@Controller
public class PictureController {
	// value注解的作用是从.properties文件中获得key对应的value值 通过 ${key}得到对应的value值
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	/**
	 * 实现图片上传的功能， 分析 1.使用图片上传的工具类，创建文件上传对象 2.获得文件的后缀名 3.实现文件上传
	 * 4.将返回的url和ip地址拼接，返回在图片服务器上的绝对路径 5.将结果封装成map，返回
	 * 6.解决浏览器建兼容性的问题，指定返回的content-type为test/plain即可
	 * 
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value = "/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		Map result = new HashMap<>();
		try {
			// 创建文件上传对象
			FastDFSClient fastClient = new FastDFSClient("classpath:conf/tracker_client.properties");
			// 获得文件的名称
			String originalFilename = uploadFile.getOriginalFilename();
			// 获得文件的后缀名
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			// 返回上传的文件的相对的url
			String path = fastClient.uploadFile(uploadFile.getBytes(), extName);
			// 将url和ip地址拼接成完成的绝对路径
			String url = IMAGE_SERVER_URL + path;
			// 图片上传成功，返回的数据为：error ： 0 url： http://ip...
			result.put("error", 0);
			result.put("url", url);
		} catch (Exception e) {
			// 上传失败 返回的数据为 error ： 1 message ："信息。。。。"
			result.put("error", 1);
			result.put("message", "图片上传失败");
		}
		//将结果转换成json返回
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
