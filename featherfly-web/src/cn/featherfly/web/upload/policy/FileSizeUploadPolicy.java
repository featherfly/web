
package cn.featherfly.web.upload.policy;

import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.web.upload.UploadException;
import cn.featherfly.web.upload.UploadFile;

/**
 * <p>
 * 文件大小上传策略
 * </p>
 *
 * @author 钟冀
 */
public class FileSizeUploadPolicy extends AbstractUploadPolicy {

	/**
	 */
	public FileSizeUploadPolicy() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isAllowUpload(UploadFile uploadFile){
		if (uploadFile.getLength() <= maxSize) {
			throw new UploadException("#"+ getDisAllowKey(), new Object[]{
				uploadFile.getFileName()
				, uploadFile.getLength() + " - " + WordUtils.parseUnit(uploadFile.getLength())
				, maxSize + " - " + WordUtils.parseUnit(maxSize)
				});
		}
	}

	// ********************************************************************
	//
	// ********************************************************************

	private Integer maxSize;

	/**
	 * 返回maxSize
	 * @return maxSize
	 */
	public Integer getMaxSize() {
		return maxSize;
	}

	/**
	 * 设置maxSize
	 * @param maxSize maxSize
	 */
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
}
