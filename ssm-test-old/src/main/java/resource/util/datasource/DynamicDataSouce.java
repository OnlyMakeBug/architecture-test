package resource.util.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @time 2019��12��25�� ����3:36:08
 * @author Administrator
 * @corporation luku
 * @description: ��̬����Դ
 */
public class DynamicDataSouce extends AbstractRoutingDataSource{
	/**
	 * �˷����������һ��������targetDataSources�е�dataSource����null����ʹ��Ĭ��dataSource
	 * */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceResolver.getDataSource();    
	}

}



