package test.frame;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

/**
  * @time��2019��12��27�� ����11:52:06
  * @author��zhoujian
  * @corporation��luke
  * @description��apache commons-io,FilenameUtilsʹ��
  * @finished��false
  * @finishTime��2019��12��27�� 23:52:28
  *
  */
public class FilenameUtilsTest {

	public static void main(String[] args) {
		//apache commons-io���Ѿ����ļ����api
				//������ȡ����api
				
				/**
				 * 1.ƴ���������֣������Ǽ򵥵ĺϲ��ַ���������������зָ������зָ����滻���ָ�����䡢�Ƴ�(��������ָ���ֻ����һ��)
				 * ���˷ָ���������windows��uninx�����һ���ġ�
				 * ע�⣺�����е�/�� \�ᱻ�����зָ����滻����windows��ʹ��\�����е�/�ᱻת����\
				 * ע�⣺����2Ӧ�������·���������Էָ�����ͷ���������ļ���Ŀ¼���������2�Էָ���/��\��ͷ���򷵻ص��ڲ���2
				 * ע�⣺�������Բ��Էָ�����β�����û�У�api���Զ���ӡ�
				 * ע�⣺������������ͷ������������2���ָ��������򷽷�����null�������ͷ�������ָ��������������ָ���������ͷλ�ã�����λ�õ������ָ���ֻ�ᱣ��һ����
				 * ע�⣺�������Ϊ����1��ͷ������ӷָ�������������Ҫע�⡣
				 * 
				 * */
				System.out.println(FilenameUtils.concat("c/////b", "x/dsa"));
				
				//  a\b\x\dsa
				System.out.println(FilenameUtils.concat("a/b", "x/dsa"));
				//  \x\dsa
				System.out.println(FilenameUtils.concat("a/b", "\\x/dsa"));
				//  a\b\x\dsa
				System.out.println(FilenameUtils.concat("a/b", "x/dsa"));
				//  a\b\x\dsa
				System.out.println(FilenameUtils.concat("a/b/", "x/dsa"));
				
				//  null :������������ͷ���ܳ���2���ָ��������򷵻�null
				System.out.println(FilenameUtils.concat("///a/b/", "x//////dsa"));
				
				//  \\x\\dsa\
				System.out.println(FilenameUtils.concat("//a/b////", "//x///////dsa/"));
				
				System.err.println(FilenameUtils.concat("//a", "b/c//v"));
				
				//null
				System.out.println(FilenameUtils.concat("///a/b//", "x///////dsa"));
				
				/**
				 * 2.1.�Ƚ��ļ��� - Ĭ������£���Сд���е�
				 * ���Ǽ򵥵ıȽ������ַ���,���Ƕ��ڿ�ָ��Ҳ����ȷ���бȽϡ���String.equals�ĸĽ��档
				 * ע�⣺ equals(null,null)  //true
				 * */
				//  true
				System.out.println(FilenameUtils.equals(null, null));
				//  false
				System.out.println(FilenameUtils.equals(null, "1"));
				//  false
				System.out.println(FilenameUtils.equals(" ", "1"));
				//  false ��Сд���е�
				System.out.println(FilenameUtils.equals("a", "A"));
				
				/**
				 * 2.2. ������equals�������������������ô�Сд����
				 * IOCase.INSENSITIVE
				 * IOCase.SENSITIVE
				 * IOCase.SYSTEM  //��ϵͳ����������ֲ��ǿ,����windows���൱��IOCase.INSENSITIVE
				 * 
				 * nor
				 * 
				 * */
				// false���ո񲻻ᱻ�Ƴ�
				System.out.println(FilenameUtils.equals("a", "A ", false, IOCase.INSENSITIVE));
				// true
				System.out.println(FilenameUtils.equals("a", "A", false, IOCase.INSENSITIVE));
				System.out.println("---");
				// true
				System.out.println(FilenameUtils.equals("a", "A", false, IOCase.SYSTEM));
				
				String s1="\\a\\b",s2="\\\\a\\b";
				//false
				System.out.println(FilenameUtils.equals(s1,s2, false, IOCase.INSENSITIVE));
				// \a\b
				System.out.println(s1);
				System.out.println(s2);
				
				/**
				 * 3.1.��ʽ���ļ�����normalize
				 * 
				 * */
				// \\a\a\a
				System.out.println(FilenameUtils.normalize("//a/a/a").length());
				//ע�⣺�ո񲻻��Ƴ�
				System.out.println(FilenameUtils.normalize("//a/a/a ").length());
				
				/**
				 * 3.2.normalizeNoEndSeparator�������൱��normalize +(���ĩβ�зָ������Ƴ�ĩβ�ķָ���)
				 * */
				//  a/b/b/c
				System.out.println(FilenameUtils.normalizeNoEndSeparator("a/b/b/c"));
				//  a\b\b\c
				System.out.println(FilenameUtils.normalizeNoEndSeparator("a/b/b/c/"));
				//  a\b\b\c
				System.out.println(FilenameUtils.normalizeNoEndSeparator("a/b/b/c/////"));
				
				/**
				 * 4. directoryContains������ļ�/Ŀ¼�Ƿ�����һ����¼�����ļ�/��Ŀ¼
				 * ע�⣺�뱣֤ʹ����ͬ���ͺ͸����ķָ�������Ҳ����normalize��ȷ����һ�㡣
				 * */
				try {
					//  true
					System.out.println(FilenameUtils.directoryContains("a/b/c", "a/b/c/d"));
					
					//true
					System.out.println(FilenameUtils.directoryContains("/a/b/c", "/a/b/c/d"));
					
					//  true
					System.out.println(FilenameUtils.directoryContains("/a/b/c", "/a/b/c/d/e/1.txt"));
					
					//false
					System.out.println(FilenameUtils.directoryContains("/a/b/c", "\\a/b/c/d/e/1.txt"));
					
					//  normalize��ȷ��ͬ���ͷָ��������ǿ�ͷ�ָ�����Ҫ�����߱�֤һ��
					//  true
					System.out.println(FilenameUtils.directoryContains(FilenameUtils.normalize("/a/b/c"), FilenameUtils.normalize("\\a/b/c/d/e/1.txt")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				/**
				 * 5.getExtension ��ȡ��չ������"."
				 * */
				//  txt
				System.out.println(FilenameUtils.getExtension("a/b/c/ds.txt"));
				//  "" - �մ�������null
				System.out.println(FilenameUtils.getExtension("a/b/c/ds"));
				
				/**
				 * 6.indexOfExtension���ַ����к�׺��ʼ�±꣬��Ҫע����ǲ��ҵ���"."��λ�á�
				 * */
				
				//  1
				System.out.println(FilenameUtils.indexOfExtension("a.txt"));
				
				/**
				 * 7.getFullPath
				 * ��ȡ�ļ�������·���������·�����򷵻�·������
				 * */
				//  /a/b/c/
				System.out.println(FilenameUtils.getFullPath("/a/b/c/d"));
				
				//  /a/b/c/
				System.out.println(FilenameUtils.getFullPath("/a/b/c/"));
				
				//  ~/a/b/c/
				System.out.println(FilenameUtils.getFullPath("~/a/b/c/"));
				
				//  */a/b/c/
				System.out.println(FilenameUtils.getFullPath("*/a/b/c/d"));
				
				//   ""
				System.out.println(FilenameUtils.getFullPath("c"));
				
				//  c:
				System.out.println(FilenameUtils.getFullPath("c:"));
				
				/**
				 * 8.getBaseName
				 * ��ȡ�ļ����������Ŀ¼����""
				 * */
				
				//  22
				System.out.println(FilenameUtils.getBaseName("a/b/c/22.txt"));
				//  ""
				System.out.println(FilenameUtils.getBaseName("a/b/c/"));
				
				/**
				 * 9.�Ƴ���׺���������أ������Ŀ¼����ֱ�ӷ���Ŀ¼
				 * */
				//  a/b/1
				System.out.println(FilenameUtils.removeExtension("a/b/1.txt"));
				//  a/b/
				System.out.println(FilenameUtils.removeExtension("a/b/"));
	}
}
