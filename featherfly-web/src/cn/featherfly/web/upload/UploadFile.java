package cn.featherfly.web.upload;

import java.io.File;

import cn.featherfly.common.io.file.FileWrapper;



/**
 * <p>
 * 上传文件的包装类
 * </p>
 *
 * @author 钟冀
 */
public class UploadFile extends FileWrapper{
	/**
	 * 默认构造方法
	 * @param file file
	 */
	public UploadFile(File file) {
		super(file);
	}

	private String itemName;

	/**
	 * @return 返回itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName 设置itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
