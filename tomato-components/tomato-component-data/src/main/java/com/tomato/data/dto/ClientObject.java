package com.tomato.data.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回客户端的值
 * view 层， 消费者
 * @author lizhifu
 * @date 2022/5/28
 */
public abstract class ClientObject implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 扩展数据
     */
    protected Map<String, Object> extValues = new HashMap<String, Object>();

    public Object getExtField(String key){
        if(extValues != null){
            return extValues.get(key);
        }
        return null;
    }

    public void putExtField(String fieldName, Object value){
        this.extValues.put(fieldName, value);
    }

    public Map<String, Object> getExtValues() {
        return extValues;
    }

    public void setExtValues(Map<String, Object> extValues) {
        this.extValues = extValues;
    }
}
