package cn.featherfly.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import cn.featherfly.common.lang.SystemPropertyUtils;

/**
 * <p>
 * PrintWriter的包装类，所有的输出方法均使用传入的StringWriter实现.
 * </p>
 * 
 * @author 钟冀 
 */
public class StringPrintWriter extends PrintWriter {

	private StringWriter sw;
	/**
	 * @param sw
	 */
	public StringPrintWriter(StringWriter sw) {
		super(sw);
		try {
			this.sw = sw;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(String s) {
		sw.write(s);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(int c) {
		sw.write(c);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(char buf[], int off, int len) {
		sw.write(buf, off, len);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(char buf[]) {
		sw.write(buf, 0, buf.length);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(String s, int off, int len) {
		sw.write(s, off, len);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(String s) {
		sw.write(s, 0, s.length());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(boolean b) {
		sw.write(b ? "true" : "false");
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(char c) {
		sw.write(c);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(int i) {
		sw.write(String.valueOf(i));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(long l) {
		sw.write(String.valueOf(l));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(float f) {
		sw.write(String.valueOf(f));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(double d) {
		sw.write(String.valueOf(d));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(char s[]) {
		try {
			sw.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(String s) {
		if (s == null) {
			s = "null";
		}
		sw.write(s);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(Object obj) {
		sw.write(String.valueOf(obj));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println() {
		newLine();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(boolean x) {
		synchronized (lock) {
			print(x);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(char x) {
		synchronized (lock) {
			print(x);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(int x) {
		synchronized (lock) {
			print(x);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(long x) {
		synchronized (lock) {
			print(x);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(float x) {
		synchronized (lock) {
			print(x);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(double x) {
		synchronized (lock) {
			print(x);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(char x[]) {
		synchronized (lock) {
			print(x);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void println(Object x) {
		String s = String.valueOf(x);
		synchronized (lock) {
			print(s);
			println();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrintWriter append(CharSequence csq) {
		if (csq == null)
			sw.write("null");
		else
			sw.write(csq.toString());
		return this;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrintWriter append(CharSequence csq, int start, int end) {
		CharSequence cs = (csq == null ? "null" : csq);
		sw.write(cs.subSequence(start, end).toString());
		return this;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrintWriter append(char c) {
		sw.write(c);
		return this;
	}
	
	// ********************************************************************
	//	
	// ********************************************************************

	private String lineSeparator = SystemPropertyUtils.getLineSeparator();

	private void newLine() {
		sw.write(lineSeparator);
	}
}
