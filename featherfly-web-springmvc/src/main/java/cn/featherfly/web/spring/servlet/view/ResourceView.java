/*
 * Copyright 2002-2014 the original author or authors. Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package cn.featherfly.web.spring.servlet.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import cn.featherfly.common.io.file.FileWrapper;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.StringUtils;
import cn.featherfly.web.WebException;

/**
 * <p>
 * DownloadView
 * </p>
 *
 * @author 钟冀
 */
public class ResourceView extends AbstractView {

    private static final int DEFAULT_BUFFERSIZE = 1024 * 10;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InputStream is = null;
        String name = null;
        try {
            if (result instanceof File) {
                File file = (File) result;
                name = file.getName();
                is = new FileInputStream(file);
            } else if (result instanceof FileWrapper) {
                FileWrapper file = (FileWrapper) result;
                name = file.getFileName();
                is = new FileInputStream(file.getFile());
            } else if (result instanceof InputStream) {
                is = (InputStream) result;
            } else if (result instanceof byte[]) {
                is = new ByteArrayInputStream((byte[]) result);
            }
        } catch (FileNotFoundException e) {
            throw new WebException("文件不存在或路径不对", e);
        }
        if (is == null) {
            throw new WebException("文件的对象不是File,FileWapper,byte[],InputStream类型");
        }
        download(request, response, is, result, name);
    }

    protected void download(HttpServletRequest request, HttpServletResponse response, InputStream is,
            Object downloadObj, String name) throws IOException {
        if (StringUtils.isBlank(getContentType())) {
            setContentType("application/octet-stream;charset=" + encodeCharset);
        }
        if (StringUtils.isNotBlank(fileName)) {
            name = fileName;
        }
        if (StringUtils.isBlank(name)) {
            throw new WebException("下载文件的文件未指定下载文件名");
        }
        if (!allowCaching) {
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        }
        String encodeName = getEncodeName(name);
        if (request.getHeader("User-Agent").indexOf("MSIE 5.5") != -1) {
            response.setHeader("Content-Disposition", "filename=" + encodeName);
        } else {
            String contentDisposition = null;
            if (downloadable) {
                contentDisposition = "attachment;filename=" + encodeName;
            } else {
                contentDisposition = "inline;filename=" + encodeName;
            }
            response.setHeader("Content-disposition", contentDisposition);
        }
        response.setHeader("Content-Type", getContentType());
        response.setContentType(getContentType());

        byte[] buffer = new byte[bufferSize];
        int length = 0;
        try {
            OutputStream os = response.getOutputStream();
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            os.close();
        } finally {
            is.close();
            delete(downloadObj);
        }
    }

    /**
     * <p>
     * 下载完成删除文件
     * </p>
     *
     * @param obj obj
     * @return 删除成功与否
     */
    protected boolean delete(Object obj) {
        if (delete && obj != null) {
            if (obj instanceof File) {
                File file = (File) obj;
                return file.delete();
            } else if (obj instanceof FileWrapper) {
                FileWrapper file = (FileWrapper) obj;
                return file.getFile().delete();
            }
            throw new WebException("文件的对象不是File,FileWapper类型，不能自动删除！");
        }
        return false;
    }

    /**
     * <p>
     * 返回编码后的名称
     * </p>
     *
     * @return 编码后的名称
     * @throws UnsupportedEncodingException
     */
    protected String getEncodeName(String name) throws UnsupportedEncodingException {
        if (LangUtils.isEmpty(decodeCharset)) {
            return new String(name.getBytes(), encodeCharset);
        } else {
            return new String(name.getBytes(decodeCharset), encodeCharset);
        }
    }

    // ********************************************************************
    //
    // ********************************************************************

    private Object result;

    private boolean allowCaching;

    private boolean delete;

    private String inputName;

    private String fileName;

    private String decodeCharset;

    private String encodeCharset = "ISO8859-1";

    private int bufferSize = DEFAULT_BUFFERSIZE;

    private boolean downloadable;

    /**
     * 返回allowCaching
     *
     * @return allowCaching
     */
    public boolean isAllowCaching() {
        return allowCaching;
    }

    /**
     * 设置allowCaching
     *
     * @param allowCaching allowCaching
     */
    public void setAllowCaching(boolean allowCaching) {
        this.allowCaching = allowCaching;
    }

    /**
     * 返回inputName
     *
     * @return inputName
     */
    public String getInputName() {
        return inputName;
    }

    /**
     * 设置inputName
     *
     * @param inputName inputName
     */
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    /**
     * 设置encodeCharset
     *
     * @param encodeCharset encodeCharset
     */
    public void setEncodeCharset(String encodeCharset) {
        this.encodeCharset = encodeCharset;
    }

    /**
     * @return 返回fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName 设置fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return 返回bufferSize
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * @param bufferSize 设置bufferSize
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * 返回delete
     *
     * @return delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * 设置delete
     *
     * @param delete delete
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /**
     * 设置decodeCharset
     *
     * @param decodeCharset decodeCharset
     */
    public void setDecodeCharset(String decodeCharset) {
        this.decodeCharset = decodeCharset;
    }

    /**
     * 返回result
     *
     * @return result
     */
    public Object getResult() {
        return result;
    }

    /**
     * 设置result
     *
     * @param result result
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 返回downloadable
     *
     * @return downloadable
     */
    public boolean isDownloadable() {
        return downloadable;
    }

    /**
     * 设置downloadable
     *
     * @param downloadable downloadable
     */
    public void setDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
    }
}
