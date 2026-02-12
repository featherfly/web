
package cn.featherfly.web.upload.policy;

import cn.featherfly.common.policy.WhiteBlackListPolicy;
import cn.featherfly.web.upload.UploadStrategy;

/**
 * 抽象黑白名单上传策略.
 *
 * @author 钟冀
 */
public abstract class AbstractWhiteBlackListUploadStrategy extends WhiteBlackListPolicy<String> implements UploadStrategy {

    /**
     * 使用MessageCode获取DisAllowMessage，
     * code为 具体实现类名.disAllowMessage，例如 X.disAllowMessage，不包含包名
     *
     * @param blackList 是否是黑名单
     * @return DisAllowMessage
     */
    protected String getDisAllowMessage(boolean blackList) {
        if (blackList) {
            return this.getClass().getSimpleName() + ".disAllowMessage.black";
        } else {
            return this.getClass().getSimpleName() + ".disAllowMessage.white";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getName();
    }

    // ********************************************************************
    //
    // ********************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isEquals(String target, String target2) {
        return target.equalsIgnoreCase(target2);
    }
}
