package com.zj.test.mp.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zj.test.mp.enums.SexEnum;
import lombok.Data;

import java.io.Serializable;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/11 16:19
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Data
// @TableName(autoResultMap = true)
public class Teacher implements Serializable {

    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Integer age;

    SexEnum sex;

    // 测试字段类型处理器: 会自动帮我们完成映射,当然这也对表名和字段名有要求
    //@TableField(typeHandler = JacksonTypeHandler.class)
    /**
     * 注: mybatis-plus官方关于字段类型处理的Demo中,提到必须要开启映射:
     * @TableName(autoResultMap = true)
     * 并且要为字段添加@TableField(typeHandler = JacksonTypeHandler.class)或
     * @TableField(typeHandler = FastjsonTypeHandler.class)
     *
     *
     * 而我在测试的时候,上面的注解都没有添加,但是测试成功返回了数据。可能是我的mybatis-plus版本比较新
     * 的缘故,如果使用较低版本的mybatis-plus时,应考虑到这个问题。
     *
     * 我在测试的时候没有添加,会自动完成
     */
    String teachACourse;
}
