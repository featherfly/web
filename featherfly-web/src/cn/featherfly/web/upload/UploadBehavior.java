
package cn.featherfly.web.upload;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.storage.file.FileStorage;

/**
 * <p>
 * 上传行为
 * </p>
 *
 * @author 钟冀
 */
public class UploadBehavior {

	private List<UploadPolicy> uploadPolicys = new ArrayList<UploadPolicy>();

	private FileStorage fileStorage;

	/**
	 * 返回uploadPolicys
	 * @return uploadPolicys
	 */
	public List<UploadPolicy> getUploadPolicys() {
		return uploadPolicys;
	}

	/**
	 * 设置uploadPolicys
	 * @param uploadPolicys uploadPolicys
	 */
	public void setUploadPolicys(List<UploadPolicy> uploadPolicys) {
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
