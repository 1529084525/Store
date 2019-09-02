package util;


import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

public abstract class FastDFSUtil {


    public static String getUrlImage(MultipartFile multipartFile) throws Exception {
        //1、向工程中添加jar包
        //2、创建一个配置文件。配置tracker服务器地址
        //3、加载配置文件

        ClientGlobal.init("client.properties");
        //4、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        //5、使用TrackerClient对象获得trackerserver对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        //6、创建一个StorageServer的引用null就可以。
        StorageServer storageServer = null;
        //7、创建一个StorageClient对象。trackerserver、StorageServer两个参数。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //8、使用StorageClient对象上传文件。
        String[] strings = storageClient.uploadFile(multipartFile.getBytes(), "jpg", null);
        /*
         *   http://106.12.86.106:8888/group1/M00/00/00/wKgABlzaDbyAVKezAAACHiPlt04668.jpg
         * */
        if (strings[1] == null) {
            return null;
        } else {
            return "http://106.12.86.106:8888/group1/" + strings[1];
        }
    }


}
