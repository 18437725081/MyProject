package nyist.fastdfs.test;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import nyist.e3.utils.FastDFSClient;

/**
 * 将图片上传到fastdfs服务器
 * 1.加载配置文件（配置中配置图片服务器的路径）
 * 2.创建TrackerClient对象
 * 3.通过trackerClient对象获得连接返回一个TrackerServer对象
 * 4.初始化一个StorageServer对象
 * 5.创建StorageClient对象，将trackerServer和storageServer作为参数传递
 * 6.通过storageClient对象完成图片的上传功能
 * 7.上传文件的拓展名中没有“点”
 * 8.上传成功后返回一个图片在服务器中的组和二级目录和图片的文件名
 * @author Administrator
 *
 */
public class FastDfsTest {

	@Test
	public void testFastDfs() {
		// 1.加载配置文件(配置文件的路径是在磁盘中的绝对路径)
		try {
			ClientGlobal.init("E:/EclipseWorkSpace/e3-manager-web/src/main/resources/conf/tracker_client.properties");
			// 2.创建TrackerClient对象
			TrackerClient trackerClient = new TrackerClient();
			// 3.创建对象连接，获得TrackerServer对象
			TrackerServer trackerServer = trackerClient.getConnection();
			// 4.创建文件储存对象StorageServer,初始化为null
			StorageServer storageServer = null;
			// 5.创建StorageClient对象
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			// 6.使用StorageClient上传对象
			// 上传的文件拓展名没有 "."
			String local_filename="C:/Users/Administrator/Pictures/u=3420612333,1068409103&fm=27&gp=0.jpg";
			String[] upload_file = storageClient.upload_file(local_filename, "jpg", null);
			for (String string : upload_file) {
				System.out.println(string);
				
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	@Test
	public void testFastDfsUtil() {
		try {
			FastDFSClient  fastDFSClient = new FastDFSClient("E:/EclipseWorkSpace/e3-manager-web/src/main/resources/conf/tracker_client.properties");
			String uploadFile = fastDFSClient.uploadFile("C:/Users/Administrator/Pictures/u=2766373166,174637563&fm=200&gp=0.jpg", "jpg");
			System.out.println(uploadFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
