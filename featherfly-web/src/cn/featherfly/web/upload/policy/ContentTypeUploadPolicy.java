
package cn.featherfly.web.upload.policy;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.web.upload.UploadException;
import cn.featherfly.web.upload.UploadFile;
import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil2;
import eu.medsea.mimeutil.detector.ExtensionMimeDetector;
import eu.medsea.mimeutil.detector.MagicMimeMimeDetector;

/**
 * <p>
 * 文件类型上传策略
 * </p>
 *
 * @author 钟冀
 */
public class ContentTypeUploadPolicy extends AbstractWhiteBlackListUploadPolicy {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContentTypeUploadPolicy.class);

	/**
	 */
	public ContentTypeUploadPolicy() {
		mimeUtil = new MimeUtil2();
		mimeUtil.registerMimeDetector(MagicMimeMimeDetector.class.getName());
		mimeUtil.registerMimeDetector(ExtensionMimeDetector.class.getName());
//		mimeUtil.registerMimeDetector(OpendesktopMimeDetector.class.getName());
	}

	private MimeUtil2 mimeUtil;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isAllowUpload(UploadFile uploadFile) {
		@SuppressWarnings("unchecked")
		Collection<MimeType> mimeTypes = mimeUtil.getMimeTypes(uploadFile.getFile());
		LOGGER.debug("文件：{}", uploadFile.getFileName());
		for (MimeType mimeType : mimeTypes) {
//			// 如果黑名单包含，直接不通过
//			LOGGER.debug("\t类型：{}", mimeType);
			String mimeT = mimeType.toString();
			if (isInBlackList(mimeT)) {
				throw new UploadException("#"+getDisAllowMessage(true), new Object[]{
					uploadFile.getFileName(), mimeT, getBlackList().toString()
				});
			}
		}
		for (MimeType mimeType : mimeTypes) {
			// 如果白名单包含，则通过
//			LOGGER.debug("\t类型：{}", mimeType);
			String mimeT = mimeType.toString();
			if (isInWhiteList(mimeT)) {
				return;
			}
		}
		// 走到这里表明白名单不包含
		throw new UploadException("#"+getDisAllowMessage(false), new Object[]{
			uploadFile.getFileName(), mimeTypes.toString(), getWhiteList().toString()
		});
	}
}
