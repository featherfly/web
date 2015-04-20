
package cn.featherfly.web.upload.policy;

import cn.featherfly.web.upload.UploadPolicy;

/**
 * <p>
 * 抽象上传策略.
 * </p>
 *
 * @author 钟冀
 */
public abstract class AbstractUploadPolicy implements UploadPolicy{

	/**
	 * 使用MessageCode获取DisAllowMessage，
	 * code为 具体实现类名.disAllowMessage，例如 X.disAllowMessage，不包含包名
	 * @return DisAllowMessage
	 */
	protected String getDisAllowKey() {
		return this.getClass().getSimpleName() + ".disAllowMessage";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
