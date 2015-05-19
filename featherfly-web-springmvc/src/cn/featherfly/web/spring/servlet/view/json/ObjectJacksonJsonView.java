/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.featherfly.web.spring.servlet.view.json;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <p>
 * ObjectJacksonJsonView
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public class ObjectJacksonJsonView extends MappingJackson2JsonView {
	
	/**
	 */
	public ObjectJacksonJsonView() {
		this(null);
	}
	
	/**
	 * 
	 * @param resultObject resultObject
	 */
	public ObjectJacksonJsonView(Object resultObject) {
		getObjectMapper().setSerializationInclusion(Include.NON_EMPTY);
		setResult(resultObject);
	}
	
	/**
	 * return result
	 * @param model the model, as passed on to {@link #renderMergedOutputModel}
	 * @return the value to be rendered -> resultObject
	 */
	@Override
	protected Object filterModel(Map<String, Object> model) {
		return result;
	}	
	
	/**
	 * 返回result
	 * @return result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * 设置result
	 * @param result result
	 */
	public void setResult(Object result) {
		this.result = result;
	}


	private Object result;

}
