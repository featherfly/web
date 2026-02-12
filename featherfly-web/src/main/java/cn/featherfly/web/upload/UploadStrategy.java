package cn.featherfly.web.upload;


/**
 * 上传文件策略.
 *
 * @author 钟冀
 */
public interface UploadStrategy {
    /**
     * 是否允许上传,不允许时，抛出UploadException.
     *
     * @param uploadFile 上传的文件
     */
    void isAllowUpload(UploadFile uploadFile) throws UploadException;
}
