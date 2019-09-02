package util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * 转换File
 */
public class ConvertFileUtil {

    public static File getFile(HttpServletRequest request,
                                MultipartFile multfile) throws Exception {
        File excelFile = null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            excelFile = File.createTempFile(UUID.randomUUID().toString(), ".jpg");
            // MultipartFile to File
            multfile.transferTo(excelFile);
            return excelFile;
        } finally {
            //程序结束时，删除临时文
            deleteFile(excelFile);
        }
    }

    /**
     * 删除
     *
     * @param files
     */
    private static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }


}
