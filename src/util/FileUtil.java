package util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    private static ServletFileUpload upload = null;

    static{
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");
    }

    public FileUtil(){}

    /**
     * 得到浏览器发送过来的FileItem数组
     * @param request
     * @return
     * @throws FileUploadException
     */
    public static List<FileItem> getFileItemList(HttpServletRequest request) throws FileUploadException {
        return upload.parseRequest(request);
    }

    /**
     * 按自定义命名保存数据
     * @param formItems
     * @param uploadPath
     * @param fileName
     * @return
     * @throws FileUploadException
     */
    public static boolean saveFile(List<FileItem> formItems, String uploadPath, String fileName) throws FileUploadException {
        // 解析请求的内容提取文件数据
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField() && item.getSize() > 0) {
                    String filePath = uploadPath + "/" + fileName;
                    File storeFile = new File(filePath);
                    // 保存文件到硬盘
                    try {
                        item.write(storeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 按原文件命名保存数据
     * @param formItems
     * @param uploadPath
     * @return
     * @throws FileUploadException
     */
    public static boolean saveFileBySelfName(List<FileItem> formItems, String uploadPath) throws FileUploadException {
        // 解析请求的内容提取文件数据
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField() && item.getSize() > 0) {
                    String fileName = item.getName();
                    String filePath = uploadPath + "/" + fileName;
                    File storeFile = new File(filePath);
                    // 保存文件到硬盘
                    try {
                        item.write(storeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取表单普通字段名
     * @param fileItems getFileItems 方法获取的表单数据
     * @param fieldName <input> 标签的 name 属性
     * @return 字段值
     */
    public static String getField(List<FileItem> fileItems, String fieldName){
        try {

            for(FileItem fileItem : fileItems){
                if(fileItem.isFormField() && fieldName.equals(fileItem.getFieldName())){

                    return fileItem.getString("UTF-8");
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 遍历一个文件夹中的所有文件
     * @param path 文件夹路径
     * @return 文件名列表
     */
    public static List<String> walkThroughFolder(String path){
        File file = new File(path);

        File[] fs = file.listFiles();

        if(fs == null) {
            return new ArrayList<>();
        }

        List<String> files = new ArrayList<>();

        for(File f : fs){
            files.add(f.getName());
        }

        return files;
    }

    /**
     * 创建文件夹
     * @param file
     * @return
     */
    public static boolean mkdir(File file){
        if (!file.exists()){
            return file.mkdirs();
        }
        return true;
    }

}
