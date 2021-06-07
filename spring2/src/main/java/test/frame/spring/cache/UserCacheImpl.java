package test.frame.spring.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import ssm.test.controller.User;

/**
 * @CreateTime��2020��2��12�� ����12:08:36
 * @Author��zhoujian
 * @QQ��2025513
 * @FileDescription��
 * @IsFinished��false
 */
@Service
public class UserCacheImpl {

	/**
	 * <pre>
	 * 1.@Cacheableע���ʹ��
	 * ���Ա����һ�������ϣ�Ҳ���Ա����һ�����ϡ��������һ��������ʱ��ʾ�÷�����֧�ֻ���ģ��������һ������ʱ���ʾ�������еķ�������֧�ֻ���ġ�
	 * 
	 * ���ڷ����ͻ��Ϊ�ɻ���ķ���,���÷����״ε���ʱ,�Ὣ���صĽ�����뵽������,�ڶ��ε��ô˷���ʱ,ֻҪ
	 * ���ݵĲ���ֵ��֮ǰ����ʱһ��,�ͻ�ֱ�Ӵӻ�����ȡ�����������,������뵽������

	 * 1.1.ע: valueָ��cache name,�����������ļ��д���,����÷������ǻ��淽����
	 * 
	 * 1.2.key:
	 * 1.2.1.Ĭ�ϲ���,����ṩkey,ehcache��ʹ��Ĭ��key���ԡ�
	 * 1.2.2.�Զ������
	 * ��ָ���ǿ���ͨ��Spring��EL���ʽ��ָ�����ǵ�key�������EL���ʽ����ʹ�÷������������Ƕ�Ӧ�����ԡ�
	 * ʹ�÷�������ʱ���ǿ���ֱ��ʹ�á�#�����������ߡ�#p����index����
	 * 
	 * ��������ʹ�÷���������Ϊkey֮�⣬Spring��Ϊ�����ṩ��һ��root���������������key��ͨ����root�������ǿ��Ի�ȡ��������Ϣ��
	 * #root.methodName  ��ǰ������
	 * #root.method.name ��ǰ����
	 * #root.target  ��ǰ�����õĶ���
	 * #root.targetClass  ��ǰ�����õĶ����class
	 * #root.args[0]  ��ǰ����������ɵ�����
	 * #root.caches[0].name ��ǰ�����õķ���ʹ�õ�Cache
	 * ע:#root��ʡ��,��ΪĬ����ʹ��#root
	 * �磺
	 *@Cacheable(value={"users", "xxx"}, key="caches[1].name")
	 *public User find(User user) {
	 *   returnnull;
	 *}
	 *
	 *1.3.condition����
	 * ������ָ�����������,Ĭ�����Ϊ"",�Ỻ�����еĽ��,�����淽��testCondition��
	 * * */
	/*@Cacheable(value="hibernateUtilCache",key="#userId")
	public String getCacahedUserName(int userId) {
		System.out.println("-----getCacahedUserName----");
		return "username" + userId;
	}*/
	
	// ʹ��token�������Զ���key
	// ƴ���ַ�������:  @Cacheable(value="hibernateUtilCache",key="#p1+"ddd"")  // �������
	
	// ����չʾ�������ƴ��
	// @Cacheable(value="hibernateUtilCache",key="#p1+'UserCacheImpl.getCacahedUserName' + #userId")
	
	// ������ƴ�ӵ�ʱ��,Ҳ����ʡ��#root��ʹ��root����: 
	@Cacheable(value="hibernateUtilCache",key="#p1+'UserCacheImpl.getCacahedUserName' + args[0]")
	public String getCacahedUserName(int userId,String token) {
		System.out.println("-----getCacahedUserName----");
		return "username" + userId;
	}
	
	// #user.age
	// %
	// ��������ӻ������18����û�
	//@Cacheable(value="hibernateUtilCache",condition="#age>=18")
	
	//������ʾ���ڵ���д
	// ==��������=,�����׳�: org.springframework.expression.spel.SpelEvaluationException: 
	// EL1001E:(pos 0): Type conversion problem, cannot convert from java.lang.Integer to boolean
	
	//@Cacheable(value="hibernateUtilCache",condition="#age==18")
	
	@CachePut(value="hibernateUtilCache")
	public String testCondition(int age) {
		System.out.println("-----testCondition-----");
		return "user"+age;
	}
	
	/**
	 * 2.@CachePut("cacheName") - ��ע������һ��cache name ��֧��Spring
	 * Cache�Ļ����£�����ʹ��@Cacheable��ע�ķ�����Spring��ÿ��ִ��ǰ������Cache���Ƿ������ͬkey�Ļ���Ԫ�أ�������ھͲ���ִ�и÷���������ֱ�Ӵӻ����л�ȡ������з��أ�����Ż�ִ�в������ؽ������ָ���Ļ����С�@CachePutҲ��������һ������֧�ֻ��湦�ܡ���@Cacheable��ͬ����ʹ��@CachePut��ע�ķ�����ִ��ǰ����ȥ��黺�����Ƿ����֮ǰִ�й��Ľ��������ÿ�ζ���ִ�и÷���������ִ�н���Լ�ֵ�Ե���ʽ����ָ���Ļ����С�
	 * 
	 * @CachePutҲ���Ա�ע�����Ϻͷ����ϡ�ʹ��@CachePutʱ���ǿ���ָ�������Ը�@Cacheable��һ���ġ�
	 * 
	 * @CachePut("users")//ÿ�ζ���ִ�з����������������ָ���Ļ�����
	 * public User find(Integer id) {
	 *
	 *     retur nnull;
	 * 
	 * }
	 */
	
	/**
	3.@CacheEvict
    @CacheEvict��������ע����Ҫ�������Ԫ�صķ��������ϵġ��������һ������ʱ��ʾ�������еķ�����ִ�ж��ᴥ����������������
    @CacheEvict����ָ����������value��key��condition��allEntries��beforeInvocation��
            ����value��key��condition��������@Cacheable��Ӧ���������ơ���value��ʾ��������Ƿ�������ЩCache�ϵģ���ӦCache�����ƣ���
    key��ʾ��Ҫ��������ĸ�key����δָ�����ʹ��Ĭ�ϲ������ɵ�key��condition��ʾ�������������������
           ��������������һ���³��ֵ���������allEntries��beforeInvocation��

    3.1.allEntries����
    allEntries��boolean���ͣ���ʾ�Ƿ���Ҫ��������е�����Ԫ�ء�Ĭ��Ϊfalse����ʾ����Ҫ����ָ����allEntriesΪtrueʱ��
    Spring Cache������ָ����key���е�ʱ��������ҪCacheһ��������е�Ԫ�أ����һ��һ�����Ԫ�ظ���Ч�ʡ�
    @CacheEvict(value="users", allEntries=true)
	public void delete(Integer id) {
	
	   System.out.println("delete user by id: " + id);
	
	}

	1.3.2  beforeInvocation����
	�������Ĭ�����ڶ�Ӧ�����ɹ�ִ��֮�󴥷��ģ������������Ϊ�׳��쳣��δ�ܳɹ�����ʱҲ���ᴥ�����������ʹ��beforeInvocation���Ըı䴥�����������ʱ�䣬
	������ָ��������ֵΪtrueʱ��Spring���ڵ��ø÷���֮ǰ��������е�ָ��Ԫ�ء�

	@CacheEvict(value="users", beforeInvocation=true)
	
	public void delete(Integer id) {
	
	   System.out.println("delete user by id: " + id);
	
	}
            ��ʵ����ʹ��@CacheEvict�������Ԫ���⣬������ʹ��Ehcache��Ϊʵ��ʱ������Ҳ��������Ehcache������������ԣ�
            ����ͨ��Ehcache�������ļ���ָ���ġ�����Ehcache���Ǳ����������ص㣬����Ͳ���׸���ˡ�*/
	
	/**4     @Caching
    @Cachingע�������������һ��������������ͬʱָ�����Spring Cache��ص�ע�⡣��ӵ���������ԣ�cacheable��put��evict��
           �ֱ�����ָ��@Cacheable��@CachePut��@CacheEvict��

	@Caching(cacheable = @Cacheable("users"), evict = { @CacheEvict("cache2"),

    @CacheEvict(value = "cache3", allEntries = true) })*/
	
	@Caching(cacheable = @Cacheable("users"), evict = { @CacheEvict("cache2"),
		    @CacheEvict(value = "cache3", allEntries = true) })
	public User find(Integer id) {
		return null;
	}
}
