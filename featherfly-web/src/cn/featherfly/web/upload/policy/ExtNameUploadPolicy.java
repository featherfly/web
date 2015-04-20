
package cn.featherfly.web.upload.policy;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.web.upload.UploadException;
import cn.featherfly.web.upload.UploadFile;

/**
 * <p>
 * 扩展名上传策略
 * </p>
 *
 * @author 钟冀
 */
public class ExtNameUploadPolicy extends AbstractWhiteBlackListUploadPolicy {

	/**
	 */
	public ExtNameUploadPolicy() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isAllowUpload(UploadFile uploadFile) {
		String extName = FileUtils.getFileExtName(uploadFile.getFileName());
		
		// 如果黑名单包含，直接不通过
		if (isInBlackList(extName)) {
			throw new UploadException("#"+getDisAllowMessage(true), new Object[]{
				uploadFile.getFileName(), extName, getBlackList().toString()
			});
		}
		if (!isInWhiteList(extName)) {
			throw new UploadException("#"+getDisAllowMessage(false), new Object[]{
				uploadFile.getFileName(), extName, getWhiteList().toString()
			});
		}
	}

	// ********************************************************************
	//
	// ********************************************************************

}
