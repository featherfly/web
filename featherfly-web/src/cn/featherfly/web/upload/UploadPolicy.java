package cn.featherfly.web.upload;


/**
 * <p>
 * 上传文件策略
 * </p>
 *
 * @author 钟冀
 */
public interface UploadPolicy {
	/**
	 * <p>
	 * 是否允许上传,不允许时，抛出UploadException
	 * </p>
	 * @param uploadFile 上传的文件 
	 */
	void isAllowUpload(UploadFile uploadFile) throws UploadException;
}
