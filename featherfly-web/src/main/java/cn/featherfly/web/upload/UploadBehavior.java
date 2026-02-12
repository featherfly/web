
package cn.featherfly.web.upload;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.storage.file.FileStorage;

/**
 * 上传行为.
 *
 * @author 钟冀
 */
public class UploadBehavior {

    private List<UploadStrategy> uploadPolicys = new ArrayList<UploadStrategy>();

    private FileStorage fileStorage;

    /**
     * 返回uploadPolicys
     * @return uploadPolicys
     */
    public List<UploadStrategy> getUploadPolicys() {
        return uploadPolicys;
    }

    /**
     * 设置uploadPolicys
     * @param uploadPolicys uploadPolicys
     */
    public void setUploadPolicys(List<UploadStrategy> uploadPolicys) {
        this.uploadPolicys = uploadPolicys;
    }

    /**
     * 返回fileStorage
     * @return fileStorage
     */
    public FileStorage getFileStorage() {
        return fileStorage;
    }

    /**
     * 设置fileStorage
     * @param fileStorage fileStorage
     */
    public void setFileStorage(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

}
