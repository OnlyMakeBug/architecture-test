package test.shiro.realm;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zj.util.NullByteArrayException;
import com.zj.util.NullStringException;
import com.zj.util.security.md.MD5Utils;


/*
 * 
 * Shiro���������
 * 1.Subject,ʵ���Ͼ��Ƿ�����Session,����sid(���ƿ�����)��ȡ��
 * 2.SecurityManager
 * 3.Realm(��֤/Ȩ����Ȩ)
 * 
 * Shiro Realm��ʱ����?
 * 1.doGetAuthenticationInfo() - ��֤����
 * ������������û���¼��ʱ����õ�Ҳ����ִ��SecurityUtils.getSubject().login������ʱ����ã�ShiroҲ�����ñ���֤���������е�¼��֤��
 *     
 * 2.doGetAuthorizationInfo() - Ȩ����Ȩ����
 * �����������ǵ���SecurityUtils.getSubject().isPermitted()�������ʱ�����doGetAuthorizationInfo()
 * ������ĳ�������ϼ���@RequiresPermissions�������ô���Ƿ������������ʱ�򣬾ͻ��Զ�����SecurityUtils.getSubject().isPermitted�������Ӷ�����doGetAuthorizationInfo
 * */

/**
 * �û���¼Realm
 * */
public class UserRealm extends AuthorizingRealm {
	
	/** ���ش�Realm�� */
	public String getName() {
		return "UserRealm";
	}
	
	/**
	 * ��¼��֤
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("shiro��֤...");
		
		// 1.��ȡ�û�����
		@SuppressWarnings("unused")
		String username = (String) token.getPrincipal();
		
		// 2.�����û���usernameʹ��Dao���ѯ������ʵ������User
		// �����ѯ���Ϊ�գ��򷵻��쳣��
		/*
		 * if(user==null){
		 * 	   //������ǰ�û�������
		 *     throw new UnknownAccountException();
		 * }
		 * 
		 * */
		
		// 3.��ȡ����
		// ����pwd�ǲ�ѯ�������
		String pwd ="fc1709d0a95a6be30bc5926fdb7f22f4";  
		// 4.�����˻��������Լ�realmName��Ϣ
		// principalӦ��Ϊ��ѯ����ӳ��ʵ���࣬����Ϊnull 
		// �˻������������л�����Ҫʵ�����л��ӿ�
		//SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(new User(),pwd,getName());
		return new SimpleAuthenticationInfo("zhoujian",pwd,getName());
	}

	/** Ȩ����Ȩ */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		// 1.��principals��ȡ�û�ʵ��
		/* @SuppressWarnings("unused")
		User user = (User) principals.getPrimaryPrincipal();*/
		
		// 2.�����û�ʵ���ȡȨ����Ϣ
		// UserRole userRole = roleServiceImpl.getUserRoles(user);
		
		// 3.����һ�����϶��󣬴洢Ȩ��
		List<String> permissions = new ArrayList<String>();
		//  ���userRole != null;
		//  permissions.add(userRole.getRoleKey())
		
		//  ��������ListȨ�޴����ݿ���
		List<String> permissionsFromDao = new ArrayList<String>();
		permissionsFromDao.add("delete");
		permissionsFromDao.add("remove");
		permissionsFromDao.add("modify");
		permissions.addAll(permissionsFromDao);
		
		// 4.��֤��Ϣ
		SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
		// ���Ȩ��
		simpleAuthorizationInfo.addStringPermissions(permissions);
		
		// 5.�����Ҫ������ӽ�ɫȨ�޹���������Dao���ѯ
		simpleAuthorizationInfo.addRole("admin");
		
		return simpleAuthorizationInfo;
	}
	
	public static void main(String[] args) {
		
		// 1.shiro sha1����
		Object s = new SimpleHash("SHA1", "123456", null, 1024);
		// fc1709d0a95a6be30bc5926fdb7f22f4
		System.out.println(s);
		// SHA1-����40
		System.out.println(s.toString().length());
		
		// 2.shiro md5����
		// MD5/md5������
		Object s1 = new SimpleHash("md5", "123456", null, 1024);
		// fc1709d0a95a6be30bc5926fdb7f22f4
		System.out.println(s1);
		
		// 3.��֤MD5Utils�����㷨��ȷ��
		// e10adc3949ba59abbe56e057f20f883e
		//byte[] bs = null;
		//String string = null;
		
		try {
			System.out.println(MD5Utils.encode("123456", false));
		} catch (NullByteArrayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullStringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// e10adc3949ba59abbe56e057f20f883e
		System.out.println(new SimpleHash("md5", "123456", null, 1));
		// 32λ
		System.out.println(new SimpleHash("md5", "123456", "213213", 1));
		
	}
}
