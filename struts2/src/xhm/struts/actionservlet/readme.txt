����Action��ʹ��ԭ��Servlet api���������

1.ͨ��ActionContext�����ڲ����������������ȡ���ݣ����صĶ�����Map
1.1.��ȡServletContext��map: ActionContext.getContext().getApplication()
1.2.��ȡrequest map�� ֱ��ActionContext().getContext().put(key,value);
1.3.��ȡSession�� ActionContext().getContext().getSession();
1.4.��ȡPageContext����

2.ͨ��ServletActionContext
2.1.��ȡServletContext - applicationScope
2.2.��ȡSession - sessionScope
2.3.��ȡHttpServletRequest - requestScope
2.4.��ȡPageContext- pageContext  -  ��Action�л�ȡΪ�ա�

