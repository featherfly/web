
package cn.featherfly.web.upload.policy;

import java.io.File;
import java.util.Collection;

import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil2;
import eu.medsea.mimeutil.detector.ExtensionMimeDetector;
import eu.medsea.mimeutil.detector.MagicMimeMimeDetector;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class ContentTypeUploadPolicyTest {
	public static void main(String[] args) {
		System.out.println(".txt");
		showMimeTypes(new File("C:/Documents and Settings/yufei/桌面/29修改.txt"));
		System.out.println(".zip");
		showMimeTypes(new File("C:/Documents and Settings/yufei/桌面/SWFUpload v2.2.0.1 Samples.zip"));
		System.out.println(".docx");
		showMimeTypes(new File("C:/Documents and Settings/yufei/桌面/BMS后期建议.docx"));
		System.out.println(".xlsx");
		showMimeTypes(new File("C:/Documents and Settings/yufei/桌面/成都市BMP任务计划.xlsx"));
		System.out.println(".JPG");
		showMimeTypes(new File("C:/Documents and Settings/yufei/桌面/100_3772.JPG"));
	}

	private static void showMimeTypes(File file) {
		MimeUtil2 mimeUtil = new MimeUtil2();
		mimeUtil.registerMimeDetector(MagicMimeMimeDetector.class.getName());
		mimeUtil.registerMimeDetector(ExtensionMimeDetector.class.getName());
//		mimeUtil.registerMimeDetector(OpendesktopMimeDetector.class.getName());
		Collection<MimeType> mimeTypes = mimeUtil.getMimeTypes(file);
		for (MimeType mimeType : mimeTypes) {
			System.out.println(mimeType);
		}
	}
}
